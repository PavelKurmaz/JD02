package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.RoleService;
import com.gmail.kurmazpavel.service.UserService;
import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.impl.RoleServiceImpl;
import com.gmail.kurmazpavel.service.impl.UserServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
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
        if (!Util.checkPermission(admin.getRoleId(), "Edit Users")) {
            req.setAttribute("errmessage", "You have no permission");
            return new ActionResult("error");
        }
        if (Util.isPost(req)) {
            long id = Util.getInteger(req, "id");
            String login = Util.getString(req, "login");
            String password = req.getParameter("password");
            boolean disabled = (Util.getString(req, "disabled").equalsIgnoreCase("disabled")? true: false);
            UserDTO user = service.read(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setDisabled(disabled);
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
