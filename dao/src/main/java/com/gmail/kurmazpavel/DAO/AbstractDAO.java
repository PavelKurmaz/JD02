package com.gmail.kurmazpavel.DAO;

import com.gmail.kurmazpavel.connection.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class AbstractDAO{
    long executeUpdate(String sql) throws SQLException {
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            if (sql.trim().toUpperCase().startsWith("INSERT")) {
                if (1 == statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            } else
                return statement.executeUpdate(sql);
        }
        return 0;
    }
}
