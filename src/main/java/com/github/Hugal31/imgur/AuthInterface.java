package com.github.Hugal31.imgur;

import com.github.scribejava.apis.ImgurApi;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

import java.net.URI;
import java.net.URL;

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

}
