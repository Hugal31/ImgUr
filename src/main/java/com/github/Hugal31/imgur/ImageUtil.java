package com.github.Hugal31.imgur;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

final class ImageUtil {

    static Image createImage(JSONObject data) {
        Image image = new Image();
        image.setId(data.getString("id"));
        if (! data.isNull("title"))
            image.setTitle(data.getString("title"));
        if (! data.isNull("description"))
            image.setDescription(data.getString("description"));
        image.setDatetime(new Date(data.getLong("datetime")));
        image.setType(data.getString("type"));
        image.setWidth(data.getInt("width"));
        image.setHeight(data.getInt("height"));
        image.setSize(data.getInt("size"));
        image.setViews(data.getInt("views"));
        image.setLink(data.getString("link"));
        image.setSection(data.optString("section", null));
        return image;
    }

    static List<Image> createImages(JSONArray data) {
        List<Image> images = new ArrayList<Image>();

        for (int i = 0; i < data.length(); i++) {
            Object o = data.get(i);
            if (o instanceof JSONObject) {
                images.add(createImage((JSONObject) o));
            }
        }

        return images;
    }

}
