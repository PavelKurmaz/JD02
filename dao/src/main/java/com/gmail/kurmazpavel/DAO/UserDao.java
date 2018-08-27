package com.gmail.kurmazpavel.DAO;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.connection.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDao extends AbstractDAO  implements DaoInterface<User>{

    @Override
    public User read(long id) throws SQLException {
        List<User> all = getAll("where id=" + id);
        if (all.size() > 0)
            return all.get(0);
        else
            return null;
    }

    @Override
    public boolean create(User user) throws SQLException {
        String sql = String.format(Locale.US,"INSERT INTO `users`(`Login`, `Password`, `Email`, `Phone`, `Carma`, `Roles_ID`)" +
                        "VALUES ('%s','%s','%s','%s','%s', %d)",
                user.getLogin(), user.getPassword(), user.getEmail(), user.getPhone(), user.getCarma(), user.getRoles_id());
        long id = executeUpdate(sql);
        if (id > 0) {
            user.setId(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = String.format(Locale.US,
                "UPDATE `users` SET `Login`='%s', `Password`='%s', `Email`='%s', `Phone`='%s', `Carma`='%s', `Roles_ID`='%d' WHERE id=%d",
                user.getLogin(), user.getPassword(), user.getEmail(), user.getPhone(), user.getCarma(), user.getRoles_id(), user.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public boolean delete(User user) throws SQLException {
        String sql = String.format(Locale.US,"DELETE FROM `users` WHERE id=%d", user.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public List<User> getAll(String whereAndOrder) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(Locale.US, "" +
                    "SELECT `ID`, `Login`, `Password`, `Email`, `Phone`, `Carma`, `Roles_ID` FROM `users` %s",
                    whereAndOrder);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("Login"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Carma"),
                        resultSet.getLong("Roles_ID")
                );
                users.add(user);
            }
        }
        return users;
    }
}
