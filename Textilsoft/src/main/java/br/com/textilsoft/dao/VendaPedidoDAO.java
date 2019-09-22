package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.PedidoProduto;
import br.com.textilsoft.model.VendaPedido;

public class VendaPedidoDAO {
	
	private final ConexaoJDBC conexao;

	public VendaPedidoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	
	
public void inserir(VendaPedido vendapedido) throws SQLException, ClassNotFoundException {
		
		
		String sqlQuery = "INSERT INTO venda_pedido (id_venda, id_pedido) VALUES (?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, vendapedido.getVenda().getIdVenda());
			stmt.setLong(2, vendapedido.getPedido().getIdPedido());
			
			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}
	
	
public int alterar(VendaPedido vendapedido, long id_venda, long id_pedido) throws SQLException, ClassNotFoundException {
	String sqlQuery = "UPDATE venda_pedido SET id_venda = ?, id_pedido = ? WHERE id_venda = ? and id_pedido = ?";
	int linhasAfetadas = 0;

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		stmt.setLong(1, vendapedido.getVenda().getIdVenda());
		stmt.setLong(2, vendapedido.getPedido().getIdPedido());
		stmt.setDouble(3, id_venda);
		stmt.setDouble(4, id_pedido);
		

		linhasAfetadas = stmt.executeUpdate();
		this.conexao.commit();
	} catch (SQLException e) {
		this.conexao.rollback();
		throw e;
	}

	return linhasAfetadas;
}



public int excluir(long id_venda, long id_pedido) throws SQLException, ClassNotFoundException {
	int linhasAlfetadas = 0;
	String sqlQuery = "DELETE FROM venda_pedido WHERE id_venda = ? and id_pedido = ?";

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		stmt.setLong(1, id_venda);
		stmt.setLong(2, id_pedido);
		linhasAlfetadas = stmt.executeUpdate();
		this.conexao.commit();
	} catch (SQLException e) {
		this.conexao.rollback();
		throw e;
	}

	return linhasAlfetadas;
}

}
