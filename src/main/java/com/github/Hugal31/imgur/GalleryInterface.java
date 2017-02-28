package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

import java.util.List;

public class GalleryInterface {

    public enum Section {
        HOT("hot"),
        TOP("top"),
        USER("user");

        private final String value;

        Section(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Sort {
        VIRAL("viral"),
        TOP("top"),
        TIME("time"),
        RISING("rising");

        private final String value;

        Sort(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Window {
        ALL("all"),
        TOP("top"),
        DAY("day"),
        WEEK("week"),
        MONTH("month"),
        YEAR("year");

        private final String value;

        Window(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }


    private static final String VIRAL = "viral";

    private static final String TIME = "time";

    private static final String RISING = "rising";

    private final Imgur imgur;

    GalleryInterface(Imgur imgur) {
        this.imgur = imgur;
    }

    public List<ImgurItem> getMainGalleryInfos() throws ImgurException {
        return getGallery(Section.HOT, Sort.VIRAL);
    }

    public List<ImgurItem> getGallery(Section section, Sort sort) throws ImgurException {
        return getGallery(section, sort, 0, Window.DAY, true);
    }

    public List<ImgurItem> getGallery(Section section, Sort sort, int page, Window window, boolean showViral) throws ImgurException {
        OAuthRequest request = new OAuthRequest(Verb.GET,
                String.format("%s/gallery/%s/%s/%s/%d", Imgur.API_URL, section, sort, window, page));

        if (showViral)
            request.addParameter("showViral", "true");

        return GalleryUtil.requestGallery(imgur, request);
    }

    public List<ImgurItem> search(String query) throws Exception {
        return search(query, 0);
    }

    public List<ImgurItem> search(String query, int page) throws Exception {
        return search(query, page, Sort.TIME, Window.ALL);
    }

    public List<ImgurItem> search(String query, int page, Sort sort, Window window) throws Exception {
        OAuthRequest request = new OAuthRequest(
                Verb.GET,
                String.format("%s/gallery/search/%s/%s/%d.json", Imgur.API_URL, sort, window, page));
        request.addParameter("q", query);
        return GalleryUtil.requestGallery(imgur, request);
    }

    public List<ImgurItem> advancedSearch(String all,
                                          String any,
                                          String exactly,
                                          String not,
                                          String type,
                                          String size) throws Exception {
        return advancedSearch(all, any, exactly, not, type, size, 0);
    }

    public List<ImgurItem> advancedSearch(String all,
                                          String any,
                                          String exactly,
                                          String not,
                                          String type,
                                          String size,
                                          int page) throws Exception {
        return advancedSearch(all, any, exactly, not, type, size, page, Sort.TIME, Window.ALL);
    }

    public List<ImgurItem> advancedSearch(String all,
                                          String any,
                                          String exactly,
                                          String not,
                                          String type,
                                          String size,
                                          int page,
                                          Sort sort,
                                          Window window) throws Exception {
        OAuthRequest request = new OAuthRequest(Verb.GET, String.format("%s/gallery/search/%s/%s/%d.json", Imgur.API_URL, sort, window, page));

        if (all != null)
            request.addParameter("q_all", all);
        if (any != null)
            request.addParameter("q_any", any);
        if (exactly != null)
            request.addParameter("q_exactly", exactly);
        if (not != null)
            request.addParameter("q_not", not);
        if (type != null)
            request.addParameter("q_type", type);
        if (size != null)
            request.addParameter("q_size_px", size);

        return GalleryUtil.requestGallery(imgur, request);
    }

}
