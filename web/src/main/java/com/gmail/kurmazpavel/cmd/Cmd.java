package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Cmd {
    public abstract ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
