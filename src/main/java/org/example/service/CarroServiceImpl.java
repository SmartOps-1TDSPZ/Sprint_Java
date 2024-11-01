package org.example.service;

import org.example.daos.CarroDao;
import org.example.daos.CarroDaoFactory;
import org.example.models.Carro;

import java.sql.SQLException;
import java.util.List;

public class CarroServiceImpl {
    private final CarroDao carroDao= CarroDaoFactory.create();

    public void cadastrarCarro(Carro carro) throws SQLException {
        if (carro.getNumero_chassi() == null || carro.getNumero_chassi().isEmpty()) {
            throw new IllegalArgumentException("Número do chassi não pode ser vazio");
        }
        if (carro.getMarca() == null) {
            throw new IllegalArgumentException("Marca não pode ser vazia");
        }
        if (carro.getModelo() == null) {
            throw new IllegalArgumentException("Modelo não pode ser vazio");
        }
        carroDao.create(carro);
    }

    public List<Carro> listarCarros(String emailCliente) throws SQLException {
        return carroDao.readByCliente(emailCliente);
    }

    public Carro buscarCarroPorChassi(String chassi) throws SQLException {
        return carroDao.readByChassi(chassi);
    }

    public void atualizarCarro(Carro carro) throws SQLException {
        if (carro.getNumero_chassi() == null || carro.getNumero_chassi().isEmpty()) {
            throw new IllegalArgumentException("Número do chassi não pode ser vazio");
        }
        carroDao.update(carro);
    }

    public void removerCarro(String chassi) throws SQLException {
        carroDao.delete(chassi);
    }
}