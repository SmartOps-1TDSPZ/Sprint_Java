package org.example.daos;

import org.example.config_database.DatabaseConfig;
import org.example.models.Carro;
import org.example.models.Cliente;
import org.example.models.Marca;
import org.example.models.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDaoImpl implements CarroDao {


    ClienteDao clienteDao = ClienteDaoFactory.create();


    @Override
    public void create(Carro carro) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "INSERT INTO carros (ano, chassi, marca, modelo, email_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carro.getAno());
            stmt.setString(2, carro.getNumero_chassi());
            stmt.setString(3, carro.getMarca().getDescricao());
            stmt.setString(4, carro.getModelo().getDescricao());
            stmt.setString(5, carro.getEmailCliente());  // Obtém o email do cliente
            stmt.executeUpdate();
        }
    }

    @Override
    public Carro readByChassi(String chassi) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "SELECT * FROM carros WHERE chassi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chassi);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Marca marca = Marca.valueOf(rs.getString("marca").toUpperCase());
                    Modelo modelo = Modelo.valueOf(rs.getString("modelo").toUpperCase());
                    String emailCliente = rs.getString("email_cliente");

                    // Cria um objeto Cliente usando o email


                    Cliente cliente = clienteDao.readByEmail(emailCliente);

                    return new Carro(
                            rs.getInt("ano"),
                            rs.getString("chassi"),
                            marca,
                            modelo,
                            cliente
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Carro> readByCliente(String emailCliente) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM carros WHERE email_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, emailCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Marca marca = Marca.valueOf(rs.getString("marca").toUpperCase());
                    Modelo modelo = Modelo.valueOf(rs.getString("modelo").toUpperCase());

                    // Cria um objeto Cliente usando o email
                    Cliente cliente = clienteDao.readByEmail(emailCliente);

                    carros.add(new Carro(
                            rs.getInt("ano"),
                            rs.getString("chassi"),
                            marca,
                            modelo,
                            cliente
                    ));
                }
            }
        }
        return carros;
    }

    @Override
    public void update(Carro carro) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "UPDATE carros SET marca = ?, modelo = ?, ano = ? WHERE chassi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Converte os enums Marca e Modelo para strings antes de inserir no banco de dados
            stmt.setString(1, carro.getMarca().name());
            stmt.setString(2, carro.getModelo().name());
            stmt.setInt(3, carro.getAno());
            stmt.setString(4, carro.getNumero_chassi());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String chassi) throws SQLException {
        Connection connection = new DatabaseConfig(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557727",
                "210605"
        ).getConnection();
        String sql = "DELETE FROM carros WHERE chassi = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chassi);
            stmt.executeUpdate();
        }
    }
}
