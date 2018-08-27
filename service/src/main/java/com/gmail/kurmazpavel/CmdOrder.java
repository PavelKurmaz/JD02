package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.beans.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CmdOrder extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
        HttpSession session = req.getSession();
        if (session.getAttribute("admin") != null)
            return new ActionResult(Actions.INDEX);
        if (Util.isPost(req)) {
            Object isUser = session.getAttribute("user");
            List<Catalog> orderList = (List<Catalog>) session.getAttribute("orderList");
            User user = (User) isUser;
            int user_id = (int) user.getId();
            long order_id;
            if (orderList == null) {
                orderList = new ArrayList<Catalog>(){};
                session.setAttribute("orderList", orderList);
            }
            if(req.getParameter("add") != null) {
                int item_id = Util.getInteger(req, "id");
                int amount = Util.getInteger(req, "amount");
                String where = String.format(Locale.US, "WHERE ID='%d'", item_id);
                List<Catalog> items = DAO.getDao().catalog.getAll(where);
                Catalog item = items.get(0);
                Catalog orderItem = new Catalog(item_id, amount, item.getName(), item.getPrice());
                orderList.add(orderItem);
                item.setAmount(item.getAmount() - amount);
                DAO.getDao().catalog.update(item);
            }
            if (req.getParameter("create") != null) {
                Order order = new Order(0, 0, user_id);
                DAO.getDao().order.create(order);
                session.setAttribute("order", order);
                order_id = order.getId();
                for (Catalog catalog : orderList) {
                    ShippingList list = new ShippingList(0, String.valueOf(catalog.getAmount()), (int) catalog.getID(), (int) order_id);
                    DAO.getDao().shippingList.create(list);
                }
                orderList.clear();
                return new ActionResult(Actions.LISTORDERS);
            }
        }
        List<Catalog> items = DAO.getDao().catalog.getAll("");
        req.setAttribute("catalogitems", items);
        return null;
    }
}
