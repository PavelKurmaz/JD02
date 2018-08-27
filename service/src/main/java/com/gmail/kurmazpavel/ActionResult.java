package com.gmail.kurmazpavel;

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

    public String getJsp() {
        return jsp;
    }

    public Actions getAction () {
        return nextAction;
    }
}
