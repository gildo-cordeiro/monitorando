package br.com.imd.pdse.monitorando.domain.enums;

import lombok.Getter;

@Getter
public enum Theme {
    LIGHT("light"), DARK("dark");

    private final String code;

    Theme(String code) {
        this.code = code;
    }
}
