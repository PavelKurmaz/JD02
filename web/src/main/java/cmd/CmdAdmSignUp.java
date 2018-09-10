package cmd;

import com.gmail.kurmazpavel.AdminService;
import com.gmail.kurmazpavel.RoleService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.impl.AdminServiceImpl;
import com.gmail.kurmazpavel.impl.RoleServiceImpl;
import util.ActionResult;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdAdmSignUp extends Cmd {
    private RoleService roleService = new RoleServiceImpl();
    private AdminService service = new AdminServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String login = Util.getString(req,"login");
            String email = Util.getEmail(req,"email");
            String role = Util.getString(req, "select");
            String password = Util.getString(req, "password");
            String phone = Util.getString(req, "phone");
            AdminDTO admin = new AdminDTO();
            admin.setEmail(email);
            admin.setLogin(login);
            admin.setPassword(password);
            admin.setPhone(phone);
            RoleDTO roleDTO = roleService.readByRole(role);
            admin.setRoles_id(roleDTO.getId());
            service.create(admin);
            return new ActionResult(Actions.ADMLOGIN);
        }
        List<RoleDTO> roles = roleService.getAll();
        HttpSession session = req.getSession();
        session.setAttribute("roles", roles);
        return null;
    }
}
