package br.com.imd.pdse.monitorando.domain.enums;

public enum UserType {

    TEACHER("ROLE_T"), STUDENT("ROLE_S"), MONITOR("ROLE_M");

    private final String code;

    UserType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
