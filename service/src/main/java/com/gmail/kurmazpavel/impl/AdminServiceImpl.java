package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.AdminService;
import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private DAO dao = DAO.getDao();

    @Override
    public boolean create (Admin admin) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.admin.create(admin, connection);
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

    public boolean update (Admin admin) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.admin.update(admin, connection);
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

    public boolean delete (Admin admin) throws SQLException {
        Connection connection = dbConnection.getInstance().getConnection();
        try {
                connection.setAutoCommit(false);
                boolean created = dao.admin.delete(admin, connection);
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
        return false;
    }

    @Override
    public List<Admin> getAll(String where) throws SQLException {
        Connection connection = dbConnection.getInstance().getConnection();
            try {
                connection.setAutoCommit(false);
                List<Admin> admins = dao.admin.getAll(where, connection);
                connection.commit();
                return admins;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        return new ArrayList<>();
    }
}
