package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Fornecedor;
import br.com.textilsoft.model.ServicoFornecedor;
import br.com.textilsoft.model.util.UndMedidaServForn;

public class ServicoFornecedorDAO {
	
	private final ConexaoJDBC conexao;

	public ServicoFornecedorDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	public Long inserir(ServicoFornecedor servicoFornecedor) throws SQLException, ClassNotFoundException {
		Long id = null;
		String sqlQuery = "INSERT INTO servico_fornecedor (nm_serv_forn, desc_serv_forn, valor_serv_forn, und_medida_serv_forn, id_fornecedor) VALUES (?, ?, ?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, servicoFornecedor.getNmServForn());
			stmt.setString(2, servicoFornecedor.getDescServForn());
			stmt.setDouble(3, servicoFornecedor.getValorServForn());
			stmt.setString(4, servicoFornecedor.getUndMedidaServForn().toString());
			stmt.setLong(5, servicoFornecedor.getIdFornecedor().getIdFornecedor());
			
			stmt.execute();
			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return id;
	}
	
	
	
	public int alterar(ServicoFornecedor servicoFornecedor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE servico_fornecedor SET nm_serv_forn = ?, desc_serv_forn = ?,"
				+ " valor_serv_forn = ?, und_medida_serv_forn = ?, id_fornecedor = ? WHERE id_serv_forn = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, servicoFornecedor.getNmServForn());
			stmt.setString(2, servicoFornecedor.getDescServForn());
			stmt.setDouble(3, servicoFornecedor.getValorServForn());
			stmt.setString(4, servicoFornecedor.getUndMedidaServForn().toString());
			stmt.setLong(5, servicoFornecedor.getIdFornecedor().getIdFornecedor());
			stmt.setLong(6, servicoFornecedor.getIdServForn());
			

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
		String sqlQuery = "DELETE FROM servico_fornecedor WHERE id_serv_forn = ?";

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
	
	public ServicoFornecedor selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM servico_fornecedor inner join fornecedor using(id_fornecedor) WHERE id_serv_forn = ?";

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
	
	public List<ServicoFornecedor> listarServicos() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM servico_fornecedor inner join fornecedor using(id_fornecedor) ORDER BY id_serv_forn";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<ServicoFornecedor> servicos = new ArrayList<>();

			while (rs.next()) {
				servicos.add(parser(rs));
			}

			return servicos;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<ServicoFornecedor> listarFornecedor(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM servico_fornecedor inner join fornecedor using(id_fornecedor) where id_fornecedor = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<ServicoFornecedor> servicos = new ArrayList<>();

			while (rs.next()) {
				servicos.add(parser(rs));
			}

			return servicos;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	private ServicoFornecedor parser(ResultSet resultSet) throws SQLException {
		ServicoFornecedor s = new ServicoFornecedor();
		Fornecedor f = new Fornecedor();
		f.setIdFornecedor(resultSet.getInt("id_fornecedor"));
		f.setNmFornecedor(resultSet.getString("nm_fornecedor"));
		f.setCnpjFornecedor(resultSet.getString("cnpj_fornecedor"));
		f.setEndFornecedor(resultSet.getString("end_fornecedor"));
		f.setCepFornecedor(resultSet.getInt("cep_fornecedor"));
		f.setBairroFornecedor(resultSet.getString("bairro_fornecedor"));
		f.setCidadeFornecedor(resultSet.getString("cidade_fornecedor"));
		f.setEstadoFornecedor(resultSet.getString("estado_fornecedor"));
		f.setCompFornecedor(resultSet.getString("comp_fornecedor"));
		f.setTelFornecedor(resultSet.getInt("tel_fornecedor"));
		f.setTel2Fornecedor(resultSet.getInt("tel2_fornecedor"));
		f.setEmailFornecedor(resultSet.getString("email_fornecedor"));	
		f.setDtCadFornecedor(resultSet.getDate("dt_cad_fornecedor"));
		
		s.setIdServForn(resultSet.getInt("id_serv_forn"));
		s.setNmServForn(resultSet.getString("nm_serv_forn"));
		s.setDescServForn(resultSet.getString("desc_serv_forn"));
		s.setValorServForn(resultSet.getDouble("valor_serv_forn"));
		s.setUndMedidaServForn(UndMedidaServForn.valueOf(resultSet.getString("und_medida_serv_forn")));		
		s.setIdFornecedor(f);

		return s;
	}
	
	
}
