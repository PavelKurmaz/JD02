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
import java.sql.SQLException;

class CmdAddItem extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        AdminDTO admin = (AdminDTO) object;
        if (!Util.checkPermission(admin.getRoleId(), "Edit Items")) {
            req.setAttribute("errmessage", "You have no permission");
            return new ActionResult("error");
        }
        if (Util.isPost(req)) {
            String name = Util.getString(req,"name");
            BigDecimal price = BigDecimal.valueOf(Util.getDouble(req, "price"));
            long amount = Util.getInteger(req, "amount");
            if (name != null) {
                CatalogDTO item = new CatalogDTO();
                item.setName(name);
                item.setPrice(price);
                item.setAmount(amount);
                item = service.create(item);
                if (item.getId() > 0)
                    return new ActionResult(Actions.EDITCATALOG);
            }
        }
        return null;
    }
}
