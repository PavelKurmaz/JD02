package util;

import cmd.Actions;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public Actions defineAction(HttpServletRequest req) {
        String command = req.getParameter("command");
        if (command!=null && !command.isEmpty()) {
            System.out.println(command);
            return Actions.valueOf(command.toUpperCase());
        }
        else
            return Actions.INDEX;
    }
}
