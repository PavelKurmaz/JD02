package cmd;

import com.gmail.kurmazpavel.AuditService;
import com.gmail.kurmazpavel.CommentService;
import com.gmail.kurmazpavel.NewsService;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.beans.dto.CommentDTO;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.impl.CommentServiceImpl;
import com.gmail.kurmazpavel.impl.NewsServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public class CmdReadNews extends Cmd {
    private NewsService service = new NewsServiceImpl();
    private CommentService commentService = new CommentServiceImpl();
    private AuditService auditService = new AuditServiceImpl();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (Util.isPost(req)) {
            Object isUser = session.getAttribute("user");
            UserDTO user = (UserDTO) isUser;
            if(req.getParameter("comments") != null){
                long newsId = Util.getInteger(req, "newsId");
                NewsDTO newsDTO = service.read(newsId);
                List<CommentDTO> comments = service.getComments(newsDTO.getId());
                req.setAttribute("singleNews", newsDTO);
                req.setAttribute("comments", comments);
                return new ActionResult("comments");
            }
            if (req.getParameter("addcomment")!= null) {
                long newsId = Util.getInteger(req, "newsId");
                NewsDTO newsDTO = service.read(newsId);
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setUserDTO(user);
                commentDTO.setCreated(LocalDateTime.now());
                commentDTO.setContent(Util.getString(req,"content"));
                commentDTO.setNewsId(newsDTO.getId());
                commentService.create(commentDTO);
                List<CommentDTO> comments = service.getComments(newsId);
                AuditDTO auditDTO = new AuditDTO();
                auditDTO.setUser(user);
                auditDTO.setCreated(LocalDateTime.now());
                auditDTO.setEvent("User " + user.getLogin() + " added comment on news " + newsDTO.getTitle());
                auditService.create(auditDTO);
                req.setAttribute("comments", comments);
                req.setAttribute("singleNews", newsDTO);
                return new ActionResult("comments");
            }
        }
        List<NewsDTO> news = service.getAll();
        session.setAttribute("news", news);
        return new ActionResult("readnews");
    }

}
