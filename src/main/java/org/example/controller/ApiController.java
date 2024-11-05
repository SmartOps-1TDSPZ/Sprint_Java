package org.example.controller;

import org.example.dtos.CarroDto;
import org.example.dtos.ClienteDto;
import org.example.models.Carro;
import org.example.models.Cliente;
import org.example.models.Marca;
import org.example.models.Modelo;
import org.example.service.CarroServiceImpl;
import org.example.service.CarroServiceFactory;
import org.example.service.ClienteService;
import org.example.service.ClienteServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/rest")
public class ApiController {
    private final ClienteService clienteService = ClienteServiceFactory.create();
    private final CarroServiceImpl carroServiceImpl = CarroServiceFactory.create();

    // CRUD de Cliente

    @POST
    @Path("/clientes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCliente(ClienteDto clienteDto) {
        try {
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getSenha(), clienteDto.getNumero(), clienteDto.getEndereco());
            clienteService.cadastrarCliente(cliente);
            return Response.status(Response.Status.CREATED).entity(clienteDto).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }



    @GET
    @Path("/clientes/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("email") String email) {
        try {
            Cliente cliente = clienteService.buscarClientePorEmail(email);
            if (cliente == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setNome(cliente.getNome());
            clienteDto.setEmail(cliente.getEmail());
            clienteDto.setSenha(cliente.getSenha());
            clienteDto.setNumero(cliente.getNumero());
            clienteDto.setEndereco(cliente.getEndereco());
            return Response.ok(clienteDto).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarLogin(ClienteDto clienteDto) {
        try {
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getEmail(), clienteDto.getSenha(), clienteDto.getNumero(), clienteDto.getEndereco());
            Cliente cliente_email = clienteService.buscarClientePorEmail(clienteDto.getEmail());
            if (cliente_email != null && cliente.getSenha().equals(clienteDto.getSenha())) {
                return Response.ok(clienteDto).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Email ou senha incorretos").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/clientes/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente(@PathParam("email") String email, ClienteDto clienteDto) {
        try {
            Cliente cliente = new Cliente(clienteDto.getNome(), email, clienteDto.getSenha(), clienteDto.getNumero(), clienteDto.getEndereco());
            clienteService.atualizarCliente(cliente);
            return Response.ok(clienteDto).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/clientes/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCliente(@PathParam("email") String email) {
        try {
            clienteService.removerCliente(email);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // CRUD de Carro

    @POST
    @Path("/carros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCarro(CarroDto carroDto) {
        try {
            Cliente cliente = clienteService.buscarClientePorEmail(carroDto.getEmailCliente());
            if (cliente == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Cliente não encontrado").build();
            }
            Carro carro = new Carro(carroDto.getAno(), carroDto.getChassi(), Marca.valueOf(carroDto.getMarca()), Modelo.valueOf(carroDto.getModelo()), cliente);
            carroServiceImpl.cadastrarCarro(carro);
            return Response.status(Response.Status.CREATED).entity(carroDto).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/carros/{chassi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarro(@PathParam("chassi") String chassi) {
        try {
            Carro carro = carroServiceImpl.buscarCarroPorChassi(chassi);
            if (carro == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            CarroDto carroDto = new CarroDto();
            carroDto.setAno(carro.getAno());
            carroDto.setChassi(carro.getNumero_chassi());
            carroDto.setMarca(carro.getMarca().name());
            carroDto.setModelo(carro.getModelo().name());
            carroDto.setEmailCliente(carro.getEmailCliente());
            return Response.ok(carroDto).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/carros/{chassi}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCarro(@PathParam("chassi") String chassi, CarroDto carroDto) {
        try {
            Cliente cliente = clienteService.buscarClientePorEmail(carroDto.getEmailCliente());
            if (cliente == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Cliente não encontrado").build();
            }
            Carro carro = new Carro(carroDto.getAno(), chassi, Marca.valueOf(carroDto.getMarca()), Modelo.valueOf(carroDto.getModelo()), cliente);
            carroServiceImpl.atualizarCarro(carro);
            return Response.ok(carroDto).build();
        } catch (SQLException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/carros/{chassi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCarro(@PathParam("chassi") String chassi) {
        try {
            carroServiceImpl.removerCarro(chassi);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }






    @GET
    @Path("/carros/email/{emailCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCarros(@PathParam("emailCliente") String emailCliente) {
        if (emailCliente == null || emailCliente.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Email do cliente é obrigatório").build();
        }
        try {
            List<Carro> carros = carroServiceImpl.listarCarros(emailCliente);
            if (carros.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Nenhum carro encontrado para o cliente").build();
            }
            List<CarroDto> carroDtos = carros.stream().map(carro -> {
                CarroDto dto = new CarroDto();
                dto.setAno(carro.getAno());
                dto.setChassi(carro.getNumero_chassi());
                dto.setMarca(carro.getMarca().name());
                dto.setModelo(carro.getModelo().name());
                dto.setEmailCliente(carro.getEmailCliente());
                return dto;
            }).collect(Collectors.toList());
            return Response.ok(carroDtos).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
