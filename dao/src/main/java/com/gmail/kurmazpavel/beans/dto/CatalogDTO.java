package com.gmail.kurmazpavel.beans.dto;

public class CatalogDTO {
    private long ID;
    private long amount;
    private String name;
    private double price;


    public CatalogDTO(long ID, int amount, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public CatalogDTO() {}

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
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
