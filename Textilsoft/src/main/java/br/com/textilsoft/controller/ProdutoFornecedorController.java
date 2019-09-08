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


import br.com.textilsoft.dao.ProdutoFornecedorDAO;
import br.com.textilsoft.model.ProdutoFornecedor;

@Path("produtosfornecedores")
public class ProdutoFornecedorController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<ProdutoFornecedor> listProdutoFornecedor() {
		try {
			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			return produtoFornecedorDAO.listarProdutos();

		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(ProdutoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public ProdutoFornecedor getprodutofornecedor(@PathParam("id") long id) {
		try {
			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			return produtoFornecedorDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(ProdutoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("f{id}/")
	public List<ProdutoFornecedor> getfornecedorprodutos(@PathParam("id") long id) {
		try {
			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			return produtoFornecedorDAO.listarFornecedor(id);
		} catch (Exception ex) {
			Logger.getLogger(ProdutoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(ProdutoFornecedor produtoFornecedor) {
		try {
			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			produtoFornecedorDAO.inserir(produtoFornecedor);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(ProdutoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(ProdutoFornecedor produtofornecedor) {
		try {

			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			produtoFornecedorDAO.alterar(produtofornecedor);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(ProdutoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
			produtoFornecedorDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(ProdutoFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
