package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuth2AccessToken;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class AccountInterfaceTest {

    private static OAuth2AccessToken accessToken;

    @BeforeClass
    public static void prepareImgur() throws Exception {
        assumeNotNull(ImgurPropertiesHelper.getRefreshToken());

        Imgur imgur = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret());
        AuthInterface authInterface = imgur.getAuthInterface();

        accessToken = authInterface.refreshAccessToken(ImgurPropertiesHelper.getRefreshToken());
    }

    @Test
    public void testGetAccount() throws Exception {
        Imgur imgur = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret());
        imgur.setAccessToken(accessToken);
        AccountInterface accountInterface = imgur.getAccountInterface();

        Account account = accountInterface.getAccount();
        assertNotNull(account.getUrl());
        System.err.println(account);
    }

    @Test
    public void testFavorites() throws Exception {
        Imgur imgur = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret());
        imgur.setAccessToken(accessToken);
        AccountInterface accountInterface = imgur.getAccountInterface();

        List<ImgurItem> items = accountInterface.getFavorites();
        assertNotEquals(0, items.size());
        System.err.println(items);
    }

    @Test
    public void testGetImages() throws Exception {
        Imgur imgur = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret());
        imgur.setAccessToken(accessToken);

        AccountInterface accountInterface = imgur.getAccountInterface();

        List<Image> images = accountInterface.getImages();
        assertNotEquals(0, images.size());
    }

}
