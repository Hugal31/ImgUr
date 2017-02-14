package com.github.Hugal31.imgur;

import org.junit.Test;

public class ImgurTest {

    @Test
    public void testConnection() throws Exception {
        Imgur imgur = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret());
    }
}
