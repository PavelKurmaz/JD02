package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.ListService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.beans.Order;
import com.gmail.kurmazpavel.beans.ShippingList;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import com.gmail.kurmazpavel.impl.ListServiceImpl;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CmdOrder extends Cmd {
    private OrderService orderService = new OrderServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();
    private ListService listService = new ListServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
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
                List<Catalog> items =  catalogService.getAll(where);
                Catalog item = items.get(0);
                Catalog orderItem = new Catalog(item_id, amount, item.getName(), item.getPrice());
                orderList.add(orderItem);
                item.setAmount(item.getAmount() - amount);
                catalogService.update(item);
            }
            if (req.getParameter("create") != null) {
                Order order = new Order(0, 0, user_id);
                orderService.create(order);
                session.setAttribute("order", order);
                order_id = order.getId();
                for (Catalog catalog : orderList) {
                    ShippingList list = new ShippingList(0, String.valueOf(catalog.getAmount()), (int) catalog.getID(), (int) order_id);
                    listService.create(list);
                }
                orderList.clear();
                return new ActionResult(Actions.LISTORDERS);
            }
        }
        List<Catalog> items = catalogService.getAll("");
        req.setAttribute("catalogitems", items);
        return null;
    }
}
