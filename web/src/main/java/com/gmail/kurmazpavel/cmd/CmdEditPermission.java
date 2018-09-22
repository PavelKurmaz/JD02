package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.PermissionService;
import com.gmail.kurmazpavel.service.RoleService;
import com.gmail.kurmazpavel.dto.PermissionDTO;
import com.gmail.kurmazpavel.dto.RoleDTO;
import com.gmail.kurmazpavel.service.impl.PermissionServiceImpl;
import com.gmail.kurmazpavel.service.impl.RoleServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdEditPermission extends Cmd {
    private PermissionService service = new PermissionServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            HttpSession session = req.getSession();
            long permissionId = 0;
            if(req.getParameter("edit") != null) {
                permissionId = Util.getInteger(req, "id");
                session.setAttribute("permissionId", permissionId);
            }
            if (req.getParameter("delete") != null){
                permissionId = (long) session.getAttribute("permissionId");
                int roleId = Util.getInteger(req, "id");
                service.deleteRole(permissionId, (long) roleId);
            }
            if (req.getParameter("add") != null) {
                permissionId = (long) session.getAttribute("permissionId");
                String roleName = req.getParameter("select");
                RoleDTO roleDTO = roleService.readByRole(roleName);
                service.addRole(permissionId, roleDTO.getId());
            }
            if (req.getParameter("erase") != null) {
                permissionId = Util.getInteger(req, "id");
                PermissionDTO permissionDTO = service.read(permissionId);
                service.delete(permissionDTO);
                return new ActionResult("permissions");
            }
            PermissionDTO permissionDTO = service.read(permissionId);
            List<RoleDTO> roleList = service.getRolesByName(permissionDTO.getName());
            List<RoleDTO> allRoles = roleService.getAll();
            allRoles.removeAll(roleList);
            req.setAttribute("addroles", allRoles);
            req.setAttribute("roles", roleList);
            req.setAttribute("name", permissionDTO.getName());
        }
        return null;
    }
}
