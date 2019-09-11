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

import br.com.textilsoft.dao.OrcamentoDAO;
import br.com.textilsoft.model.Orcamento;

@Path("orcamentos")
public class OrcamentoController {

	

	
	//Não esta funcionando
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Orcamento> listOrcamento(){
		try {
			OrcamentoDAO orcamentoDao = new OrcamentoDAO();
			return orcamentoDao.selectAll();
		} catch (Exception e) {
			Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Orcamento selectOrcamento(@PathParam("id") int id) {
		try {
			OrcamentoDAO orcamentoDao = new OrcamentoDAO();
			return orcamentoDao.select(id);
		} catch (Exception e) {
			Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertOrcamento(Orcamento orcamento) {
		try {
			OrcamentoDAO orcamentoDao = new OrcamentoDAO();
			orcamentoDao.insert(orcamento);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response updateOrcamento(Orcamento orcamento) {
		try {
			OrcamentoDAO orcamentoDao = new OrcamentoDAO();
			orcamentoDao.update(orcamento);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	

	@DELETE
	@Path("{id}/")
	public Response deleteOrcamento(@PathParam("id") int id){
		try {
			OrcamentoDAO orcamentoDao = new OrcamentoDAO();
			orcamentoDao.delete(id);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(OrcamentoController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
















}
