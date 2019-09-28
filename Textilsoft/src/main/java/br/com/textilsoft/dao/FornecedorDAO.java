package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Fornecedor;



public class FornecedorDAO {

	private final ConexaoJDBC conexao;

	public FornecedorDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public void inserir(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		
		
		
		Date hoje = new Date();
		
		
		
		String sqlQuery = "INSERT INTO fornecedor (nm_fornecedor, cnpj_fornecedor, "
				+ "end_fornecedor, cep_fornecedor, bairro_fornecedor, cidade_fornecedor, "
				+ "estado_fornecedor, comp_fornecedor, tel_fornecedor, tel2_fornecedor, "
				+ "email_fornecedor, dt_cad_fornecedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, fornecedor.getNmFornecedor());
			stmt.setString(2, fornecedor.getCnpjFornecedor());
			stmt.setString(3, fornecedor.getEndFornecedor());
			stmt.setLong(4, fornecedor.getCepFornecedor());
			stmt.setString(5, fornecedor.getBairroFornecedor());
			stmt.setString(6, fornecedor.getCidadeFornecedor());
			stmt.setString(7, fornecedor.getEstadoFornecedor());
			stmt.setString(8, fornecedor.getCompFornecedor());
			stmt.setLong(9, fornecedor.getTelFornecedor());
			stmt.setLong(10, fornecedor.getTel2Fornecedor());
			stmt.setString(11, fornecedor.getEmailFornecedor());
			stmt.setTimestamp(12, new java.sql.Timestamp(hoje.getTime()));	

			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}
	
	public int alterar(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE fornecedor SET nm_fornecedor = ?, cnpj_fornecedor = ?, end_fornecedor = ?,"
				+ "cep_fornecedor = ?, bairro_fornecedor = ?, cidade_fornecedor = ?, estado_fornecedor = ?, "
				+ "comp_fornecedor = ?, tel_fornecedor = ?, tel2_fornecedor = ?, "
				+ "email_fornecedor = ?  WHERE id_fornecedor = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
			
			stmt.setString(1, fornecedor.getNmFornecedor());
			stmt.setString(2, fornecedor.getCnpjFornecedor());
			stmt.setString(3, fornecedor.getEndFornecedor());
			stmt.setLong(4, fornecedor.getCepFornecedor());
			stmt.setString(5, fornecedor.getBairroFornecedor());
			stmt.setString(6, fornecedor.getCidadeFornecedor());
			stmt.setString(7, fornecedor.getEstadoFornecedor());
			stmt.setString(8, fornecedor.getCompFornecedor());
			stmt.setLong(9, fornecedor.getTelFornecedor());
			stmt.setLong(10, fornecedor.getTel2Fornecedor());
			stmt.setString(11, fornecedor.getEmailFornecedor());		
			stmt.setLong(12, fornecedor.getIdFornecedor());
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
		String sqlQuery = "DELETE FROM fornecedor WHERE id_fornecedor = ?";

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

	public Fornecedor selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT *,date_format(`dt_cad_fornecedor`,'%d/%m/%Y') FROM fornecedor WHERE id_fornecedor = ?";

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

	public List<Fornecedor> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT *,date_format(`dt_cad_fornecedor`,'%d/%m/%Y') FROM fornecedor ORDER BY id_fornecedor";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Fornecedor> fornecedores = new ArrayList<>();

			while (rs.next()) {
				fornecedores.add(parser(rs));
			}

			return fornecedores;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Fornecedor parser(ResultSet resultSet) throws SQLException {
		
		
		
		Fornecedor f = new Fornecedor();
		f.setIdFornecedor(resultSet.getLong("id_fornecedor"));
		f.setNmFornecedor(resultSet.getString("nm_fornecedor"));
		f.setCnpjFornecedor(resultSet.getString("cnpj_fornecedor"));
		f.setEndFornecedor(resultSet.getString("end_fornecedor"));
		f.setCepFornecedor(resultSet.getInt("cep_fornecedor"));
		f.setBairroFornecedor(resultSet.getString("bairro_fornecedor"));
		f.setCidadeFornecedor(resultSet.getString("cidade_fornecedor"));
		f.setEstadoFornecedor(resultSet.getString("estado_fornecedor"));
		f.setCompFornecedor(resultSet.getString("comp_fornecedor"));
		f.setTelFornecedor(resultSet.getLong("tel_fornecedor"));
		f.setTel2Fornecedor(resultSet.getLong("tel2_fornecedor"));
		f.setEmailFornecedor(resultSet.getString("email_fornecedor"));		
		f.setDtCadFornecedor(resultSet.getDate("dt_cad_fornecedor"));	
	
		
		return f;
	}
}
