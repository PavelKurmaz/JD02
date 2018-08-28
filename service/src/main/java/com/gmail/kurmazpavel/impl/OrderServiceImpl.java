package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private DAO dao = DAO.getDao();

    @Override
    public boolean create (Order order) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.order.create(order, connection);
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

    public boolean update (Order order) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.order.update(order, connection);
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

    public boolean delete (Order order) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.order.delete(order, connection);
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
    public List<Order> getAll(String where) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Order> orders = dao.order.getAll(where, connection);
                connection.commit();
                return orders;
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
