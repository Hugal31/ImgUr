package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

import java.util.List;

public class AccountInterface {

    private static final String ME = "me";

    public enum Sort {
        NEWEST("newest"),
        OLDEST("oldest");

        private final String value;

        Sort(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private final Imgur imgur;

    AccountInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public Account getAccount() throws ImgurException {
        return getAccount("me");
    }

    public Account getAccount(String userName) throws ImgurException {
        return AccountUtil.requestAccount(imgur,
                new OAuthRequest(Verb.GET, String.format("%s/account/%s", Imgur.API_URL, userName)));
    }

    public List<ImgurItem> getFavorites() throws ImgurException {
        return getFavorites(0, Sort.NEWEST);
    }

    public List<ImgurItem> getFavorites(int page, Sort sort) throws ImgurException {
        return getFavorites(page, sort, ME);
    }

    public List<ImgurItem> getFavorites(int page, Sort sort, String userName) throws ImgurException {
        return GalleryUtil.requestGallery(imgur,
                new OAuthRequest(Verb.GET,
                        String.format("%s/account/%s/favorites/%d/%s", Imgur.API_URL, userName, page, sort)
                )
        );
    }

    public List<Image> getImages() throws ImgurException {
        return getImages(0);
    }

    public List<Image> getImages(int page) throws ImgurException {
        return getImages(0, ME);
    }

    public List<Image> getImages(int page, String userName) throws ImgurException {
        return ImageUtil.requestImages(imgur,
                new OAuthRequest(Verb.GET, String.format("%s/account/%s/images/%d", Imgur.API_URL, userName, page)));
    }
}
