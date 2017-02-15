package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import org.json.JSONObject;

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

    public List<ImgurItem> getMainGalleryInfos() throws Exception {
        return getGallery(Section.HOT, Sort.VIRAL);
    }

    public List<ImgurItem> getGallery(Section section, Sort sort) throws Exception {
        return getGallery(section, sort, 0, Window.DAY, true);
    }

    public List<ImgurItem> getGallery(Section section, Sort sort, int page, Window window, boolean showViral) throws Exception {
        final OAuthRequest request = new OAuthRequest(
                Verb.GET,
                Imgur.API_URL + "3/gallery/" + section + '/' + sort + '/' + window + '/' + page + ".json?showViral=" + showViral);
        imgur.getOAuthService().signRequest(imgur.getAccessToken(), request);

        try {
            Response response = imgur.getOAuthService().execute(request);
            if (response.getCode() != 200)
                throw new ImgurException("API return response with code " + response.getCode() + " and body: " + response.getBody());
            JSONObject jsonResponse = new JSONObject(response.getBody());
            // FIxme data is a JSONArray
            return GalleryUtil.createGallery(jsonResponse.getJSONArray("data"));
        } catch (ImgurException e) {
            throw e;
        } catch (Exception e) {
            throw new ImgurException(e);
        }
    }

}
