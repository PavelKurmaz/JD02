package cmd;

import com.gmail.kurmazpavel.BucketService;
import com.gmail.kurmazpavel.beans.dto.AdminDTO;
import com.gmail.kurmazpavel.beans.dto.BucketDTO;
import com.gmail.kurmazpavel.impl.BucketServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

class CmdEditOrders extends Cmd {;
    private BucketService bucketService = new BucketServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            HttpSession session = req.getSession();
            AdminDTO adminDTO = (AdminDTO) session.getAttribute("admin");
            if (Util.checkPermission(adminDTO.getRoleId(), "Edit Orders")) {
                String status = Util.getString(req, "status");
                Long id = (long) Util.getInteger(req, "id");
                BucketDTO bucketDTO = bucketService.read(id);
                if (req.getParameter("Update") != null) {
                    bucketDTO.setStatus(status);
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
