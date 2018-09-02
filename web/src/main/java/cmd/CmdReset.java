package cmd;

import com.gmail.kurmazpavel.resetDB.Runner;
import util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdReset extends Cmd {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Runner.main(new String[]{});
        return new ActionResult(Actions.INDEX);
    }
}
