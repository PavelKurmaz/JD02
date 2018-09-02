package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PHONE", nullable = false)
    private String phone;
    @Column(name = "CARMA", nullable = false)
    private String carma;
    @Column(name = "ROLES_ID", nullable = false)
    private long roles_id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Audit audit;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public User(long id, String login, String password, String email, String phone, String carma, long roles_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.carma = carma;
        this.roles_id = roles_id;
    }
    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", carma='" + carma + '\'' +
                ", roles_id=" + roles_id +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCarma(String carma) {
        this.carma = carma;
    }

    public void setRoles_id(long roles_id) {
        this.roles_id = roles_id;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCarma() {
        return carma;
    }

    public long getRoles_id() {
        return roles_id;
    }
}
