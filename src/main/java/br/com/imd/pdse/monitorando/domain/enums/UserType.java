package br.com.imd.pdse.monitorando.domain.enums;

public enum UserType {

    PROFESSOR("P"), ALUNO("A"), MONITOR("M");

    private String code;

    UserType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
