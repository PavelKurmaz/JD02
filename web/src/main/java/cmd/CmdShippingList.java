package cmd;

import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.UserServiceImpl;
import util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


class CmdShippingList extends Cmd {
    private UserService service = new UserServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object isUser = session.getAttribute("user");
        UserDTO user = (UserDTO) isUser;
        service.read(user.getId());
        return new ActionResult("getshippinglist");
    }
}
