package cmd;

import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.User;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.impl.AuditService;
import com.gmail.kurmazpavel.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.impl.UserServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

class CmdEditUsers extends Cmd {
    private UserService service = new UserServiceImpl();
    private AuditService auditService = new AuditServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        if (Util.isPost(req)) {
            int id = Util.getInteger(req, "id");
            String login = Util.getString(req, "login");
            String email = Util.getEmail(req, "email");
            String password = req.getParameter("password");
            String phone = Util.getString(req, "phone");
            String carma = Util.getString(req, "carma");
            User user = new User(id, login, password, email, phone, carma, 2);
            if (req.getParameter("Update") != null) {
                service.update(user);
            } else if (req.getParameter("Delete") != null) {
                service.delete(user);
            }
        }
        List<User> users = service.getAll("");
        List<AuditDTO> list = auditService.getAll();
        System.out.println(list.toString());
        req.setAttribute("users", users);
        return null;
    }
}
