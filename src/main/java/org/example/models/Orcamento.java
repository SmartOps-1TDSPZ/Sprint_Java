package org.example.models;

public class Orcamento extends Solucao implements Reparo {
 private double valorDasPecas;
 private Solucao solucao;

 private Cliente cliente;

 private String TipoDePagamento;

 private double valor_reparo;


    public Orcamento( int numeroDePecas,double valorDasPecas,double valor_reparo) {
        super(numeroDePecas);
        this.valorDasPecas = valorDasPecas;
        this.valor_reparo = valor_reparo;
    }


    public double getValorDasPecas() {
        return valorDasPecas;
    }

    //Remover
    public void setValorDasPecas(double valorDasPecas) {
        this.valorDasPecas = valorDasPecas;
    }

    public double getValor_reparo() {
        return valor_reparo;
    }

    public void setValor_reparo(double valor_reparo) {
        this.valor_reparo = valor_reparo;
    }


    @Override
    public double valor(double valorDasPecas, int numeroDePecas, double valor_reparo) {
        double valor= (valorDasPecas * numeroDePecas)+ valor_reparo ;
        return valor;
    }
}


