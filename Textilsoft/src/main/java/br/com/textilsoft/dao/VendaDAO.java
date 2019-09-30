package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Venda;

public class VendaDAO {
	
	
	private final ConexaoJDBC conexao;

	public VendaDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	
	public void inserir(Venda venda) throws SQLException, ClassNotFoundException {
		
		
		String sqlQuery = "INSERT INTO venda "
				+ "( valor_total, "
				+ "data_pagamento, "
				+ "data_venda) "
				+ "VALUES (?, ?, ?) ";

		try {
			Timestamp hoje = new Timestamp(System.currentTimeMillis());
			
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDouble(1, 0);
			stmt.setDate(2, new java.sql.Date(venda.getDataPagamento().getTime()));
			stmt.setTimestamp(3, hoje);
			
			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}
	
	public int alterar(Venda venda) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE venda SET valor_total = ?, data_pagamento= ? WHERE id_venda = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setDouble(1, venda.getValorTotal());
			stmt.setTimestamp(2, new java.sql.Timestamp( venda.getDataPagamento().getTime()));
			stmt.setLong(3, venda.getIdVenda());

			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}
	
	
	public int excluir(long id) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM venda WHERE id_venda = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			linhasAlfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAlfetadas;
	}
	
	
	
	public Venda selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM venda inner join venda_pedido using(id_venda) inner join pedido using(id_pedido) WHERE id_venda = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return parser(rs);
			}
		} catch (SQLException e) {
			throw e;
		}

		return null;
	}
	
	
	public List<Venda> listarvendas() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM venda ORDER BY id_venda";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Venda> vendas = new ArrayList<>();

			while (rs.next()) {
				vendas.add(parser(rs));
			}

			return vendas;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	private Venda parser(ResultSet resultSet) throws SQLException {
		Venda v = new Venda();
		
		v.setIdVenda(resultSet.getInt("id_venda"));
		v.setValorTotal(resultSet.getDouble("valor_total"));
		v.setDataPagamento(resultSet.getDate("data_pagamento"));
		v.setDataVenda(resultSet.getDate("data_venda"));
		
		return v;
	}
	
	
}
