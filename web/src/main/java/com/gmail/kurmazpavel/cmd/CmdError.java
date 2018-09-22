package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdError extends Cmd {
    @Override
    public ActionResult execute(HttpServletRequest req , HttpServletResponse resp) {
        return null;
    }
}
