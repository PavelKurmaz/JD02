package com.gmail.kurmazpavel.beans;

public class ShippingItem {
    private String name;
    private int amount;
    private double price;
    private long order_id;

    public ShippingItem(String name, int amount, double price, long order_id) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public long getOrder_id(){return order_id;}

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrder_id(long order_id) {this.order_id = order_id;}

    @Override
    public String toString() {
        return "ShippingItem{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
