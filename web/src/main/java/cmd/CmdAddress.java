package cmd;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.beans.dto.AddressDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.AddressServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class CmdAddress extends Cmd {
    private AddressService service = new AddressServiceImpl();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            String country = Util.getString(req, "country");
            String city = Util.getString(req, "city");
            String street = Util.getString(req, "street");
            String building = Util.getString(req, "building");
            String apt = Util.getString(req, "apt");
            String zip = Util.getString(req, "zip");
            UserDTO user = (UserDTO) req.getSession().getAttribute("user");
            AddressDTO address = service.read(user.getId());
            address.setApt(apt);
            address.setBuilding(building);
            address.setCity(city);
            address.setCountry(country);
            address.setStreet(street);
            address.setZip(zip);
            service.update(address);
            return new ActionResult(Actions.LOGIN);
        }
        return null;
    }
}

