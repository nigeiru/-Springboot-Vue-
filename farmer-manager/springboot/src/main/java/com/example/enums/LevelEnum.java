package com.example.enums;

public enum LevelEnum {
    ORDINARY("普通户"),
    POOR("贫困户"),
    ;
    public String level;
    LevelEnum(String level){
        this.level = level;
    }
}
