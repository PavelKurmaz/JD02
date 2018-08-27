package com.gmail.kurmazpavel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdError extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req , HttpServletResponse resp) {
        return null;
    }
}
