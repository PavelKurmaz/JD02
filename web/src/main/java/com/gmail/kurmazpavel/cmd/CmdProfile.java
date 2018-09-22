package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class CmdProfile extends Cmd {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if(req.getParameter("logout") != null) {
            session.invalidate();
            return new ActionResult(Actions.INDEX);
        }
        Object isUser = session.getAttribute("user");
        Object isAdmin = session.getAttribute("admin");
        if (isUser == null && isAdmin == null)
            return new ActionResult(Actions.INDEX);
        else if (isUser != null)
            return new ActionResult("profile");
        else
            return new ActionResult("admin");
    }
}