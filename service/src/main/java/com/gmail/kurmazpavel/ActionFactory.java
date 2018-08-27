package com.gmail.kurmazpavel;

import javax.servlet.http.HttpServletRequest;

class ActionFactory {
    Actions defineAction(HttpServletRequest req) {
        String command = req.getParameter("command");
        if (command!=null && !command.isEmpty()) {
            return Actions.valueOf(command.toUpperCase());
        }
        else
            return Actions.INDEX;
    }
}
