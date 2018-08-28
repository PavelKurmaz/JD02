package cmd;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.beans.User;
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
import java.util.List;
import java.util.Locale;

class CmdLogin extends Cmd {
    private UserService service = new UserServiceImpl();
    private AddressService addressService = new AddressServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req , HttpServletResponse resp) throws SQLException, DecoderException {
        if (Util.isPost(req)) {
            String login = Util.getString(req,"login");
            String password = Util.getString(req,"password");
            if (login != null && password != null) {
                String where = String.format(Locale.US,
                        " WHERE login='%s'",
                        login);
                List<User> users = service.getAll(where);
                if (users.size() > 0) {
                    User user = users.get(0);
                    BCodec codec = new BCodec();
                    String decode = codec.decode(user.getPassword());
                    if (!password.equals(decode)) {
                        req.setAttribute("errmessage", "Wrong password");
                        return new ActionResult(Actions.ERROR);
                    }
                    int user_id = (int)user.getId();
                    where = String.format(Locale.US, "WHERE users_ID='%d'", user_id);
                    List<Address> addresses = addressService.getAll(where);
                    Address address = addresses.get(0);
                    HttpSession session = req.getSession();
                    session.setAttribute("address", address);
                    session.setAttribute("user", user);
                    return new ActionResult(Actions.PROFILE);
                }
            }
        }
        return null;
    }
}
