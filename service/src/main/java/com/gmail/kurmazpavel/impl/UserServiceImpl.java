package com.gmail.kurmazpavel.impl;


import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private DAO userDao = DAO.getDao();

    @Override
    public boolean create (User user) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = userDao.user.create(user, connection);
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

    public boolean update (User user) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = userDao.user.update(user, connection);
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

    public boolean delete (User user) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = userDao.user.delete(user, connection);
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
    public List<User> getAll(String where) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<User> users = userDao.user.getAll(where, connection);
                connection.commit();
                return users;
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
