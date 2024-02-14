package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class User extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String role;
    private String phone;
    private String email;
    private String level;
    private Double account;

}
