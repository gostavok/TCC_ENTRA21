package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Material;

public class MaterialDAO {

	private final ConexaoJDBC conexao;
	
	public MaterialDAO()throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();	
	}
	
	//-----------------------------------------------------------------------------------------------------------\\
	public void insert(Material material) throws SQLException, ClassNotFoundException{
		String sqlQuery = "INSERT INTO material(nm_material,valor_material) VALUES (? ,?)";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, material.getnmMaterial());
			stmt.setDouble(2, material.getValorMaterial());
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	//-----------------------------------------------------------------------------------------------------------\\
	public List<Material> selectAll() throws SQLException, ClassNotFoundException{
			String sqlQuery = "SELECT id_material,nm_material,valor_material FROM material";
	
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				ResultSet rs = stmt.executeQuery();

				List<Material> material = new ArrayList<Material>();

				while (rs.next()) {
					material.add(parser(rs));
				}
				return material;
			} catch (SQLException e) {
				throw e;
			}
	}
	//-----------------------------------------------------------------------------------------------------------\\
	public Material select(int id) throws SQLException,ClassNotFoundException{
		String sql = "SELECT id_material,nm_material,valor_material FROM material WHERE id_material = ?";
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return parser(rs);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return null;
	}
	//-----------------------------------------------------------------------------------------------------------\\
	public void delete(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM material WHERE id_material = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	//-----------------------------------------------------------------------------------------------------------\\
	public void update(Material material) throws SQLException, ClassNotFoundException{
		
		String sqlQuery = "UPDATE material set nm_material = ? ,valor_material = ? WHERE id_material = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, material.getnmMaterial());
			stmt.setDouble(2, material.getValorMaterial());
			stmt.setInt(3, material.getIdMaterial());
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	
	
	
	
	
	
	
	
	private Material parser(ResultSet resultSet) throws SQLException {
		Material m = new Material();

		m.setIdMaterial(resultSet.getInt("id_material"));
		m.setnmMaterial(resultSet.getString("nm_material"));
		m.setValorMaterial(resultSet.getDouble("valor_material"));
		
		return m;
	}

}
