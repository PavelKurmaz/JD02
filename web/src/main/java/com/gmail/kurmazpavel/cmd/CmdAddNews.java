package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.NewsService;
import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.dto.NewsDTO;
import com.gmail.kurmazpavel.service.impl.NewsServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
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
