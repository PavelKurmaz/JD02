package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.ListService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.ShippingItem;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.beans.dto.ShippingListDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
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

class CmdShippingList extends Cmd {
    private ListService listService = new ListServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object isUser = session.getAttribute("user");
        Object isAdmin = session.getAttribute("admin");
        int user_id;
        List<OrderDTO> allOrderList = orderService.getAll();
        List<OrderDTO> orderList = new ArrayList<>();
        if (isUser == null && isAdmin == null)
            return new ActionResult(Actions.INDEX);
        else if (isUser != null) {
            UserDTO user = (UserDTO) isUser;
            user_id = (int) user.getId();
            for (OrderDTO order: allOrderList) {
                if ((order.getCompleted() == 0) && (order.getUsers_ID()== user_id))
                    orderList.add(order);
            }
        }
        else {
            for (OrderDTO order : allOrderList) {
                if (order.getCompleted() == 0)
                    orderList.add(order);
            }
        }
        List<ShippingItem> itemList = new ArrayList<>();
        for (OrderDTO order: orderList) {
            long order_id = order.getId();
            List<ShippingListDTO> allList = listService.getAll();
            List<ShippingListDTO> list = new ArrayList<>();
            for (ShippingListDTO item: allList) {
                if (item.getOrder_ID()== order_id)
                    list.add(item);
            }
            for (ShippingListDTO ship : list) {
                Long catalogID = ship.getCatalog_ID();
                CatalogDTO catalogItem = catalogService.read(catalogID);
                ShippingItem item = new ShippingItem(catalogItem.getName(), Integer.parseInt(ship.getQuantity()), catalogItem.getPrice(), ship.getOrder_ID());
                itemList.add(item);
            }
        }
        req.setAttribute("itemlist", itemList);
        return new ActionResult("getshippinglist");

    }
}
