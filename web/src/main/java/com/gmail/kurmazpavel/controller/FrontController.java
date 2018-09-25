package com.gmail.kurmazpavel.controller;

import com.gmail.kurmazpavel.cmd.Actions;
import com.gmail.kurmazpavel.util.ActionFactory;
import com.gmail.kurmazpavel.util.ActionResult;
import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
public class FrontController extends HttpServlet {

    private ActionFactory actionFactory;
    private ServletContext servletContext;

    @Override
    public void init(){
        actionFactory = new ActionFactory();
        servletContext = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        serv(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       serv(req, resp);
    }

    private void serv (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Actions action = actionFactory.defineAction(req);
            ActionResult result = action.cmd.execute(req, resp);
            if (result == null) {
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(action.jsp);
                requestDispatcher.forward(req, resp);
            } else if (result.getNextAction() == null) {
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(result.getJsp());
                requestDispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("do?command=" + result.getNextAction().toString().toLowerCase());
            }
        }
        catch (Exception e) {
            req.setAttribute("errmessage", e.toString());
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement).append("<br>");
                if (stackTraceElement.toString().contains("FrontController"))
                    break;
            }
            req.setAttribute("stack", sb.toString());
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(Actions.ERROR.jsp);
            requestDispatcher.forward(req, resp);
        }
    }
}
