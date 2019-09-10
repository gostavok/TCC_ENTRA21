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

import br.com.textilsoft.dao.MaterialDAO;
import br.com.textilsoft.model.Material;

@Path("materiais")
public class MaterialController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Material> listMaterial() {
		try {
			MaterialDAO materialDao = new MaterialDAO();
			return materialDao.selectAll();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Material selectMateriala(@PathParam("id")int id) {
		try {
		MaterialDAO materialDao = new MaterialDAO();
		return materialDao.select(id);
		} catch (Exception e) {
		Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
		throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response updateMaterial(Material material) {
		try {
		MaterialDAO materialDao = new MaterialDAO();
		materialDao.update(material);
		return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
		Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
		throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertMaterial(Material material) {
		try {
		MaterialDAO materialDao = new MaterialDAO();
		materialDao.insert(material);
		return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
		Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
		throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response deleteMateriala(@PathParam("id")int id) {
		try {
		MaterialDAO materialDao = new MaterialDAO();
		materialDao.delete(id);
		return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
		Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
		throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
}