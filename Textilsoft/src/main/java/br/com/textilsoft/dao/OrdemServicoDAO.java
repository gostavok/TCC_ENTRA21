package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Fornecedor;
import br.com.textilsoft.model.OrdemServico;
import br.com.textilsoft.model.ServicoFornecedor;
import br.com.textilsoft.model.util.StatusOrdemServico;

public class OrdemServicoDAO {

	private final ConexaoJDBC conexao;

	public OrdemServicoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	Locale vmLocale = Locale.getDefault();
	
	public Long inserir(OrdemServico ordemServico) throws SQLException, ClassNotFoundException {
		Timestamp hoje = new Timestamp(System.currentTimeMillis());
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
			stmt.setDate(6, new java.sql.Date(ordemServico.getDataEntregaOrdemServico().getDate()));
			stmt.setDouble(7, ordemServico.getValorTotalOrdemServico());			
						
			stmt.execute();
			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return ordemServico.getIdOrdem();
	}

	public int alterar(OrdemServico ordemServico) throws SQLException, ClassNotFoundException {
		
		Timestamp hoje = new Timestamp(System.currentTimeMillis());
		
		int linhasAfetadas = 0;
		String sqlQuery = 
				
		"UPDATE textilsoft.ordem_servico SET"+
		
		"id_fornecedor = ?, "+
		"id_serv_forn = ?, "+
		"qtd_servico = ?, "+
		"status_ordem = ?, "+
		"data_entrega =?, "+
		"valor_total =?, "+			

		"WHERE id_ordem = ?";
	
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, ordemServico.getFornecedor().getIdFornecedor());
			stmt.setLong(2, ordemServico.getServicoFornecedor().getIdServForn());
			stmt.setDouble(3, ordemServico.getQtdServico());
			stmt.setString(4, ordemServico.getStatusOrdem().toString());
			stmt.setDate(5, new java.sql.Date(ordemServico.getDataEntregaOrdemServico().getDate()));
			stmt.setDouble(6, ordemServico.getValorTotalOrdemServico());

			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}

	public int excluir(long idOrdemServico) throws SQLException, ClassNotFoundException {
		int linhasAfetadas = 0;
		String sqlQuery = "DELETE FROM textilsoft.ordem_servico WHERE id_ordem = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, idOrdemServico);
			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}

	public OrdemServico selecionar(long idOrdemServico) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM textilsoft.ordem_servico WHERE id_ordem = ?";

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
		String sqlQuery = "SELECT * FROM textilsoft.ordem_servico ORDER BY id_ordem";

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
		OrdemServico ordem = new OrdemServico();
		Fornecedor fornecedor = new Fornecedor();
		ServicoFornecedor sv = new ServicoFornecedor();		

		ordem.setIdOrdem(resultSet.getLong("id_ordem"));
		fornecedor.setIdFornecedor(resultSet.getLong("id_fornecedor"));
		sv.setIdServForn(resultSet.getInt("id_serv_forn"));
		ordem.setQtdServico(resultSet.getDouble("qtd_servico"));
		ordem.setStatusOrdem(StatusOrdemServico.valueOf(resultSet.getString("status_ordem")));
		ordem.setDataAberturaOrdemServico(resultSet.getDate("date_entrega"));
		ordem.setDataEntregaOrdemServico(resultSet.getDate("data_entrega"));
		ordem.setValorTotalOrdemServico(resultSet.getDouble("valor_total"));
				
		return ordem;
	}
}
