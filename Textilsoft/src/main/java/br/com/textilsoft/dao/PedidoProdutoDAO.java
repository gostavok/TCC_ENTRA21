package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.PedidoProduto;

public class PedidoProdutoDAO {
	
	private final ConexaoJDBC conexao;

	public PedidoProdutoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	public void inserir(PedidoProduto pedidoproduto) throws SQLException, ClassNotFoundException {
		
		
		String sqlQuery = "INSERT INTO pedido_produto (id_pedido, id_produto) VALUES (?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, pedidoproduto.getPedido().getIdPedido());
			stmt.setLong(2, pedidoproduto.getProduto().getIdProduto());
			
			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}
	
	
	public int alterar(PedidoProduto pedidoproduto, long id_pedido, long id_produto) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE pedido_produto SET id_pedido = ?, id_produto= ? WHERE id_pedido = ? and id_produto = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, pedidoproduto.getPedido().getIdPedido());
			stmt.setLong(2, pedidoproduto.getProduto().getIdProduto());
			stmt.setDouble(3, id_pedido);
			stmt.setDouble(4, id_produto);
			

			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}
	
	
	public int excluir(long id_pedido, long id_produto) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM pedido_produto WHERE id_pedido = ? and id_produto = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id_pedido);
			stmt.setLong(2, id_produto);
			linhasAlfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAlfetadas;
	}
	
	

	

	
	
}
