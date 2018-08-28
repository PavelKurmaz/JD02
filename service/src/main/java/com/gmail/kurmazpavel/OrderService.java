package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    boolean create(Order order) throws SQLException;

    boolean update(Order order) throws SQLException;

    boolean delete(Order order) throws SQLException;

    List<Order> getAll(String where) throws SQLException;
}
