package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.OrdemServico;

public class OrdemServicoDAO {

	private final ConexaoJDBC conexao;

	public OrdemServicoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	Locale vmLocale = Locale.getDefault();
	
	public Long inserir(OrdemServico ordemServico) throws SQLException, ClassNotFoundException {
		Long id = null;
		Timestamp hoje = new Timestamp(System.currentTimeMillis());
		java.sql.Date entregaSQL = convertUtilToSql(ordemServico.getDataEntregaOrdemServico());
		Date entrega = new Date();
		String sqlQuery = 
		
		"INSERT INTO textilsoft.cliente "+
		"(`id_fornecedor`, "+
		"`id_serv_forn`, "+
		"`qtd_servico`, "+
		"`status_ordem`, "+
		"`data_abertura`, "+
		"`data_entrega`, "+
		"`valor_total`) "+		
		"VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		Calendar.getInstance(vmLocale);
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, ordemServico.getFornecedor().getIdFornecedor());
			stmt.setLong(2, ordemServico.getServicoFornecedor().getIdServForn());
			stmt.setDouble(3, ordemServico.getQtdServico());
			stmt.setString(4, ordemServico.getStatusOrdem().toString());
			stmt.setTimestamp(5, hoje);	
			stmt.setDate(6, new java.sql.Date(ordemServico.getDataEntregaOrdemServico()));
			stmt.setString(7, ordemServico.getValorTotalOrdemServico());			
						
			stmt.execute();
			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return id;
	}

	public int alterar(OrdemServico ordemServico) throws SQLException, ClassNotFoundException {
		
		int linhasAfetadas = 0;
		String sqlQuery = 
				
		"UPDATE textilsoft.cliente SET"+
		
		"nm_cliente = ?,"+
		"cnpj_cliente = ?,"+
		"end_cliente = ?,"+
		"cep_cliente = ?,"+
		"bairro_cliente = ?,"+
		"cidade_cliente = ?,"+
		"estado_cliente = ?,"+
		"comp_cliente = ?,"+
		"tel_cliente = ?,"+
		"tel2_cliente = ?,"+
		"email_cliente = ?,"+

		"WHERE id_cliente = ?";
	
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, ordemServico.getNmCliente());
			stmt.setString(2, ordemServico.getCnpjCliente());
			stmt.setString(3, ordemServico.getEndCliente());
			stmt.setLong(4, ordemServico.getCepCliente());
			stmt.setString(5, ordemServico.getBairroCliente());
			stmt.setString(6, ordemServico.getCidadeCliente());
			stmt.setString(7, ordemServico.getEstadoCliente());
			stmt.setString(8, ordemServico.getCompCliente());
			stmt.setInt(9, ordemServico.getTelCliente1());
			stmt.setInt(10, ordemServico.getTelCliente2());
			stmt.setString(11, ordemServico.getEmailCliente());

			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}

	public int excluir(long idOrdemServico) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM textilsoft.cliente WHERE id_cliente = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, idOrdemServico);
			linhasAlfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAlfetadas;
	}

	public OrdemServico selecionar(long idOrdemServico) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM textilsoft.cliente WHERE id_cliente = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, idOrdemServico);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return parser(rs);
			}
		} catch (SQLException e) {
			throw e;
		}

		return null;
	}

	public List<OrdemServico> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM textilsoft.cliente ORDER BY id_cliente";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<OrdemServico> ordemServicos = new ArrayList<>();

			while (rs.next()) {
				ordemServicos.add(parser(rs));
			}

			return ordemServicos;
		} catch (SQLException e) {
			throw e;
		}
	}

	private OrdemServico parser(ResultSet resultSet) throws SQLException {
		OrdemServico c = new OrdemServico();

		c.setIdCliente(resultSet.getLong("id_cliente"));
		c.setNmCliente(resultSet.getString("nm_cliente"));
		c.setCnpjCliente(resultSet.getString("cnpj_cliente"));
		c.setEndCliente(resultSet.getString("end_cliente"));
		c.setCepCliente(resultSet.getInt("cep_cliente"));
		c.setBairroCliente(resultSet.getString("bairro_cliente"));
		c.setCidadeCliente(resultSet.getString("cidade_cliente"));
		c.setEstadoCliente(resultSet.getString("estado_cliente"));
		c.setCompCliente(resultSet.getString("comp_cliente"));
		c.setTelCliente1(resultSet.getInt("tel_cliente"));
		c.setTelCliente2(resultSet.getInt("tel2_cliente"));
		c.setEmailCliente(resultSet.getString("email_cliente"));
		
		return c;
	}
}
