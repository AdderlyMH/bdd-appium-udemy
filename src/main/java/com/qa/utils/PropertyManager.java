package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static Properties properties = new Properties();
    TestUtils testUtils = new TestUtils();

    public Properties getProperties() {

        if (properties.isEmpty()) {
            try (InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream("config.properties")) {
                properties.load(is);
                testUtils.logger().info("Loaded properties file");
            } catch (IOException e) {
                testUtils.logger().fatal("Properties loading failed. ABORT! : {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return properties;

    }
}
