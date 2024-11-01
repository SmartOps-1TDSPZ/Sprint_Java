package org.example.daos;

public class CarroDaoFactory {
    public CarroDaoFactory() {
    }

    public static CarroDao create() {
        return new CarroDaoImpl();
    }
}

