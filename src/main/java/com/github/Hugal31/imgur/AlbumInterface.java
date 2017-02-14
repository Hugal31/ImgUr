package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONObject;

public class AlbumInterface {

    private final Imgur imgur;

    AlbumInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Album getInfos(String albumId) throws ImgurException {
        final OAuthRequest request = new OAuthRequest(Verb.GET, Imgur.API_URL + "3/album/" + albumId);
        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);
            if (response.getCode() != 200)
                throw new ImgurException("API return response with code " + response.getCode() + " and body: " + response.getBody());
            JSONObject jsonResponse = new JSONObject(response.getBody());
            return AlbumUtil.createAlbum(jsonResponse.getJSONObject("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

}
