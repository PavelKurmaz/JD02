package cmd;

import com.gmail.kurmazpavel.DAO.DAO;
import util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdReset extends Cmd {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        DAO.reset();
        return new ActionResult(Actions.INDEX);
    }
}
