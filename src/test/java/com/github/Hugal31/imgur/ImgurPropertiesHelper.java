package com.github.Hugal31.imgur;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

class ImgurPropertiesHelper {

    private static boolean loaded = false;

    private static String apiKey;

    private static String apiSecret;

    private static String callback;

    private static String code;

    private static String refreshToken;

    private static String imageToUpload;

    private static synchronized void loadProperties() throws Exception {
        // Load properties
        Properties properties = new Properties();
        String propFileName = "imgur.properties";

        InputStream stream = ImgurPropertiesHelper.class.getClassLoader().getResourceAsStream(propFileName);
        if (stream == null) {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        properties.load(stream);

        apiKey = properties.getProperty("IMGUR_KEY");
        apiSecret = properties.getProperty("IMGUR_SECRET");
        callback = properties.getProperty("IMGUR_CALLBACK");
        code = properties.getProperty("IMGUR_CODE");
        refreshToken = properties.getProperty("IMGUR_REFRESH_TOKEN");
        imageToUpload = properties.getProperty("IMGUR_TEST_IMAGE");
        loaded = true;
    }

    static String getApiKey() throws Exception {
        if (!loaded) {
            loadProperties();
        }
        return apiKey;
    }

    static String getApiSecret() throws Exception {
        if (!loaded) {
            loadProperties();
        }
        return apiSecret;
    }

    static String getCallback() throws Exception {
        if (!loaded) {
            loadProperties();
        }
        return callback;
    }

    static String getCode() throws Exception {
        if (!loaded) {
            loadProperties();
        }
        return code;
    }

    static String getRefreshToken() throws Exception {
        if (!loaded) {
            loadProperties();
        }
        return refreshToken;
    }

    static String getImageToUpload() throws Exception {
        if (!loaded) {
            loadProperties();
        }
        return imageToUpload;
    }

}
