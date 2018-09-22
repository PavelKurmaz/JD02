package com.gmail.kurmazpavel.cmd;

import com.gmail.kurmazpavel.service.AuditService;
import com.gmail.kurmazpavel.service.BucketService;
import com.gmail.kurmazpavel.service.CatalogService;
import com.gmail.kurmazpavel.service.OrderService;
import com.gmail.kurmazpavel.dto.*;
import com.gmail.kurmazpavel.service.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.service.impl.BucketServiceImpl;
import com.gmail.kurmazpavel.service.impl.CatalogServiceImpl;
import com.gmail.kurmazpavel.service.impl.OrderServiceImpl;
import com.gmail.kurmazpavel.util.ActionResult;
import com.gmail.kurmazpavel.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class CmdOrder extends Cmd {
    private OrderService orderService = new OrderServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();
    private AuditService auditService = new AuditServiceImpl();
    private BucketService bucketService = new BucketServiceImpl();

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException{
        HttpSession session = req.getSession();
        if (session.getAttribute("admin") != null)
            return new ActionResult(Actions.INDEX);
        if (Util.isPost(req)) {
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (session.getAttribute("bucket") == null) {
                BucketDTO bucketDTO = new BucketDTO();
                bucketDTO.setUserId(user.getId());
                bucketDTO.setStatus("New");
                bucketDTO.setCreated(LocalDateTime.now());
                bucketDTO = bucketService.create(bucketDTO);
                session.setAttribute("bucket", bucketDTO);
            }
            if(req.getParameter("add") != null) {
                BucketDTO bucketDTO = (BucketDTO) session.getAttribute("bucket");
                long itemId = Util.getInteger(req, "id");
                long amount = Util.getInteger(req, "amount");
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setItemId(itemId);
                orderDTO.setUserId(user.getId());
                orderDTO.setQuantity(amount);
                orderDTO.setCreated(LocalDateTime.now());
                orderService.create(orderDTO, bucketDTO.getId());
                AuditDTO auditDTO = new AuditDTO();
                auditDTO.setUser(user);
                auditDTO.setCreated(LocalDateTime.now());
                CatalogDTO item = catalogService.read(itemId);
                item.setAmount(item.getAmount() - amount);
                auditDTO.setEvent("User " + user.getLogin() + " created an order: " + item.getName() + " " + amount + " pcs");
                auditService.create(auditDTO);
                catalogService.update(item);
            }
            if(req.getParameter("create") != null) {
                session.removeAttribute("bucket");
                return new ActionResult(Actions.PROFILE);
            }
        }
        List<CatalogDTO> items = catalogService.getAll();
        req.setAttribute("catalogitems", items);
        return null;
    }
}
