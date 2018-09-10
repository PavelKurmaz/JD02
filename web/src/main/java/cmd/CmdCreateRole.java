package cmd;

import com.gmail.kurmazpavel.RoleService;
import com.gmail.kurmazpavel.beans.dto.RoleDTO;
import com.gmail.kurmazpavel.impl.RoleServiceImpl;
import util.ActionResult;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdCreateRole extends Cmd {
    private RoleService roleService = new RoleServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String name = Util.getString(req, "name");
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRole(name);
            roleService.create(roleDTO);
            return new ActionResult(Actions.INDEX);
        }
        return null;
    }
}
