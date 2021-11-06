package com.jadesenvovimento.holeriteweb.models;

import java.time.LocalDate;

public class Cargo {

    private String nomeCargo;
    private LocalDate admissao;


    public Cargo() {
    }

    public Cargo(String nomeCargo, LocalDate admissao) {
        this.nomeCargo = nomeCargo;
        this.admissao = admissao;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
