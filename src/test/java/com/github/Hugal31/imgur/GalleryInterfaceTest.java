package com.github.Hugal31.imgur;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GalleryInterfaceTest {

    @Test
    public void testGetInfos() throws Exception {
        GalleryInterface galleryInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret()).getGalleryInterface();
        List<ImgurItem> images = galleryInterface.getMainGalleryInfos();
    }

}
