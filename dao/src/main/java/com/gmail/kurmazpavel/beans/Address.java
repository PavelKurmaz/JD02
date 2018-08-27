package com.gmail.kurmazpavel.beans;

public class Address {
    private long id;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apt;
    private String zip;
    private long users_id;

    public Address(long id, String country, String city, String street, String building, String apt, String zip, long users_id) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apt = apt;
        this.zip = zip;
        this.users_id = users_id;
    }

    public Address() {}

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apt='" + apt + '\'' +
                ", zip='" + zip + '\'' +
                ", users_id=" + users_id +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setUsers_id(long users_id) {
        this.users_id = users_id;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuilding() {
        return building;
    }

    public String getApt() {
        return apt;
    }

    public String getZip() {
        return zip;
    }

    public long getUsers_id() {
        return users_id;
    }
}
