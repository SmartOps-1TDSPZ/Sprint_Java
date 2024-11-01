package org.example.models;

//public enum Marca {
  //  CHEVROLET, FORD, HONDA, HYUNDAI, NISSAN
//}

public enum Marca {
    CHEVROLET("Chevrolet"),
    FORD("Ford"),
    HONDA("Honda"),
    HYUNDAI("Hyundai"),
    NISSAN("Nissan");

    private final String descricao; // Corrigido para variável de instância

    // Construtor do enum
    Marca(String descricao) {
        this.descricao = descricao;
    }

    // Método para obter a descrição da marca
    public String getDescricao() { // Remover static
        return descricao;
    }

    // Método para obter um enum a partir da descrição
    public static Marca fromDescricao(String descricao) {
        for (Marca marca : Marca.values()) {
            if (marca.getDescricao().equalsIgnoreCase(descricao)) {
                return marca;
            }
        }
        throw new IllegalArgumentException("Marca não encontrada para a descrição: " + descricao);
    }
}
