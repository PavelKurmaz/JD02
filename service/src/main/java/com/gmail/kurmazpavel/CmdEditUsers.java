package com.gmail.kurmazpavel;

import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

class CmdEditUsers extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        DAO dao = DAO.getDao();
        if (Util.isPost(req)) {
            int id = Util.getInteger(req, "id");
            String login = Util.getString(req, "login");
            String email = Util.getEmail(req, "email");
            String password = req.getParameter("password");
            String phone = Util.getString(req, "phone");
            String carma = Util.getString(req, "carma");
            User user = new User(id, login, password, email, phone, carma, 2);
            if (req.getParameter("Update") != null) {
                dao.user.update(user);
            } else if (req.getParameter("Delete") != null) {
                dao.user.delete(user);
            }
        }
        List<User> users = dao.user.getAll("");
        req.setAttribute("users", users);
        return null;
    }
}

