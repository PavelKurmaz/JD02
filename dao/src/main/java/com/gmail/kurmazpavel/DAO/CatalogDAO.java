package com.gmail.kurmazpavel.DAO;
import com.gmail.kurmazpavel.beans.Catalog;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CatalogDAO extends AbstractDAO implements DaoInterface<Catalog> {
    @Override
    public Catalog read(long id, Connection connection) throws SQLException {
        List<Catalog> all = getAll("where id=" + id, connection);
        if (all.size() > 0)
            return all.get(0);
        else
            return null;
    }

    @Override
    public boolean create(Catalog cat, Connection connection) throws SQLException {
        String sql = String.format(Locale.US,"INSERT INTO `catalog`(`AmountLeft`, `Name`, `Price`)" +
                        "VALUES ('%d','%s', %f)",
                cat.getAmount(), cat.getName(), cat.getPrice());
        long id = executeUpdate(sql, connection);
        if (id > 0) {
            cat.setID(id);
            return true;
        } else
            return false;
    }

    @Override
    public boolean update(Catalog cat, Connection connection) throws SQLException {
        String sql = String.format(Locale.US,
                "UPDATE `catalog` SET `AmountLeft`=%d, `Name`='%s', `Price`=%f WHERE id=%d",
                cat.getAmount(), cat.getName(), cat.getPrice(), cat.getID());
        return (executeUpdate(sql, connection)> 0);
    }

    @Override
    public boolean delete(Catalog cat, Connection connection) throws SQLException {
        String sql = String.format(Locale.US,"DELETE FROM `catalog` WHERE id=%d", cat.getID());
        return (executeUpdate(sql, connection) > 0);
    }

    @Override
    public List<Catalog> getAll(String whereAndOrder, Connection connection) throws SQLException {
        List<Catalog> catalogs = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(Locale.US, "" + "SELECT `ID`, `AmountLeft`, `Name`, `Price` FROM `catalog` %s", whereAndOrder);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Catalog catalog = new Catalog(
                        resultSet.getLong("ID"),
                        resultSet.getInt("AmountLeft"),
                        resultSet.getString("Name"),
                        resultSet.getDouble("Price")
                );
                catalogs.add(catalog);
            }
        }
        return catalogs;
    }
}
