package com.jadesenvovimento.holeriteweb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "contra-cheques")
public class ContraCheque {

    @Id
    private String id;
    private Cargo cargo;
    private Funcionario funcionario;
    private Orgao orgao;
    private LocalDate competencia;

    public ContraCheque() {
    }

    public ContraCheque(Cargo cargo, Funcionario funcionario, Orgao orgao, LocalDate competencia) {
        this.cargo = cargo;
        this.funcionario = funcionario;
        this.orgao = orgao;
        this.competencia = competencia;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }
}
