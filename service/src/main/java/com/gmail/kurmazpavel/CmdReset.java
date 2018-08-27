package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdReset extends Cmd{
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DAO.reset();
        return new ActionResult(Actions.INDEX);
    }
}
