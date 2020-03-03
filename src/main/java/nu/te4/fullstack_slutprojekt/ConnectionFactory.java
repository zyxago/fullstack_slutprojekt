package nu.te4.fullstack_slutprojekt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static nu.te4.fullstack_slutprojekt.resources.Properties.properties;
import static nu.te4.fullstack_slutprojekt.resources.Properties.PropKeys;

/**
 * @author erikh
 */
public class ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    public Connection getConnection() {
        String host = properties.getProperty(PropKeys.DB_HOST);
        String schema = properties.getProperty(PropKeys.DB_SCHEMA);
        String user = properties.getProperty(PropKeys.DB_USERNAME);
        String password = properties.getProperty(PropKeys.DB_PASS);
        try {
            return DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s", host, schema, user, password));
        } catch (SQLException e) {
            LOGGER.error("Error in ConnectionFactory:getConnection: " + e.getMessage());
            return null;
        }
    }
}
