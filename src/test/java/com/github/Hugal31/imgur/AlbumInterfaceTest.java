package com.github.Hugal31.imgur;

import org.junit.Test;

import static org.junit.Assert.*;


public class AlbumInterfaceTest {

    @Test
    public void testGetInfos() throws Exception {
        AlbumInterface albumInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret())
                .getAlbumInterface();
        Album album = albumInterface.getInfos("1yohv");
        assertEquals("1yohv", album.getId());
    }
}
