package com.jadesenvovimento.holeriteweb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orgao")
public class Orgao {

    @Id
    private String id;
    private String cnpj;
    private String nomeOrgao;
    private String cidade;
    private String uf;

    public Orgao() {

    }

    public Orgao(String id, String cnpj, String nomeOrgao, String cidade, String uf) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeOrgao = nomeOrgao;
        this.cidade = cidade;
        this.uf = uf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeOrgao() {
        return nomeOrgao;
    }

    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
