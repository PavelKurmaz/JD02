package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceImpl implements CatalogService {

    private DAO dao = DAO.getDao();

    @Override
    public boolean create (Catalog catalog) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.catalog.create(catalog, connection);
                connection.commit();
                return created;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean update (Catalog catalog) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.catalog.update(catalog, connection);
                connection.commit();
                return created;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean delete (Catalog catalog) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.catalog.delete(catalog, connection);
                connection.commit();
                return created;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<Catalog> getAll(String where) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Catalog> catalogs = dao.catalog.getAll(where, connection);
                connection.commit();
                return catalogs;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }
}
