package com.gmail.kurmazpavel.beans;

public class Catalog {
    private long ID;
    private int amount;
    private String name;
    private double price;


    public Catalog(long ID, int amount, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Catalog() {}

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
