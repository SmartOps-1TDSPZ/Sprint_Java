package org.example.daos;

public class ClienteDaoFactory {

    private ClienteDaoFactory() {
    }

    public static ClienteDao create() {
        return new ClienteDaoImpl();
    }
}
