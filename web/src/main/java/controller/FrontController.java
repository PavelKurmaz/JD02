package controller;

import com.gmail.kurmazpavel.ActionResult;
import com.gmail.kurmazpavel.Actions;
import com.gmail.kurmazpavel.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private Service service;
    private ServletContext servletContext;
    private final Logger logger = LogManager.getLogger(FrontController.class);

    @Override
    public void init(){
        service = Service.getService();
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
            ActionResult result = service.doService(req, resp);
            if (result == null) {
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(service.getAction().jsp);
                requestDispatcher.forward(req, resp);
            } else if (result.getAction() == null) {
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(result.getJsp());
                requestDispatcher.forward(req, resp);
                logger.info(result.getJsp());
                System.out.println("log test");
            } else {
                resp.sendRedirect("do?command=" + result.getAction().toString().toLowerCase());
                logger.info(result.getJsp());

            }
        }
        catch (Exception e) {
            req.setAttribute("errmessage", e.toString());
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement).append("<br>");
                if (stackTraceElement.toString().contains("controller.FrontController"))
                    break;
            }
            logger.error(e.getMessage(), e);
            req.setAttribute("stack", sb.toString());
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(Actions.ERROR.jsp);
            requestDispatcher.forward(req, resp);
        }
    }
}
