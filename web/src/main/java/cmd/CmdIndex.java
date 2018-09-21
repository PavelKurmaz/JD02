package cmd;

import com.gmail.kurmazpavel.RoleService;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.impl.RoleServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

class CmdIndex extends Cmd {
    private RoleService roleService = new RoleServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        if (Util.isPost(req)) {
            if (req.getParameter("select") != null) {
                String select = Util.getString(req, "select");
                if (select.equalsIgnoreCase("user"))
                    return new ActionResult(Actions.LOGIN);
                else
                    return new ActionResult(Actions.ADMLOGIN);
            }
        }
        List<RoleDTO> roles = roleService.getAll();
        HttpSession session = req.getSession();
        session.setAttribute("roles", roles);
        return null;
    }
}
