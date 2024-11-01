package org.example.daos;

import org.example.models.Carro;

import java.sql.SQLException;
import java.util.List;

public interface CarroDao {

    void create(Carro carro) throws SQLException;
    Carro readByChassi(String chassi) throws SQLException;
    List<Carro> readByCliente(String emailCliente) throws SQLException;
    void update(Carro carro) throws SQLException;
    void delete(String chassi) throws SQLException;
}
