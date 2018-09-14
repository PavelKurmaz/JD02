package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="admins")
public class Admin implements Serializable {
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
    @Column(name = "ROLES_ID", nullable = false)
    private long roles_id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<News> newsList = new ArrayList<>();

    public List<News> getNewsList() {
        return newsList;
    }

    public Admin(long id, String login, String password, String email, String phone, long roles_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles_id = roles_id;
    }
    public Admin() {}

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

    public void setId(long id) {
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

    public long getRoles_id() {
        return roles_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoles_id(long roles_id) {
        this.roles_id = roles_id;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles_id=" + roles_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        Admin admin = (Admin) o;
        return roles_id == admin.roles_id &&
                Objects.equals(id, admin.id) &&
                Objects.equals(login, admin.login) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(phone, admin.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, phone, roles_id);
    }
}
