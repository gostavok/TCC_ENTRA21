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

import br.com.textilsoft.dao.EstoqueDAO;
import br.com.textilsoft.model.Estoque;


@Path("estoques")
public class EstoqueController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Estoque> listEstoque() {
		try {
			EstoqueDAO EstoqueDAO = new EstoqueDAO();
			return EstoqueDAO.listar();
		} catch (Exception ex) {
			Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Estoque getEstoque(@PathParam("id") long id) {
		try {
			EstoqueDAO EstoqueDAO = new EstoqueDAO();
			return EstoqueDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("p/{nm_prod_forn}/")
	public List<Estoque> buscarPorNome(@PathParam("nm_prod_forn") String palavra) {
		try {
			EstoqueDAO EstoqueDAO = new EstoqueDAO();
			return EstoqueDAO.buscarPorNome(palavra);
		} catch (Exception ex) {
			Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Estoque estoque) {
		try {
			EstoqueDAO EstoqueDAO = new EstoqueDAO();
			EstoqueDAO.inserir(estoque);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Estoque estoque) {
		try {

			EstoqueDAO EstoqueDAO = new EstoqueDAO();
			EstoqueDAO.alterar(estoque);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			EstoqueDAO EstoqueDAO = new EstoqueDAO();
			EstoqueDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
