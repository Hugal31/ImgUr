package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONObject;

import java.util.List;

public class GalleryInterface {

    private static final String HOT = "hot";

    private static final String TOP = "top";

    private static final String USER = "user";

    private static final String VIRAL = "viral";

    private static final String TIME = "time";

    private static final String RISING = "rising";

    private final Imgur imgur;

    GalleryInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public List<ImgurItem> getMainGalleryInfos() throws Exception {
        return getInfos(HOT, VIRAL);
    }

    public List<ImgurItem> getInfos(String section, String sort) throws Exception {
        return getInfos(section, sort, 0, "day", true);
    }

    public List<ImgurItem> getInfos(String section, String sort, int page, String window, boolean showViral) throws Exception {
        final OAuthRequest request = new OAuthRequest(
                Verb.GET,
                Imgur.API_URL + "3/gallery/" + section + '/' + sort + '/' + window + '/' + page + ".json?showViral=" + showViral);
        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);
            if (response.getCode() != 200)
                throw new ImgurException("API return response with code " + response.getCode() + " and body: " + response.getBody());
            JSONObject jsonResponse = new JSONObject(response.getBody());
            // FIxme data is a JSONArray
            return GalleryUtil.createGallery(jsonResponse.getJSONArray("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

}
