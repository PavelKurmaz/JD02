package cmd;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.beans.Address;
import com.gmail.kurmazpavel.impl.AddressServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdAddress extends Cmd {
    private AddressService service = new AddressServiceImpl();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String country = Util.getString(req,"country");
            String city = Util.getString(req,"city");
            String street = Util.getString(req,"street");
            String building = Util.getString(req,"building");
            String apt = Util.getString(req,"apt");
            String zip = Util.getString(req,"zip");
            if (country != null) {
                int id = 0;
                for (Cookie cookie : req.getCookies()) {
                    if (cookie.getName().equals("user_id"))
                        id = Integer.parseInt(cookie.getValue());
                }
                Address address = new Address(0, country, city, street, building, apt, zip, id);
                service.create(address);
                if (address.getId() > 0) {
                    return new ActionResult(Actions.LOGIN);
                }
            }
        }
        return null;
    }
}
