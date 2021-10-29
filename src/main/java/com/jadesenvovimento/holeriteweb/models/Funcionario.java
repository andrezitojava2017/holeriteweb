package com.jadesenvovimento.holeriteweb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "funcionario")
public class Funcionario {

    @Id
    private String id;
    private String nomeFuncionario;
    private Cargo cargo;
    private Orgao orgao;


    public Funcionario() {

    }

    public Funcionario(String nomeFuncionario, Cargo cargo, Orgao orgao) {
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
        this.orgao = orgao;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }
}
