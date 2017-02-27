package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuth2AccessToken;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class AuthInterfaceTest {

    @Test
    public void testGetAuthorizationUrl() throws Exception {
        assumeNotNull(ImgurPropertiesHelper.getCallback());

        AuthInterface authInterface = new Imgur(ImgurPropertiesHelper.getApiKey(),
                ImgurPropertiesHelper.getApiSecret(),
                ImgurPropertiesHelper.getCallback())
                .getAuthInterface();

        String url = authInterface.getAuthorizationUrl();
        System.err.println(url);
        assertTrue(url.contains("redirect_uri="));
    }

    @Test
    public void testGetAccessToken() throws Exception {
        assumeNotNull(ImgurPropertiesHelper.getCallback(), ImgurPropertiesHelper.getCode());

        AuthInterface authInterface = new Imgur(ImgurPropertiesHelper.getApiKey(),
                ImgurPropertiesHelper.getApiSecret(),
                ImgurPropertiesHelper.getCallback())
                .getAuthInterface();

        OAuth2AccessToken accessToken = authInterface.getAccessToken(ImgurPropertiesHelper.getCode());
        System.err.println(accessToken);
    }
}
