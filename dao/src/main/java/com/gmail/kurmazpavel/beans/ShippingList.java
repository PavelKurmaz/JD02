package com.gmail.kurmazpavel.beans;

public class ShippingList {

    private long id;
    private String quantity;
    private int catalog_ID;
    private int order_ID;

    public ShippingList(long id, String quantity, int catalog_ID, int order_ID) {
        this.id = id;
        this.quantity = quantity;
        this.catalog_ID = catalog_ID;
        this.order_ID = order_ID;
    }

    public ShippingList() {}

    public long getId() {
        return id;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getCatalog_ID() {
        return catalog_ID;
    }

    public int getOrder_ID() {
        return order_ID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCatalog_ID(int catalog_ID) {
        this.catalog_ID = catalog_ID;
    }

    public void setOrder_ID(int order_ID) {
        this.order_ID = order_ID;
    }

    @Override
    public String toString() {
        return "shippingList{" +
                "id=" + id +
                ", quantity='" + quantity + '\'' +
                ", catalog_ID=" + catalog_ID +
                ", order_ID=" + order_ID +
                '}';
    }
}
