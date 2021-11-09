package com.jadesenvovimento.holeriteweb.models;

public class ProventoDescontoNeutro {

    private double valor;
    private int quantidadeRef;
    private int codigoRubrica;
    private String descricaoRubrica;


    public ProventoDescontoNeutro() {
    }

    public ProventoDescontoNeutro(double valor, int quantidadeRef, int codigoRubrica, String descricaoRubrica) {
        this.valor = valor;
        this.quantidadeRef = quantidadeRef;
        this.codigoRubrica = codigoRubrica;
        this.descricaoRubrica = descricaoRubrica;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidadeRef() {
        return quantidadeRef;
    }

    public void setQuantidadeRef(int quantidadeRef) {
        this.quantidadeRef = quantidadeRef;
    }

    public int getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(int codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public String getDescricaoRubrica() {
        return descricaoRubrica;
    }

    public void setDescricaoRubrica(String descricaoRubrica) {
        this.descricaoRubrica = descricaoRubrica;
    }
}
