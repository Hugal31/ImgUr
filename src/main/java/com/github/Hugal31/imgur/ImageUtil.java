package com.github.Hugal31.imgur;

import org.json.JSONObject;

import java.util.Date;

final class ImageUtil {

    static Image createImage(JSONObject data) {
        Image image = new Image();
        image.setId(data.getString("id"));
        image.setTitle(data.optString("title"));
        image.setDescription(data.optString("description"));
        image.setDatetime(new Date(data.getBigInteger("datetime").longValue()));
        image.setType(data.getString("type"));
        image.setWidth(data.getInt("width"));
        image.setHeight(data.getInt("height"));
        image.setSize(data.getInt("size"));
        image.setViews(data.getInt("views"));
        image.setLink(data.getString("link"));
        image.setSection(data.optString("section"));
        return image;
    }

}
