package com.gmail.kurmazpavel.DAO;
import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.connection.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderDAO extends AbstractDAO implements DaoInterface<Order> {

    @Override
    public Order read(long id) throws SQLException {
        List<Order> all = getAll("where id=" + id);
        if (all.size() > 0)
            return all.get(0);
        else
            return null;
    }

    @Override
    public boolean create(Order order) throws SQLException {
        String sql = String.format(Locale.US,"INSERT INTO `orders`(`Completed`, `Users_ID`)" +
                        "VALUES ('%d','%d')",
                order.getCompleted(), order.getUsers_ID());
        long id = executeUpdate(sql);
        if (id > 0) {
            order.setId(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean update(Order order) throws SQLException {
        String sql = String.format(Locale.US,
                "UPDATE `orders` SET `Completed`=%d, `Users_ID`=%d WHERE id=%d",
                order.getCompleted(), order.getUsers_ID(), order.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public boolean delete(Order order) throws SQLException {
        String sql = String.format(Locale.US,"DELETE FROM `orders` WHERE id=%d", order.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public List<Order> getAll(String whereAndOrder) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = String.format(Locale.US, "" + "SELECT `ID`, `Completed`, `Users_ID` FROM `orders` %s", whereAndOrder);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getLong("ID"),
                        resultSet.getInt("Completed"),
                        resultSet.getInt("Users_ID")
                );
                orders.add(order);
            }
        }
        return orders;
    }
}
