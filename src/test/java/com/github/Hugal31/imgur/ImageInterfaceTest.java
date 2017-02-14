package com.github.Hugal31.imgur;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageInterfaceTest {

    @Test
    public void testGetInfos() throws Exception {
        ImageInterface imageInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret()).getImageInterface();
        Image image = imageInterface.getInfos("19LofcC");
        assertEquals("19LofcC", image.getId());
    }
}
