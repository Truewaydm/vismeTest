package ui.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private final String propertiesFile = "/config.properties";
    private final Properties properties;

    private PropertyLoader() {
        properties = new Properties();
        loadPropertiesFromFile();
    }

    private static class PropertyLoaderHolder {
        private static final PropertyLoader INSTANCE = new PropertyLoader();
    }

    public static PropertyLoader getInstance() {
        return PropertyLoaderHolder.INSTANCE;
    }

    private void loadPropertiesFromFile() {
        try {
            properties.load(PropertyLoader.class.getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get properties from " + propertiesFile + e.getMessage());
        }
    }

    public String getBaseUrl() {
        switch (getEnv()) {
            case "releaseProd":
                return get("urlProd");
            case "preProd":
                return get("urlPreProd");
            default:
                return get("Can't find properties form " + propertiesFile);
        }
    }

    public String getEnv() {
        return get("environment");
    }

    public String getHeadlessType() {
        return get("headless");
    }

    public String get(String key) {
        String value = System.getProperty(key, System.getenv(key));
        return value == null || value.equals("") ? properties.getProperty(key) : value;
    }
}
