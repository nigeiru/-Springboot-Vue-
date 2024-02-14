package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer goodsId;
    private Integer userId;
    private String orderId;
    private String time;
    private Double price;
    private String status;

    private String goodsName;
    private String img;
    private String userName;

}