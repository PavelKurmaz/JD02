package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Catalog;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

class CmdAddItem extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        if (object == null)
            return new ActionResult(Actions.ADMLOGIN);
        if (Util.isPost(req)) {
            String name = Util.getString(req,"name");
            double price = Util.getDouble(req, "price");
            int amount = Util.getInteger(req, "amount");
            if (name != null) {
                Catalog item = new Catalog(0, amount, name, price);
                DAO.getDao().catalog.create(item);
                if (item.getID() > 0)
                    return new ActionResult("addcatalogitem");
            }
        }
        return null;
    }
}
