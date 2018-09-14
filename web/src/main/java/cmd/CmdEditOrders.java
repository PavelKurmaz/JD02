package cmd;

import com.gmail.kurmazpavel.BucketService;
import com.gmail.kurmazpavel.OrderService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.BucketDTO;
import com.gmail.kurmazpavel.beans.dto.OrderDTO;
import com.gmail.kurmazpavel.impl.BucketServiceImpl;
import com.gmail.kurmazpavel.impl.OrderServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

class CmdEditOrders extends Cmd {
    private OrderService service = new OrderServiceImpl();
    private BucketService bucketService = new BucketServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            HttpSession session = req.getSession();
            AdminDTO adminDTO = (AdminDTO) session.getAttribute("admin");
            if (Util.checkPermission(adminDTO.getRoles_id(), "Edit Orders")) {
                String status = Util.getString(req, "status");
                Long userId = (long) Util.getInteger(req, "user_id");
                Long id = (long) Util.getInteger(req, "id");
                BucketDTO bucketDTO = new BucketDTO();
                bucketDTO.setStatus(status);
                bucketDTO.setUserId(userId);
                bucketDTO.setId(id);
                if (req.getParameter("Update") != null) {
                    bucketService.update(bucketDTO);
                } else if (req.getParameter("Delete") != null) {
                    bucketService.delete(bucketDTO);
                }
            }
            else {
                req.setAttribute("errmessage", "You have no permission");
                return new ActionResult("error");
            }
        }
        List<BucketDTO> buckets = bucketService.getAll();
        req.setAttribute("buckets", buckets);
        return null;
    }
}
