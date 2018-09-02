package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.ListService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.beans.dto.ShippingListDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
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
            List<CatalogDTO> orderList = (List<CatalogDTO>) session.getAttribute("orderList");
            UserDTO user = (UserDTO) isUser;
            int user_id = (int) user.getId();
            long order_id;
            if (orderList == null) {
                orderList = new ArrayList<>();
                session.setAttribute("orderList", orderList);
            }
            if(req.getParameter("add") != null) {
                long item_id = Util.getInteger(req, "id");
                int amount = Util.getInteger(req, "amount");
                CatalogDTO item = catalogService.read(item_id);
                CatalogDTO orderItem = new CatalogDTO(item_id, amount, item.getName(), item.getPrice());
                orderList.add(orderItem);
                item.setAmount(item.getAmount() - amount);
                catalogService.update(item);
            }
            if (req.getParameter("create") != null) {
                OrderDTO order = new OrderDTO(null, 0, user_id);
                order = orderService.create(order);
                session.setAttribute("order", order);
                order_id = order.getId();
                for (CatalogDTO catalog : orderList) {
                    ShippingListDTO list = new ShippingListDTO(0, String.valueOf(catalog.getAmount()), (int) catalog.getID(), (int) order_id);
                    listService.create(list);
                }
                orderList.clear();
                return new ActionResult(Actions.LISTORDERS);
            }
        }
        List<CatalogDTO> items = catalogService.getAll();
        req.setAttribute("catalogitems", items);
        return null;
    }
}
