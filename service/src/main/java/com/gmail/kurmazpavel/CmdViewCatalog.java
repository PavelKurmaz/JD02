package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Catalog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

class CmdViewCatalog extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        List<Catalog> items = DAO.getDao().catalog.getAll("");
        session.setAttribute("catalogItems", items);
        return new ActionResult("viewcatalog");
    }
}