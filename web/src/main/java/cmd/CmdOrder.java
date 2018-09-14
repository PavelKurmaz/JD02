package cmd;

import com.gmail.kurmazpavel.AuditService;
import com.gmail.kurmazpavel.BucketService;
import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.dto.*;
import com.gmail.kurmazpavel.impl.AuditServiceImpl;
import com.gmail.kurmazpavel.impl.BucketServiceImpl;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import util.Util;
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
                long item_id = Util.getInteger(req, "id");
                int amount = Util.getInteger(req, "amount");
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setItemId(item_id);
                orderDTO.setUserId(user.getId());
                orderDTO.setQuantity(amount);
                orderService.create(orderDTO, bucketDTO.getId());
                AuditDTO auditDTO = new AuditDTO();
                auditDTO.setUser_id(user.getId());
                auditDTO.setLocalDateTime(LocalDateTime.now());
                CatalogDTO item = catalogService.read(item_id);
                item.setAmount(item.getAmount() - amount);
                auditDTO.setEvent_type("User " + user.getLogin() + " created an order: " + item.getName() + " " + amount + " pcs");
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
