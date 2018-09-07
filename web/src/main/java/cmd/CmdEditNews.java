package cmd;

import com.gmail.kurmazpavel.CommentService;
import com.gmail.kurmazpavel.NewsService;
import com.gmail.kurmazpavel.beans.dto.CommentDTO;
import com.gmail.kurmazpavel.beans.dto.NewsDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.CommentServiceImpl;
import com.gmail.kurmazpavel.impl.NewsServiceImpl;
import util.ActionResult;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public class CmdEditNews extends Cmd {
    private NewsService service = new NewsServiceImpl();
    private CommentService commentService = new CommentServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (Util.isPost(req)) {
            Object isUser = session.getAttribute("user");
            UserDTO user = (UserDTO) isUser;
            if(req.getParameter("addComment") != null){
                CommentDTO commentDTO = new CommentDTO();
                long newsId = Util.getInteger(req, "newsId");
                commentDTO.setNews_id(newsId);
                commentDTO.setContent(Util.getString(req, "comment"));
                commentDTO.setCreated(LocalDateTime.now());
                commentDTO.setUser_id(user.getId());
                commentService.create(commentDTO);
            }
        }
        List<NewsDTO> news = service.getAll();
        session.setAttribute("news", news);
        return new ActionResult("readnews");
    }
}
