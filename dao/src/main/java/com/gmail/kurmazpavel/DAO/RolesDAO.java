package com.gmail.kurmazpavel.DAO;
import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.connection.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RolesDAO extends AbstractDAO implements DaoInterface<Role>{
    @Override
    public Role read(long id) throws SQLException {
        List<Role> all = getAll("where id=" + id);
        if (all.size() > 0)
            return all.get(0);
        else
            return null;
    }

    @Override
    public boolean create(Role role) throws SQLException {
        String sql = String.format(Locale.US,"INSERT INTO `roles`(`Role`)" + "VALUES ('%s')", role.getRole());
        long id = executeUpdate(sql);
        if (id > 0) {
            role.setId(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean update(Role role) throws SQLException {
        String sql = String.format(Locale.US,
                "UPDATE `roles` SET `Role`='%s'  WHERE id=%d",
                role.getRole(), role.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public boolean delete(Role role) throws SQLException {
        String sql = String.format(Locale.US,"DELETE FROM `roles` WHERE id=%d", role.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public List<Role> getAll(String whereAndOrder) throws SQLException {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(Locale.US, "" + "SELECT `ID`, `Role` FROM `roles` %s", whereAndOrder);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong("ID"),
                        resultSet.getString("Role")
                );
                roles.add(role);
            }
        }
        return roles;
    }
}
