package org.example.daos;

import org.example.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {

    void create(Cliente cliente) throws SQLException;

    List<Cliente> readAll() throws SQLException;

    void update(Cliente cliente) throws SQLException;

    void delete(String email) throws SQLException;

    Cliente readByEmail(String email) throws SQLException;
}
