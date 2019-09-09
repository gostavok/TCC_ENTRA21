package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Compra;
import br.com.textilsoft.model.Estoque;
import br.com.textilsoft.model.Fornecedor;
import br.com.textilsoft.model.ProdutoFornecedor;
import br.com.textilsoft.model.util.UndMedidaProdForn;


public class EstoqueDAO {

	private final ConexaoJDBC conexao;

	public EstoqueDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public void inserir(Estoque estoque) throws SQLException, ClassNotFoundException {
		
		//java.util.Date now = new java.util.Date();
		
		String sqlQuery = "INSERT INTO estoque (id_prod_forn, qtd_estoque, data_registro) + VALUES (?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, estoque.getProdutoFornecedor().getIdProdForn());
			stmt.setDouble(2, estoque.getQtdEstoque());					
			stmt.setTimestamp(3, new java.sql.Timestamp(estoque.getDataRegistro().getTime()));	
			
			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}	
	
	public int alterar(Estoque estoque) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE estoque SET id_prod_forn = ?, qtd_estoque = ?, data_registro = ? WHERE id_estoque = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);			
			
			stmt.setLong(1, estoque.getProdutoFornecedor().getIdProdForn());
			stmt.setDouble(2, estoque.getQtdEstoque());					
			stmt.setTimestamp(3, new java.sql.Timestamp(estoque.getDataRegistro().getTime()));		
			stmt.setLong(4, estoque.getIdEstoque());
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
		String sqlQuery = "DELETE FROM estoque WHERE id_estoque = ?";

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

	public Estoque selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM estoque inner join produto_fornecedor using(id_prod_forn) WHERE id_estoque = ?";

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
	
	public List<Estoque> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM estoque inner join produto_fornecedor using(id_prod_forn) ORDER BY id_estoque";		

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Estoque> estoque = new ArrayList<>();

			while (rs.next()) {
				estoque.add(parser(rs));
			}

			return estoque;
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	
	
	public List<Estoque> buscarPorNome(String palavra) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM estoque inner join produto_fornecedor using(id_prod_forn) WHERE nm_prod_forn LIKE ?";	

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, '%' +palavra+'%');
			ResultSet rs = stmt.executeQuery();
			
			List<Estoque> estoque = new ArrayList<>();

			while (rs.next()) {
				estoque.add(parser(rs));
			}

			return estoque;
		} catch (SQLException e) {
			throw e;
		}		
	}
	
	

	private Estoque parser(ResultSet resultSet) throws SQLException {
		Estoque e = new Estoque();
		ProdutoFornecedor p = new ProdutoFornecedor();
		Fornecedor f = new Fornecedor();	
		
		f.setIdFornecedor(resultSet.getLong("id_fornecedor"));
//		f.setNmFornecedor(resultSet.getString("nm_fornecedor"));
//		f.setCnpjFornecedor(resultSet.getString("cnpj_fornecedor"));
//		f.setEndFornecedor(resultSet.getString("end_fornecedor"));
//		f.setCepFornecedor(resultSet.getInt("cep_fornecedor"));
//		f.setBairroFornecedor(resultSet.getString("bairro_fornecedor"));
//		f.setCidadeFornecedor(resultSet.getString("cidade_fornecedor"));
//		f.setEstadoFornecedor(resultSet.getString("estado_fornecedor"));
//		f.setCompFornecedor(resultSet.getString("comp_fornecedor"));
//		f.setTelFornecedor(resultSet.getInt("tel_fornecedor"));
//		f.setTel2Fornecedor(resultSet.getInt("tel2_fornecedor"));
//		f.setEmailFornecedor(resultSet.getString("email_fornecedor"));	
//		f.setDtCadFornecedor(resultSet.getDate("dt_cad_fornecedor"));
		
		p.setIdProdForn(resultSet.getInt("id_prod_forn"));
		p.setNmProdForn(resultSet.getString("nm_prod_forn"));
		p.setDescProdForn(resultSet.getString("desc_prod_forn"));
		p.setValorProdForn(resultSet.getDouble("valor_prod_forn"));
		p.setUndMedidaProdForn(UndMedidaProdForn.valueOf(resultSet.getString("und_medida_prod_forn")));		
		p.setfornecedor(f);
		
		
		e.setIdEstoque(resultSet.getInt("id_estoque"));
		e.setProdutoFornecedor(p);				
		e.setQtdEstoque(resultSet.getDouble("qtd_estoque"));
		e.setDataRegistro(resultSet.getDate("data_registro"));		

		
		
		
		
	
		
		
		
		return e;
	}
}
