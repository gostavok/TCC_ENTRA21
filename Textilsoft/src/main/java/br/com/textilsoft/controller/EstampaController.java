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

import br.com.textilsoft.dao.EstampaDAO;
import br.com.textilsoft.model.Estampa;

@Path("estampas")
public class EstampaController {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Estampa> listEstampa(){
	try {
		EstampaDAO estampaDao = new EstampaDAO();
		return estampaDao.selectAll();
	} catch (Exception e) {
		Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
		throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Estampa selectEstampa(@PathParam("id") int id) {
		try {
			EstampaDAO estampaDao = new EstampaDAO();
			return estampaDao.select(id);	
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertEstampa(Estampa estampa) {
		try {
			EstampaDAO estampaDao = new EstampaDAO();
			estampaDao.insert(estampa);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response updateEstampa(Estampa estampa) {
		try {
			EstampaDAO estampaDao = new EstampaDAO();
			estampaDao.update(estampa);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Response deleteEstampa(@PathParam("id") int id) {
		try {
			EstampaDAO estampaDao = new EstampaDAO();
			estampaDao.delete(id);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
