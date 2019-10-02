package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.ContaReceber;
import br.com.textilsoft.model.SituacaoContaReceber;
import br.com.textilsoft.model.Venda;
import br.com.textilsoft.model.util.StatusContaReceber;



public class ContaReceberDAO {

	private final ConexaoJDBC conexao;

	public ContaReceberDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public void inserir(ContaReceber contaReceber) throws SQLException, ClassNotFoundException {	
		
		
		
		String sqlQuery = "INSERT INTO conta_receber (id_venda , status_conta_receber) VALUES (?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		
			stmt.setLong(1, contaReceber.getVenda().getIdVenda());			
			stmt.setString(2, contaReceber.getStatusContaReceber().toString());
			
			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		alterar();
	}
	
	public int alterar(ContaReceber contaReceber) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE conta_receber SET id_venda = ?, status_conta_receber = ?"
				+ "  WHERE id_conta_receber = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
			
			stmt.setLong(1, contaReceber.getVenda().getIdVenda());
			stmt.setString(2, contaReceber.getStatusContaReceber().toString());				
			stmt.setLong(3, contaReceber.getIdContaReceber());
			
			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		alterar();
		return linhasAfetadas;
		
	}
	
	public void alterar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE conta_receber INNER JOIN venda USING(id_venda)\r\n" + 
				"	SET\r\n" + 
				"		status_conta_receber = 'Atrasado'\r\n" + 
				"	WHERE venda.data_pagamento < CURDATE() AND status_conta_receber != 'Pago'";
		

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
			
		
			
			stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
	}

	public int excluir(long id) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM conta_receber WHERE id_conta_receber = ?";

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

	public ContaReceber selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM conta_receber inner join venda using(id_venda) WHERE id_conta_receber = ?";

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

	public List<ContaReceber> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM conta_receber ORDER BY id_conta_receber";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<ContaReceber> contasReceber = new ArrayList<>();

			while (rs.next()) {
				contasReceber.add(parser(rs));
			}
			
			return contasReceber;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	public double pegarTotalPago() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT SUM(venda.valor_total_venda) AS total FROM venda INNER JOIN conta_receber USING(id_venda) WHERE status_conta_receber = 'Pago'";
		double aux = 0;
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();			
			if (rs.next()) {				
				 aux = rs.getDouble("total");				
			}
			
			
			return aux;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public double pegarTotalPendente() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT SUM(venda.valor_total_venda) AS total FROM venda INNER JOIN conta_receber USING(id_venda) WHERE status_conta_receber = 'Pendente'";
		double aux = 0;
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();			
			if (rs.next()) {				
				 aux = rs.getDouble("total");				
			}
			
			
			return aux;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public double pegarTotalAtrasado() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT SUM(venda.valor_total_venda) AS total FROM venda INNER JOIN conta_receber USING(id_venda) WHERE status_conta_receber = 'Atrasado'";
		double aux = 0;
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();			
			if (rs.next()) {				
				 aux = rs.getDouble("total");				
			}
			
		
			return aux;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<String> listarStatus() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT status_conta_receber AS situacao, COUNT(*) AS quantidade\r\n" + 
				"  FROM conta_receber\r\n" + 
				" WHERE status_conta_receber IN ('pago', 'pendente', 'atrasado')\r\n" + 
				" GROUP BY situacao";
		String aux = "{";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<String> situacao = new ArrayList<>();
			
			while (rs.next()) {
				 if(rs.next())
				 aux = rs.getString("quantidade");
				 if (aux.isBlank()) aux= "a";
				 situacao.add(aux);	
			}
		
			alterar();
			return situacao;
			
		} catch (SQLException e) {
			throw e;
		}
	
	}
	
	public List<ContaReceber> listarPorStatus(String status) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM conta_receber INNER JOIN venda using(id_venda) WHERE status_conta_receber= ?   ORDER BY id_conta_receber ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, status);
			ResultSet rs = stmt.executeQuery();

			List<ContaReceber> contasReceber = new ArrayList<>();

			while (rs.next()) {
				contasReceber.add(parser(rs));
			}

			return contasReceber;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	public List<SituacaoContaReceber> listarStatusObj() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT status_conta_receber AS situacao, COUNT(*) AS quantidade\r\n" + 
				"  FROM conta_receber\r\n" + 
				" WHERE status_conta_receber IN ('pago', 'pendente', 'atrasado')\r\n" + 
				" GROUP BY situacao";
	
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<SituacaoContaReceber> situacao = new ArrayList<>();
			
			while (rs.next()) {				
				 situacao.add(parserStatus(rs));	
			}
		
			alterar();
			return situacao;
			
		} catch (SQLException e) {
			throw e;
		}
	
	}
	
	private SituacaoContaReceber parserStatus(ResultSet resultSet) throws SQLException {		
	SituacaoContaReceber scr = new SituacaoContaReceber();		
	
		scr.setStatus(resultSet.getString("situacao"));
		scr.setQuantidade(resultSet.getInt("quantidade"));	
		
		return scr;
	}
	
	
	
	private ContaReceber parser(ResultSet resultSet) throws SQLException {		
		
		ContaReceber cr = new ContaReceber();		
		Venda v = new Venda();
		v.setIdVenda(resultSet.getInt("id_venda" ));
		v.setValorTotal(resultSet.getDouble("valor_total_venda"));
		v.setDataPagamento(resultSet.getDate("data_pagamento"));
		v.setDataVenda(resultSet.getDate("data_venda"));
		
		cr.setIdContaReceber(resultSet.getLong("id_conta_receber"));
		cr.setVenda(v);
		cr.setStatusContaReceber(StatusContaReceber.valueOf(resultSet.getString("status_conta_receber")));
		
	
		
		return cr;
	}
}
