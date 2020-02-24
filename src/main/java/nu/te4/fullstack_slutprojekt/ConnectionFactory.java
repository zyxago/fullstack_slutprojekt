package nu.te4.fullstack_slutprojekt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author erikh
 */
public class ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    public Connection getConnection() {
        String db = "erikh-recept";
        String user = "erikh";
        String password = "2mphZjQH";
        try {
            return DriverManager.getConnection(String.format("jdbc:mysql://its.teknikum.it/%s?user=%s&password=%s", db, user, password));
        } catch (SQLException e) {
            LOGGER.error("Error in ConnectionFactory:getConnection: " + e.getMessage());
            return null;
        }
    }
}
