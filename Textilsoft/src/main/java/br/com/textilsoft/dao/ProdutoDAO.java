package br.com.textilsoft.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Cor;
import br.com.textilsoft.model.Estampa;
import br.com.textilsoft.model.Material;
import br.com.textilsoft.model.Produto;

public class ProdutoDAO {

	private final ConexaoJDBC conexao;
	
	public ProdutoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	
	public List<Produto> selectAll() throws SQLException, ClassNotFoundException{
		String sqlQuery = "SELECT id_produto,desc_produto,cor_produto,material_produto,estampa_produto,valor_produto FROM produto";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Produto> produto = new ArrayList<Produto>();
			
			while(rs.next()) {
				produto.add(parser(rs));
			}
			return produto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public Produto select(int id) throws SQLException, ClassNotFoundException{
		String sqlQuery = "SELECT id_produto,desc_produto,cor_produto,material_produto,estampa_produto,valor_produto FROM produto WHERE id_produto = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return parser(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;	
	}
	
	public void insert(Produto produto) throws SQLException, ClassNotFoundException{
		String sqlQuery = "INSERT INTO produto(desc_produto, cor_produto, material_produto, estampa_produto, valor_produto) VALUES(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, produto.getDescProduto());
			stmt.setInt(2, produto.getCorProduto().getIdCor());
			stmt.setInt(3, produto.getMaterialProduto().getIdMaterial());
			stmt.setInt(4, produto.getEstampaProduto().getIdEstampa());
			stmt.setDouble(5, produto.getValorProduto());
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public void update(Produto produto) throws SQLException, ClassNotFoundException{
		String sqlQuery = "UPDATE produto SET desc_produto  = ?, cor_produto = ?, material_produto = ?, estampa_produto = ?, valor_produto= ? WHERE id_produto = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, produto.getDescProduto());
			stmt.setInt(2, produto.getCorProduto().getIdCor());
			stmt.setInt(3, produto.getMaterialProduto().getIdMaterial());
			stmt.setInt(4,produto.getEstampaProduto().getIdEstampa());
			stmt.setDouble(5, produto.getValorProduto());
			stmt.setInt(6, produto.getIdProduto());
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public void delete(int id) throws SQLException, ClassNotFoundException{
		String sqlQuery = "DELETE FROM produto WHERE id_produto = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	
	
	
	
	
	private Produto parser(ResultSet resultSet) throws SQLException {
		Cor c = new Cor();
		Estampa e = new Estampa();
		Material m = new Material();
		Produto p = new Produto();

		c.setIdCor(resultSet.getInt("id_cor"));
		c.setNmCor(resultSet.getString("nm_cor"));
		c.setValorCor(resultSet.getDouble("valor_cor"));
		
		e.setIdEstampa(resultSet.getInt("id_estampa"));
		e.setCodEstampa(resultSet.getString("codigo_estampa"));
		e.setValorEstampa(resultSet.getDouble("valor_estampa"));
		
		m.setIdMaterial(resultSet.getInt("id_material"));
		m.setNmMatarial(resultSet.getString("nm_material"));
		m.setValorMaterial(resultSet.getDouble("valor_material"));
		
		p.setIdProduto(resultSet.getInt("id_produto"));
		p.setDescProduto(resultSet.getString("desc_produto"));
		p.setValorProduto(resultSet.getDouble("valor_produto"));
		p.setCorProduto(c);
		p.setEstampaProduto(e);
		p.setMaterialProduto(m);

		return p;
	}

}
