package cmd;

import com.gmail.kurmazpavel.AdminService;
import com.gmail.kurmazpavel.beans.Admin;
import com.gmail.kurmazpavel.impl.AdminServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

class CmdAdminLogin extends Cmd {
    private AdminService service = new AdminServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String login = Util.getString(req,"login");
            String password = Util.getString(req,"password");
            if (login != null && password != null) {
                String where = String.format(Locale.US,
                        " WHERE login='%s' AND password='%s' ",
                        login, password);
                List<Admin> admins = service.getAll(where);
                if (admins.size() > 0) {
                    Admin admin = admins.get(0);
                    HttpSession session = req.getSession();
                    session.setAttribute("admin", admin);
                    return new ActionResult("admin");
                }
            }
        }
        return null;
    }
}
