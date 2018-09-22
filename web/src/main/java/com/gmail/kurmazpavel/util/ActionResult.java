package com.gmail.kurmazpavel.util;

import com.gmail.kurmazpavel.cmd.Actions;

public class ActionResult {
    private Actions nextAction;
    private String jsp;

    public ActionResult(Actions nextAction) {
        this(nextAction,  nextAction.toString().toLowerCase());
    }

    public ActionResult (String jsp) {
        this(null, jsp);
    }

    public ActionResult(Actions nextAction, String jsp) {
        this.nextAction = nextAction;
        this.jsp = "/" + jsp + ".jsp";
    }

    public Actions getNextAction () {
        return nextAction;
    }

    public String getJsp() {
        return jsp;
    }
}
