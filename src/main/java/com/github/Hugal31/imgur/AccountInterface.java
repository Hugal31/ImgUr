package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONObject;

import java.util.List;

public class AccountInterface {

    public enum Sort {
        NEWEST("newest"),
        OLDEST("oldest");

        private final String value;

        Sort(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private final Imgur imgur;

    AccountInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Account getAccount() throws ImgurException {
        return getAccount("me");
    }

    public Account getAccount(String userName) throws ImgurException {
        OAuthRequest request = new OAuthRequest(Verb.GET, String.format("%s3/account/%s", Imgur.API_URL, userName));

        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);

            JSONObject jsonResponse = new JSONObject(response.getBody());
            if (! jsonResponse.optBoolean("success", false))
                throw new ImgurException("API return response with code "
                        + jsonResponse.optInt("code", response.getCode())
                        + " and body: " + jsonResponse);

            return AccountUtil.createAccount(jsonResponse.getJSONObject("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

    public List<ImgurItem> getFavorites() throws ImgurException {
        return getFavorites(0, Sort.NEWEST);
    }

    public List<ImgurItem> getFavorites(int page, Sort sort) throws ImgurException {
        return getFavorites(page, sort, "me");
    }

    public List<ImgurItem> getFavorites(int page, Sort sort, String userName) throws ImgurException {
        OAuthRequest request = new OAuthRequest(Verb.GET,
                String.format("%s3/account/%s/favorites/%d/%s", Imgur.API_URL, userName, page, sort));
        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);

            JSONObject jsonObject = new JSONObject(response.getBody());
            if (! jsonObject.optBoolean("success", false))
                throw new ImgurException("API return response with code "
                        + jsonObject.optInt("code", response.getCode())
                        + " and body: " + jsonObject);
            return GalleryUtil.createGallery(jsonObject.getJSONArray("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

    public List<Image> getImages() throws ImgurException {
        return getImages(0);
    }

    public List<Image> getImages(int page) throws ImgurException {
        return getImages(0, "me");
    }

    public List<Image> getImages(int page, String userName) throws ImgurException {
        OAuthRequest request = new OAuthRequest(Verb.GET,
                String.format("%s3/account/%s/images/%d", Imgur.API_URL, userName, page));

        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);

            if (response.getCode() != 200)
                throw new ImgurException("API return response with code " + response.getCode() + " and body: " + response.getBody());

            JSONObject jsonResponse = new JSONObject(response.getBody());
            if (! jsonResponse.optBoolean("success", false))
                throw new ImgurException("API return response with code "
                        + jsonResponse.optInt("code", response.getCode())
                        + " and body: " + jsonResponse);

            return ImageUtil.createImages(jsonResponse.getJSONArray("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }
}
