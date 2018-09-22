package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.UserService;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.impl.UserServiceImpl;
import org.apache.commons.codec.net.BCodec;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class CmdSignUp extends Cmd {
    private UserService service = new UserServiceImpl();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String login = Util.getString(req, "login");
            String email = Util.getEmail(req, "E-mail");
            String password = Util.getString(req, "password");
            if (service.readByLogin(login)!= null){
                req.setAttribute("errmessage", "Login already exists");
                return new ActionResult("error");
            }
            else if (service.readByEmail(email) != null) {
                req.setAttribute("errmessage", "Email already exists");
                return new ActionResult("error");
            }
            String phone = Util.getString(req, "phone");
            BCodec codec = new BCodec();
            String encode = codec.encode(password);
            UserDTO user = new UserDTO();
            user.setLogin(login);
            user.setPassword(encode);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(new AddressDTO());
            user.setDisabled(false);
            service.create(user);
            user = service.readByEmail(email);
            if (user.getId() != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                return new ActionResult(Actions.ADDRESS);
            }
        }
        return null;
    }
}
