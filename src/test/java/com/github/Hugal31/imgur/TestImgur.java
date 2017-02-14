package com.github.Hugal31.imgur;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class TestImgur {

    private String IMGUR_KEY;

    private String IMGUR_SECRET;

    public TestImgur() throws Exception {
        // Load properties
        Properties properties = new Properties();
        String propFileName = "imgur.properties";

        InputStream stream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (stream == null) {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        properties.load(stream);

        IMGUR_KEY = properties.getProperty("IMGUR_KEY");
        IMGUR_SECRET = properties.getProperty("IMGUR_SECRET");
    }
}
