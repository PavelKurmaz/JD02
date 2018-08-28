package cmd;

import com.gmail.kurmazpavel.CatalogService;
import com.gmail.kurmazpavel.beans.Catalog;
import com.gmail.kurmazpavel.impl.CatalogServiceImpl;
import util.ActionResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

class CmdViewCatalog extends Cmd {
    private CatalogService service = new CatalogServiceImpl();
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        HttpSession session = req.getSession();
        List<Catalog> items = service.getAll("");
        session.setAttribute("catalogItems", items);
        return new ActionResult("viewcatalog");
    }
}