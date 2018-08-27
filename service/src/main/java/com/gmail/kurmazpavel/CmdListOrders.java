package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

class CmdListOrders extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        if (Util.isPost(req)){
            return new ActionResult(Actions.PROFILE);
        }
        HttpSession session = req.getSession();
        Object object = session.getAttribute("user");
        if (object != null){
            User user = (User) object;
            int user_id = (int) user.getId();
            String where = String.format(Locale.US, "WHERE users_ID='%d'", user_id);
            List<Order> orders = DAO.getDao().order.getAll(where);
            req.setAttribute("orders", orders);
        }
        return null;
    }
}
