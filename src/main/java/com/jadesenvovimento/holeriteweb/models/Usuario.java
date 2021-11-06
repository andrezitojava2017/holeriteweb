package com.jadesenvovimento.holeriteweb.models;

import com.jadesenvovimento.holeriteweb.repository.UsuarioRepository;
import com.mongodb.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Document(collection = "usuario")
public class Usuario {

    @Id
    private String id;
    private String nome;
    @NotEmpty(message = "CAMPO CPF É OBRIGATORIO")
    private String cpf;
    private String telefone;
    @DBRef
    private Orgao orgao;
    private String token;

    private UsuarioRepository repository;

    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(String id, String nome, String cpf, String telefone, Orgao orgao, String token) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.orgao = orgao;
        this.token = token;
    }

    public Usuario() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }



}
