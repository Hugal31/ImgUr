package com.github.Hugal31.imgur;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

public class ImageInterfaceTest {

    @Test
    public void testGetInfos() throws Exception {
        ImageInterface imageInterface = new Imgur(ImgurPropertiesHelper.getApiKey(), ImgurPropertiesHelper.getApiSecret()).getImageInterface();
        Image image = imageInterface.getInfos("19LofcC");
        assertEquals("19LofcC", image.getId());
    }

    @Test
    public void testUploadFile() throws Exception {
        assumeNotNull(ImgurPropertiesHelper.getRefreshToken(), ImgurPropertiesHelper.getImageToUpload());

        Imgur imgur = new Imgur(ImgurPropertiesHelper.getApiKey(),
                ImgurPropertiesHelper.getApiSecret(),
                ImgurPropertiesHelper.getCallback());

        AuthInterface authInterface = imgur.getAuthInterface();
        try {
            imgur.setAccessToken(authInterface.refreshAccessToken(ImgurPropertiesHelper.getRefreshToken()));
        } catch (ImgurException e) {
            assumeNoException("Unable to auth", e);
        }

        ImageInterface imageInterface = imgur.getImageInterface();
        imageInterface.upload(new File(ImgurPropertiesHelper.getImageToUpload()), "Title", "Description");
    }
}
