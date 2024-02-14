package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 管理员
*/
@Setter
@Getter
public class Agritainment implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String name;
    private String content;
    private String time;
    private String img;

}