package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuth2AccessToken;

public class AuthInterface {

    private final Imgur imgur;

    AuthInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public String getAuthorizationUrl() {
        return imgur.getOAuthService().getAuthorizationUrl();
    }

    public OAuth2AccessToken getAccessToken(String code) throws ImgurException {
        try {
            return imgur.getOAuthService().getAccessToken(code);
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

    public OAuth2AccessToken refreshAccessToken(String refreshToken) throws ImgurException {
        try {
            return imgur.getOAuthService().refreshAccessToken(refreshToken);
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

}
