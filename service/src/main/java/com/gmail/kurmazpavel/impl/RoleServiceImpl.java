package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.RoleService;
import com.gmail.kurmazpavel.beans.Role;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private DAO dao = DAO.getDao();

    @Override
    public boolean create (Role role) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.roles.create(role, connection);
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

    public boolean update (Role role) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.roles.update(role, connection);
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

    public boolean delete (Role role) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.roles.delete(role, connection);
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
    public List<Role> getAll(String where) throws SQLException {
        try (Connection connection = dbConnection.getInstance().getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Role> roles = dao.roles.getAll(where, connection);
                connection.commit();
                return roles;
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
