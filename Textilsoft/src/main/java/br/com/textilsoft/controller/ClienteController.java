package br.com.textilsoft.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.textilsoft.dao.ClienteDAO;
import br.com.textilsoft.model.Cliente;


@Path("clientes")
public class ClienteController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Cliente> listClientes() {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();
			return ClienteDAO.listar();
		} catch (Exception ex) {
			Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Cliente getCliente(@PathParam("id") long idCliente) {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();
			return ClienteDAO.selecionar(idCliente);
		} catch (Exception ex) {
			Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Cliente cliente) {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();
			ClienteDAO.inserir(cliente);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Cliente cliente) {
		try {

			ClienteDAO ClienteDAO = new ClienteDAO();
			ClienteDAO.alterar(cliente);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception ex) {
			Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long idCliente) {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();
			ClienteDAO.excluir(idCliente);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
