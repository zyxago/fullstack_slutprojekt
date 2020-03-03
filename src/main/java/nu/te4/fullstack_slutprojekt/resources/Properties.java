package nu.te4.fullstack_slutprojekt.resources;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erik
 */
public class Properties {

    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);

    private Properties(){}

    public static Properties properties = new Properties();

    public enum PropKeys {
        DB_HOST("db_host"),
        DB_PASS("db_password"),
        DB_USERNAME("db_username"),
        DB_SCHEMA("db_schema");

        private String stringValue;

        PropKeys(String value) {
            this.stringValue = value;
        }
    }

    public String getProperty(PropKeys key) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = classLoader.getResourceAsStream("config.properties");
        java.util.Properties properties = new java.util.Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error("Error in PropertiesBean:getProperty: " + e.getMessage());
        }
        return properties.getProperty(key.stringValue);
    }
}
