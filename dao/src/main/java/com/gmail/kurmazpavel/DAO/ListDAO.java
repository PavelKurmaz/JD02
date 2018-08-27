package com.gmail.kurmazpavel.DAO;

import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListDAO extends AbstractDAO implements DaoInterface<ShippingList> {

    @Override
    public ShippingList read(long id) throws SQLException {
        List<ShippingList> all = getAll("where id=" + id);
        if (all.size() > 0)
            return all.get(0);
        else
            return null;
    }

    @Override
    public boolean create(ShippingList list) throws SQLException {
        String sql = String.format(Locale.US,"INSERT INTO `shippinglist`(`Quantity`, `Catalog_ID`, `Orders_ID`)" +
                        "VALUES ('%s','%d', %d)",
                list.getQuantity(), list.getCatalog_ID(), list.getOrder_ID());
        long id = executeUpdate(sql);
        if (id > 0) {
            list.setId(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean update(ShippingList list) throws SQLException {
        String sql = String.format(Locale.US,
                "UPDATE `shippinglist` SET `Quantity`=%s, `Catalog_ID`=%d, `Orders_ID`=%d WHERE id=%d",
                list.getQuantity(), list.getCatalog_ID(), list.getOrder_ID(), list.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public boolean delete(ShippingList list) throws SQLException {
        String sql = String.format(Locale.US,"DELETE FROM `shippinglist` WHERE id=%d", list.getId());
        return (executeUpdate(sql) > 0);
    }

    @Override
    public List<ShippingList> getAll(String whereAndOrder) throws SQLException {
        List<ShippingList> lists = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(Locale.US, "" + "SELECT `ID`, `Quantity`, `Catalog_ID`, `Orders_ID` FROM `shippinglist` %s", whereAndOrder);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ShippingList list = new ShippingList(
                        resultSet.getLong("ID"),
                        resultSet.getString("Quantity"),
                        resultSet.getInt("Catalog_ID"),
                        resultSet.getInt("Orders_ID")
                );
                lists.add(list);
            }
        }
        return lists;
    }
}
