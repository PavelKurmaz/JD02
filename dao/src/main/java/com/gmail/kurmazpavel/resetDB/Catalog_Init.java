package com.gmail.kurmazpavel.resetDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class Catalog_Init {
    static void createCatalog(Connection connection) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `kurmaz`.`Catalog` (`ID`, `AmountLeft`, `Name`, `Price`) VALUES (DEFAULT, 50, 'Vodka', 5);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Catalog` (`ID`, `AmountLeft`, `Name`, `Price`) VALUES (DEFAULT, 40, 'Whiskey', 10);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Catalog` (`ID`, `AmountLeft`, `Name`, `Price`) VALUES (DEFAULT, 30, 'Cognac', 25);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Catalog` (`ID`, `AmountLeft`, `Name`, `Price`) VALUES (DEFAULT, 25, 'Tequila', 20);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Catalog` (`ID`, `AmountLeft`, `Name`, `Price`) VALUES (DEFAULT, 15, 'Baileys', 15);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
