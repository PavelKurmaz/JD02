package com.gmail.kurmazpavel.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(country, address.country) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(building, address.building) &&
                Objects.equals(apt, address.apt) &&
                Objects.equals(zip, address.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, building, apt, zip);
    }
}
