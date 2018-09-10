package com.gmail.kurmazpavel.resetDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class Table_Init {

    static void createTabs(Connection connection) {
        try (Statement statement = connection.createStatement())
        {
            statement.execute("DROP SCHEMA IF EXISTS `kurmaz` ;");
            statement.execute("CREATE SCHEMA IF NOT EXISTS `kurmaz` DEFAULT CHARACTER SET utf8 ;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Roles` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Role` VARCHAR(100) NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Users` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Login` VARCHAR(45) NULL,\n" +
                    "  `Password` VARCHAR(45) NULL,\n" +
                    "  `Email` VARCHAR(45) NULL,\n" +
                    "  `Phone` VARCHAR(45) NULL,\n" +
                    "  `Carma` VARCHAR(45) NULL,\n" +
                    "  `Roles_ID` BIGINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Address` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Country` VARCHAR(45) NULL,\n" +
                    "  `City` VARCHAR(45) NULL,\n" +
                    "  `Street` VARCHAR(45) NULL,\n" +
                    "  `Building` VARCHAR(45) NULL,\n" +
                    "  `Apt` VARCHAR(45) NULL,\n" +
                    "  `ZIP` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Audit` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `EVENT_TYPE` VARCHAR(40) NULL,\n" +
                    "  `CREATED` DATETIME NULL,\n" +
                    "  `USER_ID` BIGINT,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Admins` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Login` VARCHAR(45) NULL,\n" +
                    "  `Password` VARCHAR(45) NULL,\n" +
                    "  `Email` VARCHAR(45) NULL,\n" +
                    "  `Phone` VARCHAR(45) NULL,\n" +
                    "  `Roles_ID` BIGINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Orders` (\n" +
                    "  `Item_ID` BIGINT NOT NULL,\n" +
                    "  `User_ID` BIGINT NOT NULL,\n" +
                    "  `CREATED` DATETIME NOT NULL,\n" +
                    "  `Quantity` INT NULL)\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Catalog` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `AmountLeft` BIGINT NULL,\n" +
                    "  `Name` VARCHAR(45) NULL,\n" +
                    "  `Price` DOUBLE NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`News` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Title` VARCHAR(45) NULL,\n" +
                    "  `Content` VARCHAR(100) NULL,\n" +
                    "  `CREATED` DATETIME NOT NULL,\n" +
                    "  `User_Id` BIGINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Comment` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Content` VARCHAR(100) NULL,\n" +
                    "  `CREATED` DATETIME NOT NULL,\n" +
                    "  `User_Id` BIGINT NOT NULL,\n" +
                    "  `News_Id` BIGINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Permission` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Name` VARCHAR(400) NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`role_permission` (\n" +
                    "  `role_id` BIGINT,\n" +
                    "  `permission_id` BIGINT)\n" +
                    "ENGINE = InnoDB;");
        } catch (SQLException e) {
            e.printStackTrace(); }

    }
}
