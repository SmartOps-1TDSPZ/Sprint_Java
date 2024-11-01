package org.example.service;

public class CarroServiceFactory {
    public static CarroServiceImpl create() {
        return new CarroServiceImpl();
    }
}