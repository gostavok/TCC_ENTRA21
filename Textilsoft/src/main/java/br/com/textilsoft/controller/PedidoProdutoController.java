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

import br.com.textilsoft.dao.FornecedorDAO;
import br.com.textilsoft.dao.PedidoDAO;
import br.com.textilsoft.dao.PedidoProdutoDAO;
import br.com.textilsoft.dao.ProdutoFornecedorDAO;
import br.com.textilsoft.model.Fornecedor;
import br.com.textilsoft.model.PedidoProduto;

@Path("pedidosprodutos")
public class PedidoProdutoController {
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public List<PedidoProduto> getpedidoprodutos(@PathParam("id") long id) {
		try {
			PedidoProdutoDAO pedidoprodutoDAO = new PedidoProdutoDAO();
			return pedidoprodutoDAO.listarpedidoprodutos(id);
		} catch (Exception ex) {
			Logger.getLogger(PedidoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(PedidoProduto pedidoproduto) {
		try {
			PedidoProdutoDAO pedidoprodutoDAO = new PedidoProdutoDAO();
			pedidoprodutoDAO.inserir(pedidoproduto);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{idpedido: .*}/{idproduto}/")
	public Response delete(@PathParam("idpedido") long idpedido, @PathParam("idproduto") long idproduto) {
		try {
			PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO();
			pedidoProdutoDAO.excluir(idpedido,idproduto);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO();
			pedidoProdutoDAO.excluirpedido(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoProdutoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
