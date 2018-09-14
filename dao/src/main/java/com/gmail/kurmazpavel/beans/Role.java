package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name="Role", nullable = false)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id")
    private List<User> userList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id")
    private List<Admin> adminList = new ArrayList<>();
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Permission> permissions = new ArrayList<>();

    public Role () {}

    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }


    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
