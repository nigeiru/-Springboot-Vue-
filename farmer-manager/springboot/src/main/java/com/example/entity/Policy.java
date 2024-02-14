package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter

public class Policy implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String name;
    private String content;
    private String time;
    private Integer num;

}