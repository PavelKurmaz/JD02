package com.gmail.kurmazpavel.resetDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class User_Inserts {
    static void userInserts(Connection connection) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO `kurmaz`.`Roles` (`ID`, `Role`) VALUES (DEFAULT, 'Admin');");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Roles` (`ID`, `Role`) VALUES (DEFAULT, 'User');");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Users` (`ID`, `Login`, `Password`, `Email`, `Phone`, `Carma`, `Roles_ID`) VALUES (DEFAULT, 'Pavel', '12345', 'pavel@it.by', '+375291111111', 'regular', 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Users` (`ID`, `Login`, `Password`, `Email`, `Phone`, `Carma`, `Roles_ID`) VALUES (DEFAULT, 'Stepan', '54321', 'stepan@it.by', '+375443333333', 'weak', 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Users` (`ID`, `Login`, `Password`, `Email`, `Phone`, `Carma`, `Roles_ID`) VALUES (DEFAULT, 'Afanasiy', '987654321', 'afonya@it.by', '+375449999999', 'regular', 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Users` (`ID`, `Login`, `Password`, `Email`, `Phone`, `Carma`, `Roles_ID`) VALUES (DEFAULT, 'Caesar', '0000000', 'gjc@it.by', '+375255555555', 'silver', 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Address` (`ID`, `Country`, `City`, `Street`, `Building`, `Apt`, `ZIP`, `Users_ID`) VALUES (DEFAULT, 'Belarus', 'Minsk', 'Nezavisimosti', '10', '2', '2200099', 1);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Address` (`ID`, `Country`, `City`, `Street`, `Building`, `Apt`, `ZIP`, `Users_ID`) VALUES (DEFAULT, 'Belarus', 'Gomel', 'Minskaya', '2', '5', '2230909', 2);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Address` (`ID`, `Country`, `City`, `Street`, `Building`, `Apt`, `ZIP`, `Users_ID`) VALUES (DEFAULT, 'Belarus', 'Brest', 'Brestskaya', '105', '310', '2290909', 3);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Address` (`ID`, `Country`, `City`, `Street`, `Building`, `Apt`, `ZIP`, `Users_ID`) VALUES (DEFAULT, 'Italy', 'Rome', 'Unknown', '1', '1', '3333333', 4);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Admins` (`ID`, `Login`, `Password`, `Email`, `Phone`, `Roles_ID`) VALUES (DEFAULT, 'Admin1', 'admin1', 'admin1@it.by', '+375298888888', 1);");
            statement.executeUpdate("INSERT INTO `kurmaz`.`Admins` (`ID`, `Login`, `Password`, `Email`, `Phone`, `Roles_ID`) VALUES (DEFAULT, 'Admin2', 'admin2', 'admin2@it.by', '+375297777777', 1);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
