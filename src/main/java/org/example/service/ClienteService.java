package org.example.service;

import org.example.daos.ClienteDao;
import org.example.daos.ClienteDaoFactory;
import org.example.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteDao clienteDao = ClienteDaoFactory.create();

    public void cadastrarCliente(Cliente cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        clienteDao.create(cliente);
    }

    public Cliente loginCliente(String email, String senha) throws SQLException {
        Cliente cliente = clienteDao.readByEmail(email);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            return cliente;
        }
        return null;
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDao.readAll();
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        clienteDao.update(cliente);
    }

    public void removerCliente(String email) throws SQLException {
        clienteDao.delete(email);
    }

    // Implementação do método buscarClientePorEmail
    public Cliente buscarClientePorEmail(String email) throws SQLException {
        return clienteDao.readByEmail(email);
    }
}
