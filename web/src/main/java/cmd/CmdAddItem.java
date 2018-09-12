package cmd;

import com.gmail.kurmazpavel.AuditService;
import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.AuditDTO;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;

class CmdAddItem extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    private AuditService auditService = new AuditServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        AdminDTO admin = (AdminDTO) object;
        if (!Util.checkPermission(admin.getRoles_id(), "Edit Items")) {
            req.setAttribute("errmessage", "You have no permission");
            return new ActionResult("error");
        }
        if (Util.isPost(req)) {
            String name = Util.getString(req,"name");
            double price = Util.getDouble(req, "price");
            int amount = Util.getInteger(req, "amount");
            if (name != null) {
                CatalogDTO item = new CatalogDTO(0, amount, name, price);
                item = service.create(item);
                AuditDTO auditDTO = new AuditDTO();
                auditDTO.setLocalDateTime(LocalDateTime.now());
                auditDTO.setUser_id(admin.getId());
                auditDTO.setEvent_type("Admin " + admin.getLogin() + " created item: " + name);
                auditService.create(auditDTO);
                if (item.getID() > 0)
                    return new ActionResult(Actions.EDITCATALOG);
            }
        }
        return null;
    }
}
