package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import util.ActionResult;
import util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

class CmdEditCatalog extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Util.isPost(req)) {
            int id = Util.getInteger(req, "id");
            int amount = Util.getInteger(req, "amount");
            double price = Util.getDouble(req, "price");
            String name = Util.getString(req, "name");
            Catalog catalog = new Catalog(id, amount, name, price);
            if (req.getParameter("Update") != null) {
                service.update(catalog);
            } else if (req.getParameter("Delete") != null) {
                service.delete(catalog);
            }
        }
        List<Catalog> items = service.getAll("");
        req.setAttribute("items", items);
        return null;
    }
}
