package org.example.models;

public class Carro {
    private Marca marca;
    private Modelo modelo;

    private int ano;
    private String numero_chassi;
    private Cliente cliente;
   // private List<Problema> problemas;  // Lista de problemas associados ao carro


    public Carro(int ano, String numero_chassi, Marca marca, Modelo modelo,Cliente cliente) {
        this.ano = ano;
        this.numero_chassi = numero_chassi;
        this.marca = marca;
        this.modelo= modelo;
        this.cliente = cliente;
        //this.problemas = new ArrayList<>();
    }


    //metodos


    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getNumero_chassi() {
        return numero_chassi;
    }

    public void setNumero_chassi(String numero_chassi) {
        this.numero_chassi = numero_chassi;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // Método para retornar o email do cliente associado
    public String getEmailCliente() {
        return cliente != null ? cliente.getEmail() : null;
    }

    public Marca getMarca() {
        return marca;
    }

    public Modelo getModelo() {
        return modelo;
    }






}







