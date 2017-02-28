package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

public class AlbumInterface {

    private final Imgur imgur;

    AlbumInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Album getInfos(String albumId) throws ImgurException {
        return AlbumUtil.requestAlbum(imgur,
                new OAuthRequest(Verb.GET, String.format("%s/album/%s", Imgur.API_URL, albumId)));
    }

}
