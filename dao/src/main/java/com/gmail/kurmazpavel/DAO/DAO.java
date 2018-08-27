package com.gmail.kurmazpavel.DAO;
import com.gmail.kurmazpavel.resetDB.Runner;

public class DAO {

    private static DAO dao;

    private DAO (){
        user = new UserDao();
        admin = new AdminDao();
        address = new AddressDao();
        catalog = new CatalogDAO();
        order = new OrderDAO();
        shippingList = new ListDAO();
        roles = new RolesDAO();
    }

    public DAO getDAO() {
        return dao;
    }

    public UserDao user;
    public AdminDao admin;
    public AddressDao address;
    public CatalogDAO catalog;
    public OrderDAO order;
    public ListDAO shippingList;
    public RolesDAO roles;

    public static DAO getDao() {
        if (dao == null)
            synchronized (DAO.class){
            if (dao == null)
                dao = new DAO();
            }
            return dao;
    }

    public static void reset(){
        Runner.main(new String[]{});
    }
}
