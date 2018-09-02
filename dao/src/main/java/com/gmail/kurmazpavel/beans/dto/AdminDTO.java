package com.gmail.kurmazpavel.beans.dto;

public class AdminDTO {
    private long id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private long roles_id;

    public void setRoles_id(long roles_id) {
        this.roles_id = roles_id;
    }

    public long getRoles_id() {

        return roles_id;
    }

    public AdminDTO(long id, String login, String password, String email, String phone, String carma, long roles_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;

        this.roles_id = roles_id;
    }
    public AdminDTO() {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles_id=" + roles_id +
                '}';
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
}
