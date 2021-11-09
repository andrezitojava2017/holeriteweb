package com.jadesenvovimento.holeriteweb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "contra-cheques")
public class ContraCheque {

    @Id
    private String id;
    private Funcionario funcionario;
    private LocalDate competencia;
    private List<ProventoDescontoNeutro> proventos;
    private List<ProventoDescontoNeutro> descontos;

    public ContraCheque() {
    }

    public ContraCheque( Funcionario funcionario, Orgao orgao, LocalDate competencia) {
        this.funcionario = funcionario;
        this.competencia = competencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProventoDescontoNeutro> getProventos() {
        return proventos;
    }

    public void setProventos(List<ProventoDescontoNeutro> proventos) {
        this.proventos = proventos;
    }

    public List<ProventoDescontoNeutro> getDescontos() {
        return descontos;
    }

    public void setDescontos(List<ProventoDescontoNeutro> descontos) {
        this.descontos = descontos;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }
}
