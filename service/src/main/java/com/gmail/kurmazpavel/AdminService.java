package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.Admin;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {

    boolean create(Admin admin) throws SQLException;

    boolean update(Admin admin) throws SQLException;

    boolean delete(Admin admin) throws SQLException;

    List<Admin> getAll(String where) throws SQLException;
}
