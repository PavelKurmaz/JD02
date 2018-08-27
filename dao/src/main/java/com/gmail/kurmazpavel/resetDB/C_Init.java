package com.gmail.kurmazpavel.resetDB;

import java.sql.Connection;

class C_Init {

    static void initialize(Connection connection) {
        System.out.println("Initializing tabs");
        Table_Init.createTabs(connection);
        System.out.println("Creating users");
        User_Inserts.userInserts(connection);
        System.out.println("Creating catalog");
        Catalog_Init.createCatalog(connection);
        System.out.println("Creating Orders");
        Orderlist_Init.createOrders(connection);
        System.out.println("Finally, creating shipping list");
        List_Init.shippingListInit(connection);
        System.out.println("If you see this, table and data created!");
    }
}
