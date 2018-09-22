package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.CatalogService;
import com.gmail.kurmazpavel.dto.AdminDTO;
import com.gmail.kurmazpavel.dto.CatalogDTO;
import com.gmail.kurmazpavel.service.impl.CatalogServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

class CmdEditCatalog extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        AdminDTO admin = (AdminDTO) object;
        if (!Util.checkPermission(admin.getRoleId(), "Edit Items")) {
            req.setAttribute("errmessage", "You have no permission");
            return new ActionResult("error");
        }
        if (Util.isPost(req)) {
            long id = Util.getInteger(req, "id");
            long amount = Util.getInteger(req, "amount");
            BigDecimal price = BigDecimal.valueOf(Util.getDouble(req, "price"));
            String name = Util.getString(req, "name");
            CatalogDTO catalog = service.read(id);
            catalog.setAmount(amount);
            catalog.setPrice(price);
            catalog.setName(name);
            if (req.getParameter("Update") != null) {
                service.update(catalog);
            } else if (req.getParameter("Delete") != null) {
                service.delete(catalog);
            }
        }
        List<CatalogDTO> items = service.getAll();
        req.setAttribute("items", items);
        return null;
    }
}
