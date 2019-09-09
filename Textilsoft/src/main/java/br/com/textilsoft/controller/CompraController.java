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

import br.com.textilsoft.dao.CompraDAO;
import br.com.textilsoft.model.Compra;

@Path("compras")
public class CompraController {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Compra> listcompras() {
		try {
			CompraDAO compraDAO = new CompraDAO();
			return compraDAO.listarcompras();

		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Compra getcompra(@PathParam("id") long id) {
		try {
			CompraDAO compraDAO = new CompraDAO();
			return compraDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Compra compra) {
		try {
			CompraDAO compraDAO = new CompraDAO();
			compraDAO.inserir(compra);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Compra compra) {
		try {

			CompraDAO compraDAO = new CompraDAO();
			compraDAO.alterar(compra);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			CompraDAO compraDAO = new CompraDAO();
			compraDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
}
