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

import br.com.textilsoft.dao.CorDAO;
import br.com.textilsoft.model.Cor;



@Path("Cores")
public class CorController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Cor> listCor() {
		try {
			CorDAO corDAO = new CorDAO();
			return corDAO.selectAll();
		} catch (Exception ex) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Cor selectCor(@PathParam("id") int id) {
		try {
			CorDAO corDao = new CorDAO();
			return corDao.select(id);
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response alterCor(Cor cor) {
		try {
			CorDAO corDAO = new CorDAO();
			corDAO.update(cor);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	} 

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertCor(Cor cor) {
		try {
			CorDAO corDao = new CorDAO();
			corDao.insert(cor);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}/")
	public Response deleteCor(@PathParam("id") int id) {
		try {
			CorDAO corDAO = new CorDAO();
			corDAO.delete(id);
			return Response.status(Response.Status.ACCEPTED).build();
				
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}



}

