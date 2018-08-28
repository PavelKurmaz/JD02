package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean create (User user) throws SQLException;

    boolean update (User user) throws SQLException;

    boolean delete (User user) throws SQLException;

    List<User> getAll(String where) throws SQLException;
}
