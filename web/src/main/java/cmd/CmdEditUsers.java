package cmd;

import com.gmail.kurmazpavel.RoleService;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.RoleServiceImpl;
import com.gmail.kurmazpavel.impl.UserServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

class CmdEditUsers extends Cmd {
    private UserService service = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        AdminDTO admin = (AdminDTO) object;
        if (!Util.checkPermission(admin.getRoles_id(), "Edit Users")) {
            req.setAttribute("errmessage", "You have no permission");
            return new ActionResult(Actions.ERROR);
        }
        if (Util.isPost(req)) {
            int id = Util.getInteger(req, "id");
            String login = Util.getString(req, "login");
            String password = req.getParameter("password");
            String carma = Util.getString(req, "carma");
            String role = Util.getString(req, "role");
            RoleDTO userNewRole = roleService.readByRole(role);
            int isDisabled = (Util.getString(req, "disabled").equalsIgnoreCase("disabled")? 1: 0);
            UserDTO user = new UserDTO(id, login, password, null, null, carma, userNewRole.getId(), isDisabled);
            if (req.getParameter("Update") != null) {
                service.update(user);
            } else if (req.getParameter("Delete") != null) {
                service.delete(user);
            }
        }
        List<UserDTO> users = service.getAll();
        List<RoleDTO> roles = roleService.getAll();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        return null;
    }
}

