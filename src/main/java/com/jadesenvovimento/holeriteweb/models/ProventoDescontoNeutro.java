package com.jadesenvovimento.holeriteweb.models;

public class ProventoDescontoNeutro {

    private double valor;
    private int quantidadeRef;
    private int codigoRubrica;
    private String nomeRubrica;


    public ProventoDescontoNeutro() {
    }

    public ProventoDescontoNeutro(double valor, int quantidadeRef, int codigoRubrica, String nomeRubrica) {
        this.valor = valor;
        this.quantidadeRef = quantidadeRef;
        this.codigoRubrica = codigoRubrica;
        this.nomeRubrica = nomeRubrica;
    }

    public int getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(int codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public String getNomeRubrica() {
        return nomeRubrica;
    }

    public void setNomeRubrica(String nomeRubrica) {
        this.nomeRubrica = nomeRubrica;
    }

    public int getQuantidadeRef() {
        return quantidadeRef;
    }

    public void setQuantidadeRef(int quantidadeRef) {
        this.quantidadeRef = quantidadeRef;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
