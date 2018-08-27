package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Catalog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class CmdEditCatalog extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DAO dao = DAO.getDao();
        if (Util.isPost(req)) {
            int id = Util.getInteger(req, "id");
            int amount = Util.getInteger(req, "amount");
            double price = Util.getDouble(req, "price");
            String name = Util.getString(req, "name");
            Catalog catalog = new Catalog(id, amount, name, price);
            if (req.getParameter("Update") != null) {
                dao.catalog.update(catalog);
            } else if (req.getParameter("Delete") != null) {
                dao.catalog.delete(catalog);
            }
        }
        List<Catalog> items = dao.catalog.getAll("");
        req.setAttribute("items", items);
        return null;
    }
}
