package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONObject;

import java.util.List;

public class AccountInterface {

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
            if (! jsonResponse.optBoolean("status", true))
                throw new ImgurException("API return response with code " + response.getCode() + " and body: " + response.getBody());

            return AccountUtil.createAccount(jsonResponse.getJSONObject("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

    public List<Image> getImages() throws ImgurException {
        return getImages("me", 0);
    }

    public List<Image> getImages(String userName) throws ImgurException {
        return getImages(userName, 0);
    }

    public List<Image> getImages(String userName, int page) throws ImgurException {
        OAuthRequest request = new OAuthRequest(Verb.GET,
                String.format("%s3/account/%s/images/%d", Imgur.API_URL, userName, page));

        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);

            if (response.getCode() != 200)
                throw new ImgurException("API return response with code " + response.getCode() + " and body: " + response.getBody());

            JSONObject jsonResponse = new JSONObject(response.getBody());
            return ImageUtil.createImages(jsonResponse.getJSONArray("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }
}
