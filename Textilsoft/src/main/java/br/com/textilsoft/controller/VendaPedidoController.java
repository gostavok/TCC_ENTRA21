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

import br.com.textilsoft.dao.OrdemServicoDAO;
import br.com.textilsoft.dao.PedidoProdutoDAO;
import br.com.textilsoft.dao.VendaDAO;
import br.com.textilsoft.dao.VendaPedidoDAO;
import br.com.textilsoft.model.OrdemServico;
import br.com.textilsoft.model.PedidoProduto;
import br.com.textilsoft.model.Venda;
import br.com.textilsoft.model.VendaPedido;

@Path("vendaspedidos")
public class VendaPedidoController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public List<VendaPedido> listVendasPedidos(@PathParam("id") long id) {
		try {
			VendaPedidoDAO vendaPedidoDAO = new VendaPedidoDAO();
			return vendaPedidoDAO.listarVendasPedidos(id);
		} catch (Exception ex) {
			Logger.getLogger(VendaPedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(VendaPedido vendaPedido) {
		try {
			VendaPedidoDAO vendaPedidoDAO = new VendaPedidoDAO();
			vendaPedidoDAO.inserir(vendaPedido);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(VendaPedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{idvenda: .*}/{idpedido}/")
	public Response delete(@PathParam("idvenda") long idvenda, @PathParam("idpedido") long idpedido) {
		try {
			VendaPedidoDAO vendaPedidoDAO = new VendaPedidoDAO();
			vendaPedidoDAO.excluir(idvenda,idpedido);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("d{idvenda: .*}/")
	public Response deletetotal(@PathParam("idvenda") long idvenda) {
		try {
			VendaPedidoDAO vendaPedidoDAO = new VendaPedidoDAO();
			vendaPedidoDAO.excluirtotal(idvenda);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Venda venda) {
		try {

			VendaPedidoDAO VendaPedidoDAO = new VendaPedidoDAO();
			VendaPedidoDAO.alterar(venda);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception ex) {
			Logger.getLogger(VendaPedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	
}
