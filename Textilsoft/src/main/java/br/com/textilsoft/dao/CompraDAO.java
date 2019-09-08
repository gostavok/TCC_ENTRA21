package br.com.textilsoft.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Compra;
import br.com.textilsoft.model.Fornecedor;
import br.com.textilsoft.model.ProdutoFornecedor;
import br.com.textilsoft.model.util.UndMedidaProdForn;

public class CompraDAO {
	
	
	private final ConexaoJDBC conexao;

	public CompraDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	
	public Long inserir(Compra compra) throws SQLException, ClassNotFoundException {
		Long id = null;
		String sqlQuery = "INSERT INTO compra (id_prod_forn, qtd_compra, valor_total, data_compra, data_venc) VALUES (?, ?, ?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, compra.getProdutoFornecedor().getIdProdForn());
			stmt.setDouble(2, compra.getQtdCompra());
			stmt.setDouble(3, compra.getValorTotal());
			stmt.setTimestamp(4, new java.sql.Timestamp( compra.getDataCompra().getTime()));
			stmt.setDate(5, new java.sql.Date( compra.getDataVenc().getTime()));
			
			stmt.execute();
			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return id;
	}
	
	
	public int alterar(Compra compra) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE compra SET id_prod_forn = ?, qtd_compra = ?,"
				+ " valor_total = ?, data_venc= ? WHERE id_compra = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, compra.getProdutoFornecedor().getIdProdForn());
			stmt.setDouble(2, compra.getQtdCompra());
			stmt.setDouble(3, compra.getValorTotal());
			stmt.setDate(4, new java.sql.Date( compra.getDataVenc().getTime()));
			

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
		String sqlQuery = "DELETE FROM compra WHERE id_compra = ?";

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
	
	
	public Compra selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM compra inner join produto_fornecedor using(id_prod_forn) WHERE id_compra = ?";

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
	
	
	public List<Compra> listarcompras() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM compra inner join produto_fornecedor using(id_prod_forn) ORDER BY id_compra";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Compra> compras = new ArrayList<>();

			while (rs.next()) {
				compras.add(parser(rs));
			}

			return compras;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	
	private Compra parser(ResultSet resultSet) throws SQLException {
		Compra c = new Compra();
		ProdutoFornecedor p = new ProdutoFornecedor();
		Fornecedor f = new Fornecedor();
		
		f.setIdFornecedor(resultSet.getInt("id_fornecedor"));
	
		
		p.setIdProdForn(resultSet.getInt("id_prod_forn"));
		p.setNmProdForn(resultSet.getString("nm_prod_forn"));
		p.setDescProdForn(resultSet.getString("desc_prod_forn"));
		p.setValorProdForn(resultSet.getDouble("valor_prod_forn"));
		p.setUndMedidaProdForn(UndMedidaProdForn.valueOf(resultSet.getString("und_medida_prod_forn")));		
		p.setfornecedor(f);
		
		c.setIdCompra(resultSet.getInt("id_compra"));
		c.setProdutoFornecedor(p);
		c.setQtdCompra(resultSet.getDouble("qtd_compra"));
		c.setValorTotal(resultSet.getDouble("valor_total"));
		c.setDataCompra(resultSet.getDate("data_compra"));
		c.setDataVenc(resultSet.getDate("data_venc"));

		return c;
		
		
	}
	
	
}
