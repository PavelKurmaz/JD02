package cmd;

import com.gmail.kurmazpavel.NewsService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;
import com.gmail.kurmazpavel.impl.NewsServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class CmdAddNews extends Cmd {
    private NewsService service = new NewsServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (Util.isPost(req)) {
            AdminDTO adminDTO = (AdminDTO) session.getAttribute("admin");
            String title = Util.getString(req, "title");
            String content = Util.getString(req, "content");
            NewsDTO newsDTO = new NewsDTO();
            newsDTO.setContent(content);
            newsDTO.setTitle(title);
            newsDTO.setCreated(LocalDateTime.now());
            newsDTO.setUserId(adminDTO.getId());
            service.create(newsDTO);
            return new ActionResult(Actions.EDITNEWS);
        }
        return null;
    }
}
