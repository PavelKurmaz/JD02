package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.ListService;
import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListServiceImpl implements ListService {

    private DAO dao = DAO.getDao();

    @Override
    public boolean create (ShippingList list) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.shippingList.create(list, connection);
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

    public boolean update (ShippingList list) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.shippingList.update(list, connection);
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

    public boolean delete (ShippingList list) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.shippingList.delete(list, connection);
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
    public List<ShippingList> getAll(String where) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<ShippingList> list = dao.shippingList.getAll(where, connection);
                connection.commit();
                return list;
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
