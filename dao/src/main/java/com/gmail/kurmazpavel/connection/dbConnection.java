package com.gmail.kurmazpavel.connection;
import com.gmail.kurmazpavel.config.ConfigurationManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class dbConnection {

    private static dbConnection instance;

    private Connection connection;

    private dbConnection() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        try {
            Class.forName(configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static dbConnection getInstance() {
        if (instance == null) {
            instance = new dbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configurationManager.getProperty(ConfigurationManager.DATABASE_USERNAME));
            properties.setProperty("password", configurationManager.getProperty(ConfigurationManager.DATABASE_PWD));
            connection = DriverManager.getConnection(
                    configurationManager.getProperty(ConfigurationManager.DATABASE_URL),
                    properties
            );
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
