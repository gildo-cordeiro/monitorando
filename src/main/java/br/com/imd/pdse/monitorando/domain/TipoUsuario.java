package br.com.imd.pdse.monitorando.domain;

public enum TipoUsuario {

    PROFESSOR("P"), ALUNO("A"), MONITOR("M");

    private String code;


    TipoUsuario(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
