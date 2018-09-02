package com.gmail.kurmazpavel.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "BUILDING")
    private String building;
    @Column(name = "APT")
    private String apt;
    @Column(name = "ZIP")
    private String zip;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public Address(long id, String country, String city, String street, String building, String apt, String zip) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apt = apt;
        this.zip = zip;
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
                '}';
    }

    public void setId(Long id) {
        this.id = id;
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

}
