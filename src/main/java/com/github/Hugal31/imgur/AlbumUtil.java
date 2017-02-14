package com.github.Hugal31.imgur;

import org.json.JSONObject;

import java.util.Date;

final class AlbumUtil {
    static Album createAlbum(JSONObject data) {
        Album album = new Album();
        album.setId(data.getString("id"));
        album.setTitle(data.optString("title"));
        album.setDescription(data.optString("description"));
        album.setDatetime(new Date(data.getBigInteger("datetime").longValue()));
        return album;
    }
}
