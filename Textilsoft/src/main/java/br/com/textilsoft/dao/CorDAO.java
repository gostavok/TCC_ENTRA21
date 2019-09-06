package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Cor;

public class CorDAO {

	private final ConexaoJDBC conexao;

	public CorDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public void insert(Cor cor) throws SQLException, ClassNotFoundException {
		String sqlQuery = "INSERT INTO cor (nm_cor, valor_cor) VALUES (?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, cor.getNmCor());
			stmt.setDouble(2, cor.getValorCor());
			stmt.execute();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}

	public List<Cor> selectAll() throws SQLException, ClassNotFoundException {

		String sqlQuery = "SELECT id_cor,nm_cor,valor_cor FROM cor";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Cor> cor = new ArrayList<Cor>();

			while (rs.next()) {
				cor.add(parser(rs));
			}
			return cor;
		} catch (SQLException e) {
			throw e;
		}

	}

	public Cor select(int id) throws SQLException, ClassNotFoundException {

		String sqlQuery = "SELECT id_cor,nm_cor,valor_cor FROM cor WHERE id_cor = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return parser(rs);
			}

			}
		catch (Exception e) {
			throw e;
			}
			return null;
			}
	
	public void delete(int id) throws SQLException, ClassNotFoundException{
		
		String sqlQuery = "DELETE FROM cor WHERE id_cor = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			stmt.execute();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		}
	
	public void update(Cor cor) throws SQLException, ClassNotFoundException{
		
		String sqlQuery = "UPDATE cor SET nm_cor = ? ,valor_cor= ? WHERE id_cor = ?";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, cor.getNmCor());
			stmt.setDouble(2, cor.getValorCor());
			stmt.setInt(3, cor.getIdCor());
			stmt.execute();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	
	
	
	
	
	
	

	private Cor parser(ResultSet resultSet) throws SQLException {
		Cor c = new Cor();

		c.setIdCor(resultSet.getInt("id_cor"));
		c.setNmCor(resultSet.getString("nm_cor"));
		c.setValorCor(resultSet.getDouble("valor_cor"));

		return c;
	}

}
