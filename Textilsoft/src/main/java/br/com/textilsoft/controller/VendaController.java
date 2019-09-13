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


import br.com.textilsoft.dao.VendaDAO;
import br.com.textilsoft.model.Venda;

@Path("vendas")
public class VendaController {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Venda> listVendas() {
		try {
			VendaDAO vendaDAO = new VendaDAO ();
			return vendaDAO.listarvendas();
		} catch (Exception ex) {
			Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Venda getVenda(@PathParam("id") long id) {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			return vendaDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Venda venda) {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.inserir(venda);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Venda venda) {
		try {

			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.alterar(venda);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	
}
