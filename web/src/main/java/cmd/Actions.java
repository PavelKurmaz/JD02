package cmd;

public enum Actions {
    INDEX {
        {
            cmd = new CmdIndex();
        }
    },
    EDITNEWS {
        {
            cmd = new CmdEditNews();
        }
    },
    EDITPERMISSION {
        {
            cmd = new CmdEditPermission();
        }
    },
    CREATEPERMISSION {
        {
            cmd = new CmdCreatePermission();
        }
    },
    CREATEROLE {
        {
            cmd = new CmdCreateRole();
        }
    },
    LOGIN{
        {
            cmd = new CmdLogin();
        }
    },
    PERMISSIONS{
        {
            cmd = new CmdPermissions();
        }
    },
    ADMLOGIN{
        {
            cmd = new CmdAdminLogin();
        }
    },
    SIGNUP{
        {
            cmd = new CmdSignUp();
        }
    },
    ADMSIGNUP{
        {
            cmd = new CmdAdmSignUp();
        }
    },
    ADDRESS{
        {
            cmd = new CmdAddress();
        }
    },
    ERROR{
        {
            cmd = new CmdError();
        }
    },
    PROFILE{
        {
            cmd = new CmdProfile();
        }
    },
    RESET{
        {
            cmd = new CmdReset();
        }
    },
    VIEWCATALOG {
        {
            cmd = new CmdViewCatalog();
        }
    },
    MAKEORDER {
        {
            cmd = new CmdOrder();
        }
    },
    LISTORDERS {
        {
            cmd = new CmdListOrders();
        }
    },
    ADDCATALOGITEM {
        {
            cmd = new CmdAddItem();
        }
    },
    USEREDIT{
        {
            cmd = new CmdUserEdit();
        }
    },
    EDITCATALOG{
        {
            cmd = new CmdEditCatalog();
        }
    },
    EDITUSERS{
        {
            cmd = new CmdEditUsers();
        }
    },
    EDITORDERS{
        {
            cmd = new CmdEditOrders();
        }
    };

    public Cmd cmd = new CmdError();
    public String jsp="/" + this.toString().toLowerCase() + ".jsp";
}
