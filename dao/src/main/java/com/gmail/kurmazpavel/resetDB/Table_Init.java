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
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
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
                    "  `Roles_ID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `fk_Users_Roles_idx` (`Roles_ID` ASC),\n" +
                    "  CONSTRAINT `fk_Users_Roles`\n" +
                    "    FOREIGN KEY (`Roles_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Roles` (`ID`)\n" +
                    "    ON DELETE RESTRICT\n" +
                    "    ON UPDATE RESTRICT)\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Address` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Country` VARCHAR(45) NULL,\n" +
                    "  `City` VARCHAR(45) NULL,\n" +
                    "  `Street` VARCHAR(45) NULL,\n" +
                    "  `Building` VARCHAR(45) NULL,\n" +
                    "  `Apt` VARCHAR(45) NULL,\n" +
                    "  `ZIP` VARCHAR(45) NULL,\n" +
                    "  `Users_ID` BIGINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `fk_Address_Users1_idx` (`Users_ID` ASC),\n" +
                    "  CONSTRAINT `fk_Address_Users1`\n" +
                    "    FOREIGN KEY (`Users_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Users` (`ID`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE RESTRICT)\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Audit` (\n" +
                    "  `ID` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Users_ID` BIGINT NOT NULL,\n" +
                    "  `EVENT_TYPE` VARCHAR(20) NULL,\n" +
                    "  `CREATED` DATETIME NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `fk_Audit_Users1_idx` (`Users_ID` ASC),\n" +
                    "  CONSTRAINT `fk_Audit_Users1`\n" +
                    "    FOREIGN KEY (`Users_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Users` (`ID`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE RESTRICT)\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Admins` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Login` VARCHAR(45) NULL,\n" +
                    "  `Password` VARCHAR(45) NULL,\n" +
                    "  `Email` VARCHAR(45) NULL,\n" +
                    "  `Phone` VARCHAR(45) NULL,\n" +
                    "  `Roles_ID` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `fk_Admins_Roles1_idx` (`Roles_ID` ASC),\n" +
                    "  CONSTRAINT `fk_Admins_Roles1`\n" +
                    "    FOREIGN KEY (`Roles_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Roles` (`ID`)\n" +
                    "    ON DELETE RESTRICT\n" +
                    "    ON UPDATE RESTRICT)\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Orders` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Completed` TINYINT(1) NULL,\n" +
                    "  `Users_ID` BIGINT NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `fk_Order_Users1_idx` (`Users_ID` ASC),\n" +
                    "  CONSTRAINT `fk_Order_Users1`\n" +
                    "    FOREIGN KEY (`Users_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Users` (`ID`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE NO ACTION)\n" +
                    "ENGINE = InnoDB;\n");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`Catalog` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `AmountLeft` INT NULL,\n" +
                    "  `Name` VARCHAR(45) NULL,\n" +
                    "  `Price` DOUBLE NULL,\n" +
                    "  PRIMARY KEY (`ID`))\n" +
                    "ENGINE = InnoDB;");
            statement.execute("CREATE TABLE IF NOT EXISTS `kurmaz`.`ShippingList` (\n" +
                    "  `ID` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `Quantity` VARCHAR(45) NULL,\n" +
                    "  `Catalog_ID` INT NULL,\n" +
                    "  `Orders_ID` INT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  INDEX `fk_ShippingList_Catalog1_idx` (`Catalog_ID` ASC),\n" +
                    "  INDEX `fk_ShippingList_Orders1_idx` (`Orders_ID` ASC),\n" +
                    "  CONSTRAINT `fk_ShippingList_Catalog1`\n" +
                    "    FOREIGN KEY (`Catalog_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Catalog` (`ID`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `fk_ShippingList_Orders1`\n" +
                    "    FOREIGN KEY (`Orders_ID`)\n" +
                    "    REFERENCES `kurmaz`.`Orders` (`ID`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB;");

        } catch (SQLException e) {
            e.printStackTrace(); }

    }
}
