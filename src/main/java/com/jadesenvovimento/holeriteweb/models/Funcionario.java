package com.jadesenvovimento.holeriteweb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "funcionario")
public class Funcionario {

    @Id
    private String id;
    private String nomeFuncionario;
    private String cpf;
    private Cargo cargo;
    private Orgao orgao;


    public Funcionario() {

    }

    public Funcionario(String id, String nomeFuncionario, String cpf, Cargo cargo, Orgao orgao) {
        this.id = id;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.cargo = cargo;
        this.orgao = orgao;
    }

    public Funcionario(String nomeFuncionario, String cpf, Cargo cargo, Orgao orgao) {
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.cargo = cargo;
        this.orgao = orgao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
