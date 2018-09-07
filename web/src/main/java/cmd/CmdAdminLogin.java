package cmd;

import com.gmail.kurmazpavel.AdminService;
import com.gmail.kurmazpavel.NewsService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;
import com.gmail.kurmazpavel.impl.AdminServiceImpl;
import com.gmail.kurmazpavel.impl.NewsServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

class CmdAdminLogin extends Cmd {
    private AdminService service = new AdminServiceImpl();
    private NewsService newsService = new NewsServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String login = Util.getString(req,"login");
            String password = Util.getString(req,"password");
            if (login != null && password != null) {
                AdminDTO admin = service.readByLogin(login);
                if (admin.getPassword().equals(password)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("admin", admin);
                    NewsDTO newsDTO = new NewsDTO();
                    newsDTO.setUser_id(admin.getId());
                    newsDTO.setCreated(LocalDateTime.now());
                    newsDTO.setTitle("Test Title");
                    newsDTO.setContent("Some test content");
                    newsService.create(newsDTO);
                    return new ActionResult("admin");
                }
            }
        }
        return null;
    }
}
