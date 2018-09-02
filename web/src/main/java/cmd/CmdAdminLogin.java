package cmd;

import com.gmail.kurmazpavel.AdminService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.impl.AdminServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

class CmdAdminLogin extends Cmd {
    private AdminService service = new AdminServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String login = Util.getString(req,"login");
            String password = Util.getString(req,"password");
            if (login != null && password != null) {
                List<AdminDTO> admins = service.getAll();
                if (admins.size() > 0) {
                    long id;
                    for (AdminDTO admin: admins) {
                        if (admin.getLogin().equals(login)) {
                            id = admin.getId();
                            admin = service.read(id);
                            HttpSession session = req.getSession();
                            session.setAttribute("admin", admin);
                            return new ActionResult("admin");
                        }
                    }
                }
            }
        }
        return null;
    }
}
