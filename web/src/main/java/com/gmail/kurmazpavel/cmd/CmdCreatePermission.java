package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.PermissionService;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.service.impl.PermissionServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdCreatePermission extends Cmd {
    private PermissionService service = new PermissionServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            if (req.getParameter("name") != null) {
                String name = Util.getString(req, "name");
                PermissionDTO permissionDTO = new PermissionDTO();
                permissionDTO.setName(name);
                service.create(permissionDTO);
                return new ActionResult(Actions.PERMISSIONS);
            }
        }
        return null;
    }
}
