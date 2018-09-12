package cmd;

import com.gmail.kurmazpavel.AuditService;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.impl.AuditServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CmdAuditUsers extends Cmd {
    private AuditService service = new AuditServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            return new ActionResult("profile");
        }
        List<AuditDTO> list = service.getAll();
        req.setAttribute("audit", list);
        return null;
    }
}
