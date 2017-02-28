package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

public class ImageInterface {

    private final Imgur imgur;

    ImageInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Image getInfos(String imageId) throws ImgurException {
        return ImageUtil.requestImage(imgur, new OAuthRequest(Verb.GET, Imgur.API_URL + "/image/" + imageId));
    }

}
