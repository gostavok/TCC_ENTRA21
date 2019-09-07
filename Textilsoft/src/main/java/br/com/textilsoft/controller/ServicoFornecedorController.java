package br.com.textilsoft.controller;

import java.sql.SQLException;
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


import br.com.textilsoft.dao.ServicoFornecedorDAO;
import br.com.textilsoft.model.ServicoFornecedor;

@Path("servicofornecedor")
public class ServicoFornecedorController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<ServicoFornecedor> listServicoFornecedor() {
		try {
			ServicoFornecedorDAO servicoFornecedorDAO = new ServicoFornecedorDAO();
			return servicoFornecedorDAO.listarServicos();

		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(ServicoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public ServicoFornecedor getservicofornecedor(@PathParam("id") long id) {
		try {
			ServicoFornecedorDAO servicoFornecedorDAO = new ServicoFornecedorDAO();
			return servicoFornecedorDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(ServicoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("f{id}/")
	public List<ServicoFornecedor> getfornecedorservicos(@PathParam("id") long id) {
		try {
			ServicoFornecedorDAO servicoFornecedorDAO = new ServicoFornecedorDAO();
			return servicoFornecedorDAO.listarFornecedor(id);
		} catch (Exception ex) {
			Logger.getLogger(ServicoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(ServicoFornecedor servicoFornecedor) {
		try {
			ServicoFornecedorDAO servicoFornecedorDAO = new ServicoFornecedorDAO();
			servicoFornecedorDAO.inserir(servicoFornecedor);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(ServicoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(ServicoFornecedor servicofornecedor) {
		try {

			ServicoFornecedorDAO servicoFornecedorDAO = new ServicoFornecedorDAO();
			servicoFornecedorDAO.alterar(servicofornecedor);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(ServicoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			ServicoFornecedorDAO servicoFornecedorDAO = new ServicoFornecedorDAO();
			servicoFornecedorDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(ServicoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
