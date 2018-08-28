package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.ListService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.*;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import com.gmail.kurmazpavel.impl.ListServiceImpl;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CmdShippingList extends Cmd {
    private ListService listService = new ListServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object isUser = session.getAttribute("user");
        Object isAdmin = session.getAttribute("admin");
        String where;
        if (isUser == null && isAdmin == null)
            return new ActionResult(Actions.INDEX);
        else if (isAdmin != null)
            where = "WHERE Completed = 0";
        else {
            User user = (User) isUser;
            int user_ID = (int) user.getId();
            where = String.format(Locale.US, "WHERE Completed = 0 AND Users_ID='%d'", user_ID);
            }
        List<Order> orderList = orderService.getAll(where);
        List<ShippingItem> itemList = new ArrayList<>();
        for (Order order: orderList) {
            int order_id = (int) order.getId();
            where = String.format(Locale.US, "WHERE Orders_ID='%d'", order_id);
            List<ShippingList> list = listService.getAll(where);
            for (ShippingList ship : list) {
                int catalogID = ship.getCatalog_ID();
                where = String.format(Locale.US, "WHERE ID='%d'", catalogID);
                List<Catalog> catalogList = catalogService.getAll(where);
                Catalog catalogItem = catalogList.get(0);
                ShippingItem item = new ShippingItem(catalogItem.getName(), Integer.parseInt(ship.getQuantity()), catalogItem.getPrice(), ship.getOrder_ID());
                itemList.add(item);
            }
        }
        req.setAttribute("itemlist", itemList);
        return new ActionResult("getshippinglist");

    }
}
