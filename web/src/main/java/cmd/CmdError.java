package cmd;

import util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdError extends Cmd {
    @Override
    public ActionResult execute(HttpServletRequest req , HttpServletResponse resp) {
        return null;
    }
}
