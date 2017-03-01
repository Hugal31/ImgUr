package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import org.apache.commons.codec.binary.Base64;

import java.io.*;

public class ImageInterface {

    private final Imgur imgur;

    ImageInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Image getInfos(String imageId) throws ImgurException {
        return ImageUtil.requestImage(imgur, new OAuthRequest(Verb.GET, Imgur.API_URL + "/image/" + imageId));
    }

    public void delete(String imageId) throws ImgurException {
        imgur.executeJSONRequest(new OAuthRequest(
                Verb.DELETE,
                String.format("%s/image/%s", Imgur.API_URL, imageId)));
    }

    public void favorite(String imageId) throws ImgurException {
        imgur.executeJSONRequest(new OAuthRequest(
                Verb.POST,
                String.format("%s/image/%s/favorite", Imgur.API_URL, imageId)));
    }

    public void upload(File file) throws ImgurException, IOException {
        upload(file, null, null);
    }

    public void upload(File file, String title, String description) throws ImgurException, IOException {
        upload(file, title, description, null);
    }

    public void upload(File file, String title, String description, String fileName) throws ImgurException, IOException {
        InputStream inputStream = new FileInputStream(file);
        upload(inputStream, title, description, fileName);
        inputStream.close();
    }

    public void upload(InputStream inputStream) throws ImgurException, IOException {
        upload(inputStream, null, null);
    }

    public void upload(InputStream inputStream, String title, String description) throws ImgurException, IOException {
        upload(inputStream, title, description, null);
    }

    public void upload(InputStream inputStream, String title, String description, String fileName) throws ImgurException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);

        {
            int readed;
            byte[] buf = new byte[1024];
            while ((readed = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, readed);
            }
        }

        upload(new String(Base64.encodeBase64(outputStream.toByteArray())), title, description, fileName);
    }

    public void upload(String base64Image) throws ImgurException {
        upload(base64Image, null, null);
    }

    public void upload(String base64Image, String title, String description) throws ImgurException {
        upload(base64Image, title, description, null);
    }

    public void upload(String base64Image, String title, String description, String fileName) throws ImgurException {
        OAuthRequest request = new OAuthRequest(Verb.POST, Imgur.API_URL + "/image");

        request.addParameter("image", base64Image);
        request.addParameter("type", "base64");

        if (title != null)
            request.addParameter("title", title);
        if (description != null)
            request.addParameter("description", description);
        if (fileName != null)
            request.addParameter("name", fileName);

        imgur.executeJSONRequest(request);
    }

}
