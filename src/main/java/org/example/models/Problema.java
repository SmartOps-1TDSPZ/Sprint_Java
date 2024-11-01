package org.example.models;

public class Problema {
    private Carro carro;


    private String descricao;

    private String gravidade;



    public Problema(String descricao, String gravidade) {
        this.descricao = descricao;
        this.gravidade = gravidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }








    // Método para validar se o problema é verdadeiro
    public boolean isProblemaValido() {
        // Regras de validação:
        // 1. A descrição não pode ser nula ou vazia
        // 2. A gravidade deve ser uma das opções válidas

        if (descricao == null || descricao.isEmpty()) {
            return false;
        }

        // Considera gravidade válida como: "ALTA", "MEDIA", "BAIXA"
        if (!(gravidade.equalsIgnoreCase("ALTA") ||
                gravidade.equalsIgnoreCase("MEDIA") ||
                gravidade.equalsIgnoreCase("BAIXA"))) {
            return false;
        }


        return true;
    }
}