package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONObject;

public class ImageInterface {

    private final Imgur imgur;

    ImageInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Image getInfos(String imageId) throws ImgurException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, Imgur.API_URL + "3/image/" + imageId);
        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);

            JSONObject jsonResponse = new JSONObject(response.getBody());
            if (! jsonResponse.optBoolean("success", false))
                throw new ImgurException("API return response with code "
                        + jsonResponse.optInt("code", response.getCode())
                        + " and body: " + jsonResponse);

            return ImageUtil.createImage(jsonResponse.getJSONObject("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }
}
