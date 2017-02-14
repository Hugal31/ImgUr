package com.github.Hugal31.imgur;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

class ImgurPropertiesHelper {

    private static boolean loaded = false;

    private static String apiKey;

    private static String apiSecret;

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

}
