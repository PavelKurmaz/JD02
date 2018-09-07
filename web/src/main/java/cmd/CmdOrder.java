package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class CmdOrder extends Cmd {
    private OrderService orderService = new OrderServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
        HttpSession session = req.getSession();
        if (session.getAttribute("admin") != null)
            return new ActionResult(Actions.INDEX);
        if (Util.isPost(req)) {
            Object isUser = session.getAttribute("user");
            UserDTO user = (UserDTO) isUser;
            if(req.getParameter("add") != null) {
                long item_id = Util.getInteger(req, "id");
                int amount = Util.getInteger(req, "amount");
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setItem_id(item_id);
                orderDTO.setUser_id(user.getId());
                orderDTO.setQuantity(amount);
                orderDTO.setCreated(LocalDateTime.now());
                orderService.create(orderDTO);
                CatalogDTO item = catalogService.read(item_id);
                item.setAmount(item.getAmount() - amount);
                catalogService.update(item);
            }
        }
        List<CatalogDTO> items = catalogService.getAll();
        req.setAttribute("catalogitems", items);
        return null;
    }
}
