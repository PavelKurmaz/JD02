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
import java.time.LocalDateTime;
import java.util.List;

class CmdEditCatalog extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    private AuditService auditService = new AuditServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        AdminDTO admin = (AdminDTO) object;
        if (!Util.checkPermission(admin.getRoles_id(), "Edit Items")) {
            req.setAttribute("errmessage", "You have no permission");
            return new ActionResult("error");
        }
        if (Util.isPost(req)) {
            int id = Util.getInteger(req, "id");
            int amount = Util.getInteger(req, "amount");
            double price = Util.getDouble(req, "price");
            String name = Util.getString(req, "name");
            CatalogDTO catalog = new CatalogDTO(id, amount, name, price);
            AuditDTO auditDTO = new AuditDTO();
            auditDTO.setLocalDateTime(LocalDateTime.now());
            auditDTO.setUser_id(admin.getId());
            if (req.getParameter("Update") != null) {
                service.update(catalog);
                auditDTO.setEvent_type("Admin " + admin.getLogin() + " updated item #" + id + " : " + name);
            } else if (req.getParameter("Delete") != null) {
                service.delete(catalog);
                auditDTO.setEvent_type("Admin " + admin.getLogin() + " deleted item #" + id + " : " + name);
            }
            auditService.create(auditDTO);
        }
        List<CatalogDTO> items = service.getAll();
        req.setAttribute("items", items);
        return null;
    }
}
