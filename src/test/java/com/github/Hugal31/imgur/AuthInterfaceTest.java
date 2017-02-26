package com.github.Hugal31.imgur;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthInterfaceTest {

    @Test
    public void testGetAuthorizationUrl() throws Exception {
        AuthInterface authInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret(), "http://github.com/")
                .getAuthInterface();

        String url = authInterface.getAuthorizationUrl();
        System.err.println(url);
        assertTrue(url.contains("redirect_uri=http%3A%2F%2Fgithub.com%2F"));
    }

    @Test
    public void testGetAccessToken() throws Exception {
        AuthInterface authInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret(), "http://github.com/")
                .getAuthInterface();

        System.err.println(authInterface.getAccessToken("b34286ef6635ef27bc890c5614cbcb5e2df64cb1"));
    }
}
