package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.Address;
import java.sql.SQLException;
import java.util.List;

public interface AddressService {

    boolean create(Address address) throws SQLException;

    boolean update(Address address) throws SQLException;

    boolean delete(Address address) throws SQLException;

    List<Address> getAll(String where) throws SQLException;
}
