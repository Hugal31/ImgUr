package com.github.Hugal31.imgur;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

final class GalleryUtil {

    static List<ImgurItem> createGallery(JSONArray data) {
        List<ImgurItem> itemList = new ArrayList<ImgurItem>();
        for (Object object: data) {
            if (((JSONObject) object).has("cover"))
                itemList.add(AlbumUtil.createAlbum((JSONObject) object));
            else
                itemList.add(ImageUtil.createImage((JSONObject) object));
        }
        return itemList;
    }

}
