package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private Integer userId;
    private Integer policyId;
    private String time;
    private Integer parentId;
    private String content;

    private String userName;
    private String avatar;

    private List<Comment> children;

}