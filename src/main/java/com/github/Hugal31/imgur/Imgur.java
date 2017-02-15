package com.github.Hugal31.imgur;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

public class Imgur {

    public static final String API_URL = "https://api.imgur.com/";

    private final String apiKey;

    private final String apiSecret;

    private final OAuth20Service oAuthService;

    private AlbumInterface albumInterface;

    private GalleryInterface galleryInterface;

    private ImageInterface imageInterface;

    public Imgur(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        oAuthService = new ServiceBuilder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build(com.github.scribejava.apis.ImgurApi.instance());
    }

    public AlbumInterface getAlbumInterface() {
        if (albumInterface == null) {
            albumInterface = new AlbumInterface(this);
        }
        return albumInterface;
    }

    public GalleryInterface getGalleryInterface() {
        if (galleryInterface == null) {
            galleryInterface = new GalleryInterface(this);
        }
        return galleryInterface;
    }

    public ImageInterface getImageInterface() {
        if (imageInterface == null) {
            imageInterface = new ImageInterface(this);
        }
        return imageInterface;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public OAuth20Service getOAuthService() {
        return oAuthService;
    }

    public OAuth2AccessToken getAccessToken() {
        return null;
    }
}
