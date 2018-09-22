package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.OrderService;
import com.gmail.kurmazpavel.dto.OrderDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.impl.OrderServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
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
            UserDTO user = (UserDTO)object;
            List<OrderDTO> orders = service.getById(user.getId());
            session.setAttribute("orders", orders);
        }
        else {
            List<OrderDTO> orders = service.getAll();
            session.setAttribute("orders", orders);
        }
        return null;
    }
}
