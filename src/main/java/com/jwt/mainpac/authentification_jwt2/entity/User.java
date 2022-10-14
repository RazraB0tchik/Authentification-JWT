package com.jwt.mainpac.authentification_jwt2.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Component
@Table(name = "table_jwt_two")
@Entity
@Data
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
}
