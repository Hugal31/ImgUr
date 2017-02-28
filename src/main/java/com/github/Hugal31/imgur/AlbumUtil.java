package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

final class AlbumUtil {

    static Album createAlbum(JSONObject data) {
        Album album = new Album();
        album.setId(data.getString("id"));
        if (! data.isNull("title"))
            album.setTitle(data.getString("title"));
        if (! data.isNull("description"))
        album.setDescription(data.optString("description", null));
        album.setDatetime(new Date(data.getLong("datetime")));
        return album;
    }

    static Album requestAlbum(Imgur imgur, OAuthRequest request) throws ImgurException {
        try {
            return createAlbum(imgur.executeJSONRequest(request).getJSONObject("data"));
        } catch (JSONException e) {
            throw new ImgurException(e);
        }
    }

}
