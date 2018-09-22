package com.gmail.kurmazpavel.cmd;


import com.gmail.kurmazpavel.service.PermissionService;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.service.impl.PermissionServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;

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
