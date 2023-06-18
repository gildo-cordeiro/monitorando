package br.com.imd.pdse.monitorando.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Monitor {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
