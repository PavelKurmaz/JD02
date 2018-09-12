package com.gmail.kurmazpavel.beans.dto;

public class UserDTO {
    private long id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private String carma;
    private long rolesId;
    private int disabled;

    public UserDTO(long id, String login, String password, String email, String phone, String carma, long rolesId, int disabled) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.carma = carma;
        this.rolesId = rolesId;
        this.disabled = disabled;
    }
    public UserDTO() {}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", carma='" + carma + '\'' +
                ", rolesId=" + rolesId +
                '}';
    }

    public long getRolesId() {
        return rolesId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
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

    public void setRolesId(long rolesId) {
        this.rolesId = rolesId;
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

}
