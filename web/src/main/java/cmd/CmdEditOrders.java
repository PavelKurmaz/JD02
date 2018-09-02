package cmd;

import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class CmdEditOrders extends Cmd {
    private OrderService service = new OrderServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            Long order_id = (long)Util.getInteger(req, "id");
            int completed = Util.getInteger(req, "completed");
            int user_id = Util.getInteger(req, "users_id");
            OrderDTO order= new OrderDTO(order_id, completed, user_id);
            if (req.getParameter("Update") != null) {
                service.update(order);
            } else if (req.getParameter("Delete") != null) {
                service.delete(order);
            }
        }
        List<OrderDTO> orders = service.getAll();
        req.setAttribute("orders", orders);
        return null;
    }
}
