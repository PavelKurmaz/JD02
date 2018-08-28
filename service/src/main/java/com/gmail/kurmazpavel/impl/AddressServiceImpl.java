package com.gmail.kurmazpavel.impl;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.connection.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private DAO dao = DAO.getDao();

    @Override
    public boolean create (Address address) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.address.create(address, connection);
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

    public boolean update (Address address) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.address.update(address, connection);
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

    public boolean delete (Address address) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                boolean created = dao.address.delete(address, connection);
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
    public List<Address> getAll(String where) throws SQLException {
        try (Connection connection = dbConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Address> address = dao.address.getAll(where, connection);
                connection.commit();
                return address;
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
