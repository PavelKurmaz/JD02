package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.ShippingList;
import java.sql.SQLException;
import java.util.List;

public interface ListService {

    boolean create(ShippingList list) throws SQLException;

    boolean update(ShippingList list) throws SQLException;

    boolean delete(ShippingList list) throws SQLException;

    List<ShippingList> getAll(String where) throws SQLException;
}
