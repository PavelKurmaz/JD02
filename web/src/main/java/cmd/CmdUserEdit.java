package cmd;

import com.gmail.kurmazpavel.AddressService;
import com.gmail.kurmazpavel.AuditService;
import com.gmail.kurmazpavel.UserService;
import com.gmail.kurmazpavel.beans.dto.AddressDTO;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.beans.dto.UserDTO;
import com.gmail.kurmazpavel.impl.AddressServiceImpl;
import com.gmail.kurmazpavel.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.impl.UserServiceImpl;
import util.ActionResult;
import util.Util;
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
                auditDTO.setUser_id(user.getId());
                auditDTO.setLocalDateTime(LocalDateTime.now());
                auditDTO.setEvent_type("User updated its data");
                auditService.create(auditDTO);
                return null;
            }
            else if (req.getParameter("AddUpdate") != null) {
                AddressDTO address = (AddressDTO) session.getAttribute("address");
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
                auditDTO.setUser_id(user.getId());
                auditDTO.setLocalDateTime(LocalDateTime.now());
                auditDTO.setEvent_type("User updated address");
                auditService.create(auditDTO);
                return null;
            }
        }
        return null;
    }
}
