package cmd;


import com.gmail.kurmazpavel.PermissionService;
import com.gmail.kurmazpavel.beans.dto.PermissionDTO;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.impl.PermissionServiceImpl;
import util.ActionResult;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdPermissions extends Cmd {
    private PermissionService service = new PermissionServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (req.getParameter("create") != null) {
            return new ActionResult(Actions.CREATEPERMISSION);
        }
        List<PermissionDTO> permissions = service.getAll();
        session.setAttribute("permissions", permissions);
        return new ActionResult("permissions");
    }
}
