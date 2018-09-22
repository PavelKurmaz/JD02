package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.AuditService;
import com.gmail.kurmazpavel.dto.AuditDTO;
import com.gmail.kurmazpavel.service.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CmdAuditUsers extends Cmd {
    private AuditService service = new AuditServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            return new ActionResult("profile");
        }
        List<AuditDTO> list = service.getAll();
        req.setAttribute("audit", list);
        return null;
    }
}
