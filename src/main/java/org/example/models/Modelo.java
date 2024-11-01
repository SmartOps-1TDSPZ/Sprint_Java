package org.example.models;

public enum Modelo {
  //  CROSSOVER , SUV , PICAPES , ESPORTIVOS , SEDA , MINIVAN , PERUA , HATCH

    SEDAN("Sedan"),
    SUV("SUV"),
    HATCHBACK("Hatchback");

    private final String descricao;

    Modelo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
