package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.beans.Catalog;
import java.sql.SQLException;
import java.util.List;

public interface CatalogService {

    boolean create(Catalog catalog) throws SQLException;

    boolean update(Catalog catalog) throws SQLException;

    boolean delete(Catalog catalog) throws SQLException;

    List<Catalog> getAll(String where) throws SQLException;
}
