package com.jwt.mainpac.authentification_jwt2.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Component
@Table(name = "table_jwt_two")
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "userName")
    String userName;

    @NotEmpty
    @Column(name = "passwordUser")
    String passwordUser;

    @NotEmpty
    @Column(name = "role")
    String role;

    @Column(name = "active")
    Boolean active;

    @Column(name = "email")
    String email;

    public User(String userName, String passwordUser, String role, Boolean active, String email) {
        this.userName = userName;
        this.passwordUser = passwordUser;
        this.role = role;
        this.active = active;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                '}';
    }
}
