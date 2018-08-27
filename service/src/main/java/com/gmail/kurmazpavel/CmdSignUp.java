package com.gmail.kurmazpavel;
import com.gmail.kurmazpavel.DAO.DAO;
import com.gmail.kurmazpavel.beans.User;
import org.apache.commons.codec.net.BCodec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdSignUp extends Cmd {
    @Override
    ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        if (Util.isPost(req)) {
            String login = Util.getString(req,"login");
            String email = Util.getEmail(req,"E-mail");
            String password = Util.getString(req,"password");
            String phone = Util.getString(req, "phone");
            BCodec codec = new BCodec();
            String encode = codec.encode(password);
            if (login != null && email != null && password != null && phone !=null) {
                User user = new User(0, login, encode, email, phone, "regular", 2);
                DAO.getDao().user.create(user);
                if (user.getId() > 0) {
                    resp.addCookie(new Cookie("user_id", "" + user.getId()));
                    return new ActionResult(Actions.ADDRESS);
                }
            }
        }
        return null;
    }
}
