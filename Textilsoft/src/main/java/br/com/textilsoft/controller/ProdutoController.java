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

import br.com.textilsoft.dao.ProdutoDAO;
import br.com.textilsoft.model.Produto;

@Path("Produtos")
public class ProdutoController {


	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Produto> listProduto(){
		try {
			ProdutoDAO produtoDao =  new ProdutoDAO();
			return produtoDao.selectAll();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Produto selectProduto(@PathParam("id") int id) {
		try {
			ProdutoDAO produtoDao =  new ProdutoDAO();
			return produtoDao.select(id);
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response insertProduto(Produto produto) {
		try {
			ProdutoDAO produtoDao =  new ProdutoDAO();
			produtoDao.insert(produto);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Response updateProduto(Produto produto) {
		try {
			ProdutoDAO produtoDao =  new ProdutoDAO();
			produtoDao.update(produto);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}/")
	public Response deleteProduto(@PathParam("id") int id) {
		try {
			ProdutoDAO produtoDao =  new ProdutoDAO();
			produtoDao.delete(id);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception e) {
			Logger.getLogger(CorController.class.getName()).log(Level.SEVERE, null, e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}

