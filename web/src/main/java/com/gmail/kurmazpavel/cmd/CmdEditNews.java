package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.CommentService;
import com.gmail.kurmazpavel.service.NewsService;
import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.dto.CommentDTO;
import com.gmail.kurmazpavel.dto.NewsDTO;
import com.gmail.kurmazpavel.service.impl.CommentServiceImpl;
import com.gmail.kurmazpavel.service.impl.NewsServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdEditNews extends Cmd {
    private NewsService service = new NewsServiceImpl();
    private CommentService commentService = new CommentServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (Util.isPost(req)) {
            AdminDTO adminDTO = (AdminDTO) session.getAttribute("admin");
            if (!Util.checkPermission(adminDTO.getRoleId(), "Edit News")) {
                req.setAttribute("errmessage", "You have no permission");
                return new ActionResult("error");
            }
                if (req.getParameter("edit") != null) {
                    long newsId = Util.getInteger(req, "newsId");
                    NewsDTO newsDTO = service.read(newsId);
                    req.setAttribute("singleNews", newsDTO);
                    return new ActionResult("singleNewsEdit");
                }

                if (req.getParameter("delete") != null) {
                    long newsId = Util.getInteger(req, "newsId");
                    NewsDTO newsDTO = service.read(newsId);
                    service.delete(newsDTO);
                    return new ActionResult(Actions.EDITNEWS);
                }

                if (req.getParameter("add") != null)
                    return new ActionResult(Actions.ADDNEWS);

                if (req.getParameter("update") != null){
                    long newsId = Util.getInteger(req, "newsId");
                    NewsDTO newsDTO = service.read(newsId);
                    newsDTO.setTitle(Util.getString(req, "title"));
                    newsDTO.setContent(Util.getString(req, "content"));
                    service.update(newsDTO);
                    return new ActionResult(Actions.EDITNEWS);
                }

                if (req.getParameter("comments")!= null) {
                    long newsId = Util.getInteger(req, "newsId");
                    List<CommentDTO> comments = service.getComments(newsId);
                    req.setAttribute("comments", comments);
                    req.setAttribute("newsId", newsId);
                    return new ActionResult("editcomments");
                }

                if (req.getParameter("deletecomment")!= null) {
                    long newsId = Util.getInteger(req, "newsId");
                    long id = Util.getInteger(req, "id");
                    CommentDTO commentDTO = commentService.read(id);
                    commentService.delete(commentDTO);
                    List<CommentDTO> comments = service.getComments(newsId);
                    req.setAttribute("comments", comments);
                    req.setAttribute("newsId", newsId);
                    return new ActionResult("editcomments");
                }
            }
        List<NewsDTO> news = service.getAll();
        req.setAttribute("news", news);
        return new ActionResult("editnews");
    }
}
