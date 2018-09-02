package com.gmail.kurmazpavel.beans.dto;

public class ShippingListDTO {

    private Long id;
    private String quantity;
    private long catalog_ID;
    private long order_ID;

    public ShippingListDTO(long id, String quantity, int catalog_ID, int order_ID) {
        this.id = id;
        this.quantity = quantity;
        this.catalog_ID = catalog_ID;
        this.order_ID = order_ID;
    }

    public ShippingListDTO() {}

    public Long getId() {
        return id;
    }

    public String getQuantity() {
        return quantity;
    }

    public long getCatalog_ID() {
        return catalog_ID;
    }

    public long getOrder_ID() {
        return order_ID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCatalog_ID(long catalog_ID) {
        this.catalog_ID = catalog_ID;
    }

    public void setOrder_ID(long order_ID) {
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
