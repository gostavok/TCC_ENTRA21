package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Estampa;

public class EstampaDAO {

	private final ConexaoJDBC conexao;

	public EstampaDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public List<Estampa> selectAll() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT id_estampa,codigo_estampa,valor_estampa FROM estampa";
		try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		ResultSet rs = stmt.executeQuery();
		
		List<Estampa> estampa = new ArrayList<Estampa>();
		
		while (rs.next()) {
			estampa.add(parser(rs));
		}
		return estampa;
		} catch (Exception e) {
		throw e;
		}	
	}

	public Estampa select(int id) throws SQLException,ClassNotFoundException{
		String sqlQuery = "SELECT id_estampa,codigo_estampa,valor_estampa FROM estampa WHERE id_estampa = ?";
		
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


		public void insert(Estampa estampa) throws SQLException, ClassNotFoundException{
			String sqlQuery = "INSERT INTO estampa (codigo_estampa,valor_estampa) VALUES( ?, ?)";
			
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, estampa.getCodEstampa());
				stmt.setDouble(2, estampa.getValorEstampa());
				stmt.execute();
				this.conexao.commit();
			} catch (Exception e) {
				this.conexao.rollback();
				throw e;
			}
		} 

		public void update(Estampa estampa) throws SQLException, ClassNotFoundException{
			String sqlQuery = "UPDATE estampa SET codigo_estampa = ? ,valor_estampa = ? WHERE id_estampa = ?";
			
			try {
				PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
				stmt.setString(1, estampa.getCodEstampa());
				stmt.setDouble(2, estampa.getValorEstampa());
				stmt.setInt(3, estampa.getIdEstampa());
				stmt.execute();
				this.conexao.commit();
			} catch (Exception e) {
				this.conexao.rollback();
				throw e;
			}
		}

		public void delete(int id) throws SQLException, ClassNotFoundException {
			String sqlQuery = "DELETE FROM estampa WHERE id_estampa = ?";
			
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

	private Estampa parser(ResultSet resultSet) throws SQLException {
		Estampa e = new Estampa();
		
		e.setIdEstampa(resultSet.getInt("id_estampa"));
		e.setCodEstampa(resultSet.getString("codigo_estampa"));
		e.setValorEstampa(resultSet.getDouble("valor_estampa"));
		return e;
	}



}
