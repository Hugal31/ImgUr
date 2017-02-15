package com.github.Hugal31.imgur;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GalleryInterfaceTest {

    @Test
    public void testGetGallery() throws Exception {
        GalleryInterface galleryInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret()).getGalleryInterface();
        List<ImgurItem> items = galleryInterface.getMainGalleryInfos();
        assertNotEquals(0, items.size());
    }

    @Test
    public void testSearch() throws Exception {
        GalleryInterface galleryInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret()).getGalleryInterface();
        List<ImgurItem> items = galleryInterface.search("42");
        assertNotEquals(0, items.size());
    }

    @Test
    public void testAdvancedSearch() throws Exception {
        GalleryInterface galleryInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret()).getGalleryInterface();
        List<ImgurItem> items = galleryInterface.advancedSearch(null, null, "42", null, null, null);
        assertNotEquals(0, items.size());
        assertTrue(items.get(0).getTitle().contains("42") || items.get(0).getDescription().contains("42"));
    }

}
