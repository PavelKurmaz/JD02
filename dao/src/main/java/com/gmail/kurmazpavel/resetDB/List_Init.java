package com.gmail.kurmazpavel.resetDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class List_Init {
    static void shippingListInit(Connection connection) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `kurmaz`.`ShippingList` (`ID`, `Quantity`, `Catalog_ID`, `Orders_ID`) VALUES (DEFAULT, '2', 1, 1);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`ShippingList` (`ID`, `Quantity`, `Catalog_ID`, `Orders_ID`) VALUES (DEFAULT, '1', 2, 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`ShippingList` (`ID`, `Quantity`, `Catalog_ID`, `Orders_ID`) VALUES (DEFAULT, '3', 3, 3);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`ShippingList` (`ID`, `Quantity`, `Catalog_ID`, `Orders_ID`) VALUES (DEFAULT, '2', 4, 4);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`ShippingList` (`ID`, `Quantity`, `Catalog_ID`, `Orders_ID`) VALUES (DEFAULT, '1', 5, 2);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
