package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Pedido;
import br.com.textilsoft.model.PedidoProduto;
import br.com.textilsoft.model.Produto;
import br.com.textilsoft.model.util.StatusPedido;

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
	
	
	public int excluirpedido(long id_pedido) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM pedido_produto WHERE id_pedido = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id_pedido);
			linhasAlfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAlfetadas;
	}
	

	public List<PedidoProduto> listarpedidoprodutos(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM pedido inner join pedido_produto using(id_pedido) inner join produto using(id_produto) where id_pedido = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<PedidoProduto> pedidoproduto = new ArrayList<>();

			while (rs.next()) {
				pedidoproduto.add(parser(rs));
			}

			return pedidoproduto;
		} catch (SQLException e) {
			throw e;
		}
	}

	
	private PedidoProduto parser(ResultSet resultSet) throws SQLException {
		Pedido p = new Pedido();
		Produto r = new Produto();
		
		PedidoProduto pr = new PedidoProduto();
		
		
		r.setIdProduto(resultSet.getInt("id_produto"));
		r.setDescProduto(resultSet.getString("desc_produto"));
		r.setValorProduto(resultSet.getDouble("valor_produto"));
		
		p.setIdPedido(resultSet.getInt("id_pedido"));
		p.setQtdProd(resultSet.getInt("qtd_prod"));
		p.setValorTotalPedido(resultSet.getDouble("valor_total_pedido"));
		p.setDataPedido(resultSet.getDate("data_pedido"));
		p.setStatusPedido(StatusPedido.valueOf(resultSet.getString("status_pedido")));
		
		
		pr.setPedido(p);
		pr.setProduto(r);
		
		return pr;
	}
	
	
	
	
	
}
