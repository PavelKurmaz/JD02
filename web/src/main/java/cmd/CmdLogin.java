package cmd;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.dto.AddressDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.AddressServiceImpl;
import com.gmail.kurmazpavel.impl.UserServiceImpl;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.BCodec;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

class CmdLogin extends Cmd {
    private UserService service = new UserServiceImpl();
    private AddressService addressService = new AddressServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req , HttpServletResponse resp) throws SQLException, DecoderException {
        if (Util.isPost(req)) {
            String login = Util.getString(req, "login");
            String password = Util.getString(req, "password");
            UserDTO validateUser;
            if (login != null && password != null) {
                validateUser = service.readByLogin(login);
                if (validateUser != null) {
                    if (validateUser.isDisabled()) {
                        req.setAttribute("errmessage", "Access denied");
                        return new ActionResult("error");
                    }
                    BCodec codec = new BCodec();
                    String decode = codec.decode(validateUser.getPassword());
                    if (!password.equals(decode)) {
                        req.setAttribute("errmessage", "Wrong password");
                        return new ActionResult("error");
                    }
                }
                else {
                    req.setAttribute("errmessage", "Wrong login");
                    return new ActionResult("error");
                }
            }
            else
                return null;
            AddressDTO address = addressService.read(validateUser.getId());
            HttpSession session = req.getSession();
            session.setAttribute("address", address);
            session.setAttribute("user", validateUser);
            return new ActionResult(Actions.PROFILE);
        }
        return null;
    }
}
