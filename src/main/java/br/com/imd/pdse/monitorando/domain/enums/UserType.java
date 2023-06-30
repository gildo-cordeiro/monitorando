package br.com.imd.pdse.monitorando.domain.enums;

public enum UserType {

    PROFESSOR("ROLE_P"), ALUNO("ROLE_A"), MONITOR("ROLE_M");

    private String code;

    UserType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
