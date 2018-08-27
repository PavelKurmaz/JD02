package com.gmail.kurmazpavel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Cmd {
    abstract ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
