package com.gmail.kurmazpavel.resetDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class Orderlist_Init {
    static void createOrders(Connection connection) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `kurmaz`.`Orders` (`ID`, `Completed`, `Users_ID`) VALUES (DEFAULT, FALSE, 1);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Orders` (`ID`, `Completed`, `Users_ID`) VALUES (DEFAULT, TRUE, 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Orders` (`ID`, `Completed`, `Users_ID`) VALUES (DEFAULT, FALSE, 3);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Orders` (`ID`, `Completed`, `Users_ID`) VALUES (DEFAULT, FALSE, 1);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
