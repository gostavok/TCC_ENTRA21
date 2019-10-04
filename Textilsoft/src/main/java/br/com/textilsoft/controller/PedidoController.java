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


import br.com.textilsoft.dao.PedidoDAO;
import br.com.textilsoft.model.Pedido;


@Path("pedidos")
public class PedidoController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Pedido> listpedidos() {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			return pedidoDAO.listarpedidos();

		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Pedido getpedido(@PathParam("id") long id) {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			return pedidoDAO.selecionar(id);
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ultimo/")
	public Pedido returnPedidoId() {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			return pedidoDAO.listarPedidoId();

		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("c{id}/")
	public List<Pedido> getpedidoscliente(@PathParam("id") long id) {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			return pedidoDAO.listarpedidoscliente(id);
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Pedido pedido) {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.inserir(pedido);

			return Response.status(Response.Status.CREATED).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Pedido pedido) {
		try {

			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.alterar(pedido);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
