package com.gmail.kurmazpavel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Service {
    private Actions action;
    private static ActionFactory factory;

    public ActionResult doService (HttpServletRequest req, HttpServletResponse resp) throws Exception {
        action = factory.defineAction(req);
        return action.cmd.execute(req, resp);
    }

    public Actions getAction() {
        return action;
    }

    public static Service getService() {
        factory = new ActionFactory();
        return new Service();
    }
}
