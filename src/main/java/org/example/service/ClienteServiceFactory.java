package org.example.service;

import org.example.daos.ClienteDao;

public class ClienteServiceFactory {
    public static ClienteService create() {
        return new ClienteService();
    }

}
