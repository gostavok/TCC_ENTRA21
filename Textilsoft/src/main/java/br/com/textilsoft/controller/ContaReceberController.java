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

import br.com.textilsoft.dao.ContaReceberDAO;
import br.com.textilsoft.model.ContaReceber;


@Path("contasreceber")
public class ContaReceberController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<ContaReceber> listaContasPagar() {
		try {
			ContaReceberDAO ContaReceberDAO = new ContaReceberDAO();
			return ContaReceberDAO.listar();
		} catch (Exception ex) {
			Logger.getLogger(ContaReceberController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status/")
	public List<String> listarSituacao() {
		try {
			ContaReceberDAO ContaReceberDAO = new ContaReceberDAO();
			return ContaReceberDAO.listarStatus();
		} catch (Exception ex) {
			Logger.getLogger(ContaReceberController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public ContaReceber getFornecedor(@PathParam("id") long id) {
		try {
			ContaReceberDAO ContaReceberDAO = new ContaReceberDAO();
			return ContaReceberDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(ContaReceberController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(ContaReceber contaReceber) {
		try {
			ContaReceberDAO ContaReceberDAO = new ContaReceberDAO();
			ContaReceberDAO.inserir(contaReceber);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(ContaReceberController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(ContaReceber contaReceber) {
		try {

			ContaReceberDAO ContaReceberDAO = new ContaReceberDAO();
			ContaReceberDAO.alterar(contaReceber);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(ContaReceberController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			ContaReceberDAO ContaReceberDAO = new ContaReceberDAO();
			ContaReceberDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(ContaReceberController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
