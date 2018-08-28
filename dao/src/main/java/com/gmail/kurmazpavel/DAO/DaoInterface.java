package com.gmail.kurmazpavel.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<Bean> {
    Bean read(long id, Connection connection) throws SQLException;
    boolean create(Bean bean, Connection connection) throws SQLException;
    boolean update(Bean bean, Connection connection) throws SQLException;
    boolean delete(Bean bean, Connection connection) throws SQLException;
    List<Bean> getAll(String whereAndOrder, Connection connection) throws SQLException;
}
