package com.example.common.enums;

public enum Level {
    ORDINARY("普通户"),
    POOR("贫困户"),
    ;
    private String level;
    private Level(String level) {
        this.level = level;
    }
}
