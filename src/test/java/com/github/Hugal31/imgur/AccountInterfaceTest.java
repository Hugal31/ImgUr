package com.github.Hugal31.imgur;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountInterfaceTest {
    @Test
    @Ignore
    public void testGetImages() throws Exception {
        AccountInterface accountInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret())
                .getAccountInterface();

        List<Image> images = accountInterface.getImages();
        assertNotEquals(0, images.size());
    }
}
