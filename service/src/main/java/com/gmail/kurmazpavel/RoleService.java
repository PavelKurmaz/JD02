package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.Role;
import java.sql.SQLException;
import java.util.List;

public interface RoleService {

    boolean create(Role role) throws SQLException;

    boolean update(Role role) throws SQLException;

    boolean delete(Role role) throws SQLException;

    List<Role> getAll(String where) throws SQLException;
}
