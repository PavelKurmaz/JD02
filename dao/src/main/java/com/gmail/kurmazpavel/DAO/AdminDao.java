package com.gmail.kurmazpavel.DAO;
import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminDao extends AbstractDAO implements DaoInterface<Admin> {

    @Override
    public Admin read(long id) throws SQLException {
        List<Admin> all = getAll("where id=" + id);
        if (all.size() > 0)
            return all.get(0);
        else
            return null;
    }

    @Override
    public boolean create(Admin admin) throws SQLException {
        String sql = String.format(Locale.US,"INSERT INTO `admins`(`Login`, `Password`, `Email`, `Phone`, `Roles_ID`)" +
                        "VALUES ('%s','%s','%s','%s', %d)",
                admin.getLogin(), admin.getPassword(), admin.getEmail(), admin.getPhone(), admin.getRoles_id());
        long id = executeUpdate(sql);
        if (id > 0) {
            admin.setId(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean update(Admin admin) throws SQLException {
        String sql = String.format(Locale.US,
                "UPDATE `admins` SET `Login`='%s', `Password`='%s', `Email`='%s', `Phone`='%s', `Roles_ID`='%d' WHERE id=%d",
                admin.getLogin(), admin.getPassword(), admin.getEmail(), admin.getPhone(), admin.getRoles_id(), admin.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public boolean delete(Admin admin) throws SQLException {
        String sql = String.format(Locale.US,"DELETE FROM `admins` WHERE id=%d", admin.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public List<Admin> getAll(String whereAndOrder) throws SQLException {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(Locale.US, "" +
                            "SELECT `ID`, `Login`, `Password`, `Email`, `Phone`, `Roles_ID` FROM `admins` %s",
                    whereAndOrder);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getLong("ID"),
                        resultSet.getString("Login"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getLong("Roles_ID")
                );
                admins.add(admin);
            }
        }
        return admins;
    }
}
