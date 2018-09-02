package cmd;

import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CmdListOrders extends Cmd {
    private OrderService service = new OrderServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        if (Util.isPost(req)){
            return new ActionResult(Actions.PROFILE);
        }
        HttpSession session = req.getSession();
        Object object = session.getAttribute("user");
        if (object != null){
            UserDTO user = (UserDTO) object;
            int user_id = (int) user.getId();
            List<OrderDTO> allOrders = service.getAll();
            List<OrderDTO> userOrders = new ArrayList<>();
            for (OrderDTO order: allOrders) {
                if (order.getUsers_ID() == user_id)
                    userOrders.add(order);
            }
            req.setAttribute("orders", userOrders);
        }
        return null;
    }
}
