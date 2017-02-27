package com.github.Hugal31.imgur;

import org.json.JSONObject;

import java.util.Date;

final class AlbumUtil {
    static Album createAlbum(JSONObject data) {
        Album album = new Album();
        album.setId(data.getString("id"));
        if (! data.isNull("title"))
            album.setTitle(data.getString("title"));
        if (! data.isNull("description"))
        album.setDescription(data.getString("description"));
        album.setDatetime(new Date(data.getLong("datetime")));
        return album;
    }
}
