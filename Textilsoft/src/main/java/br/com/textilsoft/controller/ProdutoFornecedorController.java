package br.com.textilsoft.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.textilsoft.model.ProdutoFornecedor;

@Path("alunos")
public class ProdutoFornecedorController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<ProdutoFornecedor> listProdutoFornecedor() {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			return alunoDAO.listar();

		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
