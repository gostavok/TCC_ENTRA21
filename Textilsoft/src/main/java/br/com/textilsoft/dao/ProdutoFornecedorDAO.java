package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.ProdutoFornecedor;
import br.com.textilsoft.model.util.UndMedidaProdForn;

public class ProdutoFornecedorDAO {
	
	private final ConexaoJDBC conexao;

	public ProdutoFornecedorDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	
	public Long inserir(ProdutoFornecedor produtoFornecedor) throws SQLException, ClassNotFoundException {
		Long id = null;
		String sqlQuery = "INSERT INTO produto_fornecedor (nm_prod_forn, desc_prod_forn, valor_prod_forn, und_medida_prod_forn, id_fornecedor) VALUES (?, ?, ?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, produtoFornecedor.getNmProdForn());
			stmt.setString(2, produtoFornecedor.getDescProdForn());
			stmt.setDouble(3, produtoFornecedor.getValorProdForn());
			stmt.setString(4, produtoFornecedor.getUndMedidaProdForn().toString());
			stmt.setLong(5, produtoFornecedor.getIdFornecedor().getIdFornecedor());
			
			stmt.execute();
			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return id;
	}
	
	public int alterar(ProdutoFornecedor produtoFornecedor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE produto_fornecedor SET nm_prod_forn = ?, desc_prod_forn = ?,"
				+ " valor_prod_forn = ?, und_medida_prod_forn = ?, id_fornecedor = ? WHERE id_prod_forn = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, produtoFornecedor.getNmProdForn());
			stmt.setString(2, produtoFornecedor.getDescProdForn());
			stmt.setDouble(3, produtoFornecedor.getValorProdForn());
			stmt.setString(4, produtoFornecedor.getUndMedidaProdForn().toString());
			stmt.setLong(5, produtoFornecedor.getIdFornecedor().getIdFornecedor());
			stmt.setLong(6, produtoFornecedor.getIdProdForn());
			

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
		String sqlQuery = "DELETE FROM produto_fornecedor WHERE id_prod_forn = ?";

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
	
	public ProdutoFornecedor selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM produto_fornecedor inner join fornecedor using(id_fornecedor) WHERE id_prod_forn = ?";

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
		
	
	
	public List<ProdutoFornecedor> listarProdutos() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM produto_fornecedor inner join fornecedor using(id_fornecedor) ORDER BY id_prod_forn";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<ProdutoFornecedor> produtos = new ArrayList<>();

			while (rs.next()) {
				produtos.add(parser(rs));
			}

			return produtos;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public List<ProdutoFornecedor> listarFornecedores(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM produto_fornecedor inner join fornecedor using(id_fornecedor) where id_fornecedor = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<ProdutoFornecedor> produtos = new ArrayList<>();

			while (rs.next()) {
				produtos.add(parser(rs));
			}

			return produtos;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	
	
	private ProdutoFornecedor parser(ResultSet resultSet) throws SQLException {
		ProdutoFornecedor p = new ProdutoFornecedor();
		Fornecedor f = new Fornecedor();
		
		
		p.setIdProdForn(resultSet.getInt("id_prod_forn"));
		p.setNmProdForn(resultSet.getString("nm_prod_forn"));
		p.setDescProdForn(resultSet.getString("desc_prod_forn"));
		p.setValorProdForn(resultSet.getDouble("valor_prod_forn"));
		p.setUndMedidaProdForn(UndMedidaProdForn.valueOf(resultSet.getString("und_medida_prod_forn")));		
		f.getIdFornecedor(resultSet.getInt("id_fornecedor"));
		p.setIdFornecedor(f);

		return p;
	}
	
}
