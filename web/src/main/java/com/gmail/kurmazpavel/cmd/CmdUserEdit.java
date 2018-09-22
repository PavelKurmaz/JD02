package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.AddressService;
import com.gmail.kurmazpavel.service.AuditService;
import com.gmail.kurmazpavel.service.UserService;
import com.gmail.kurmazpavel.dto.AddressDTO;
import com.gmail.kurmazpavel.dto.AuditDTO;
import com.gmail.kurmazpavel.dto.UserDTO;
import com.gmail.kurmazpavel.service.impl.AddressServiceImpl;
import com.gmail.kurmazpavel.service.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.service.impl.UserServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

class CmdUserEdit extends Cmd {
    private AddressService addressService = new AddressServiceImpl();
    private UserService service = new UserServiceImpl();
    private AuditService auditService = new AuditServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (Util.isPost(req)) {
            if (req.getParameter("Delete") != null) {
                service.delete(user);
                session.invalidate();
                return new ActionResult(Actions.INDEX);
            }
            else if (req.getParameter("Update") != null) {
                String login = Util.getString(req, "login");
                String email = Util.getEmail(req, "email");
                String password = req.getParameter("password");
                String phone = Util.getString(req, "phone");
                user.setPassword(password);
                user.setEmail(email);
                user.setLogin(login);
                user.setPhone(phone);
                service.update(user);
                AuditDTO auditDTO = new AuditDTO();
                auditDTO.setUser(user);
                auditDTO.setCreated(LocalDateTime.now());
                auditDTO.setEvent("User " + user.getLogin() + " updated its data");
                auditService.create(auditDTO);
                return null;
            }
            else if (req.getParameter("AddUpdate") != null) {
                AddressDTO address = user.getAddress();
                String country = Util.getString(req, "country");
                String city = Util.getString(req, "city");
                String street = Util.getString(req, "street");
                String building = Util.getString(req, "building");
                String apt = Util.getString(req, "apt");
                String zip = Util.getString(req, "zip");
                address.setApt(apt);
                address.setBuilding(building);
                address.setCity(city);
                address.setStreet(street);
                address.setCountry(country);
                address.setZip(zip);
                addressService.update(address);
                AuditDTO auditDTO = new AuditDTO();
                auditDTO.setUser(user);
                auditDTO.setCreated(LocalDateTime.now());
                auditDTO.setEvent("User" + user.getLogin() + " updated address");
                auditService.create(auditDTO);
                session.setAttribute("address", address);
                return null;
            }
        }
        return null;
    }
}
