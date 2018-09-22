package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.AddressService;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.impl.AddressServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
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

