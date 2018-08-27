package com.gmail.kurmazpavel.connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class dbConnection {

    static {
        final Logger logger = LogManager.getLogger(dbConnection.class);
        try {
        Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Error loading driver: ", e);
        }
    }

    private dbConnection() {}
    private static final String dbURL = "jdbc:mysql://127.0.0.1:3306/kurmaz" + "?useUnicode=true&characterEncoding=UTF-8" + "&useLegacyDatetimeCode=false&serverTimezone=Europe/Minsk";
    private static final String dbUser = "root";
    private static final String dbPassword = "root";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (dbConnection.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                }
            }
        }
        return connection;
    }
}
