package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.ContaPagar;
import br.com.textilsoft.model.util.StatusContaPagar;



public class ContaPagarDAO {

	private final ConexaoJDBC conexao;

	public ContaPagarDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public void inserir(ContaPagar contaPagar) throws SQLException, ClassNotFoundException {		
		
		
		String sqlQuery = "INSERT INTO conta_pagar (desc_conta_pagar, complemento, "
				+ "valor_conta_pagar, data_vencimento, data_inclusao, status_conta_pagar)"
				+ " VALUES (?, ?, ?, ?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, contaPagar.getDescContaPagar());
			stmt.setString(2, contaPagar.getComplemento());
			stmt.setDouble(3, contaPagar.getValorContaPagar());
			stmt.setDate(4, new java.sql.Date(contaPagar.getDataVencimento().getTime()));				
			stmt.setTimestamp(5, new java.sql.Timestamp(contaPagar.getDataInclusao().getTime()));
			stmt.setString(6, contaPagar.getStatusContaPagar().toString());

			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}
	
	public int alterar(ContaPagar contaPagar) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE conta_pagar SET desc_conta_pagar = ?, complemento = ?,"
				+ " valor_conta_pagar = ?, data_vencimento = ?, status_conta_pagar = ?"
				+ "  WHERE id_conta_pagar = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, contaPagar.getDescContaPagar());
			stmt.setString(2, contaPagar.getComplemento());
			stmt.setDouble(3, contaPagar.getValorContaPagar());
			stmt.setDate(4, new java.sql.Date(contaPagar.getDataVencimento().getTime()));			
			stmt.setString(5, contaPagar.getStatusContaPagar().toString());
			stmt.setLong(6, contaPagar.getIdContaPagar());
			
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
		String sqlQuery = "DELETE FROM conta_pagar WHERE id_conta_pagar = ?";

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

	public ContaPagar selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM conta_pagar WHERE id_conta_pagar = ?";

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

	public List<ContaPagar> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM conta_pagar ORDER BY id_conta_pagar";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<ContaPagar> contasPagar = new ArrayList<>();

			while (rs.next()) {
				contasPagar.add(parser(rs));
			}

			return contasPagar;
		} catch (SQLException e) {
			throw e;
		}
	}

	private ContaPagar parser(ResultSet resultSet) throws SQLException {
		
		
		
		ContaPagar cp = new ContaPagar();
		
		cp.setIdContaPagar(resultSet.getLong("id_conta_pagar"));
		cp.setDescContaPagar(resultSet.getString("desc_conta_pagar"));
		cp.setComplemento(resultSet.getString("complemento"));
		cp.setValorContaPagar(resultSet.getDouble("valor_conta_pagar"));
		cp.setDataInclusao(resultSet.getDate("data_inclusao"));
		cp.setDataVencimento(resultSet.getDate("data_vencimento"));		
		cp.setStatusContaPagar(StatusContaPagar.valueOf(resultSet.getString("status_conta_pagar")));
	
		
		return cp;
	}
}
