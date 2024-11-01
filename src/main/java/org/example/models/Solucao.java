package org.example.models;

public class Solucao {
    private Problema problema;

    private String descricaoSolucao;

    private int numeroDePecas;


    //construtores
    public Solucao( String descricaoSolucao, int numeroDePecas) {
        this.descricaoSolucao = descricaoSolucao;
        this.numeroDePecas = numeroDePecas;
    }

   public Solucao(int numeroDePecas) {
        this.numeroDePecas = numeroDePecas;
    }


    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    public int getNumeroDePecas() {
        return numeroDePecas;
    }

    public void setNumeroDePecas(int numeroDePecas) {
        this.numeroDePecas = numeroDePecas;
    }

    //todo
    public boolean isSolucaoValido(String descricaoSolucao, int numeroDePecas){

        if (descricaoSolucao == this.descricaoSolucao && numeroDePecas == this.numeroDePecas){
            return true;
        }
        else{
            return false;
        }
    }
    }



