package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.beans.dto.CatalogDTO;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

class CmdAddItem extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        Object object = session.getAttribute("admin");
        if (object == null)
            return new ActionResult(Actions.ADMLOGIN);
        if (Util.isPost(req)) {
            String name = Util.getString(req,"name");
            double price = Util.getDouble(req, "price");
            int amount = Util.getInteger(req, "amount");
            if (name != null) {
                CatalogDTO item = new CatalogDTO(0, amount, name, price);
                item = service.create(item);
                if (item.getID() > 0)
                    return new ActionResult(Actions.EDITCATALOG);
            }
        }
        return null;
    }
}
