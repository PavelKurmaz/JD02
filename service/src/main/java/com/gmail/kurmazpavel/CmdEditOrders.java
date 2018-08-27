package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class CmdEditOrders extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DAO dao = DAO.getDao();
        if (Util.isPost(req)) {
            int order_id = Util.getInteger(req, "id");
            int completed = Util.getInteger(req, "completed");
            int user_id = Util.getInteger(req, "users_id");
            Order order= new Order(order_id, completed, user_id);
            if (req.getParameter("Update") != null) {
                dao.order.update(order);
            } else if (req.getParameter("Delete") != null) {
                dao.order.delete(order);
            }
        }
        List<Order> orders = dao.order.getAll("");
        req.setAttribute("orders", orders);
        return null;
    }
}
