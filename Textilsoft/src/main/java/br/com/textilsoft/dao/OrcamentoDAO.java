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
import br.com.textilsoft.model.Orcamento;



public class OrcamentoDAO {

	private final ConexaoJDBC conexao;

	public OrcamentoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	public List<Orcamento> selectAll()throws SQLException,ClassNotFoundException{
		String sqlQuery = "SELECT id_orcamento,id_cor,id_estampa,id_material,valor_base,valor_orcamento FROM orcamento INNER JOIN cor USING(id_cor) INNER JOIN estampa USING(id_estampa) INNER JOIN material USING(id_material)";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			
			List<Orcamento> orcamento = new ArrayList<Orcamento>();
			
			while(rs.next()) {
 				orcamento.add(parser(rs));
			}
			return orcamento;
		} catch (Exception e) {
		throw e;
		}
	}
	
	public Orcamento select(int id)throws SQLException,ClassNotFoundException{
		String sqlQuery = "SELECT id_orcamento,id_cor,id_estampa,id_material,valor_base,valor_orcamento FROM orcamento INNER JOIN cor USING(id_cor) INNER JOIN estampa USING(id_estampa) INNER JOIN material USING(id_material) WHERE id_orcamento = ?";

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
	
	public void insert(Orcamento orcamento) throws SQLException, ClassNotFoundException{
		String sqlQuery = "INSERT INTO orcamento (id_cor,id_estampa,id_material,valor_base,valor_orcamento) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, orcamento.getCorOrcamento().getIdCor());
			stmt.setInt(2, orcamento.getEstampaOrcamento().getIdEstampa());
			stmt.setInt(3, orcamento.getMaterialOrcamento().getIdMaterial());
			stmt.setDouble(4, orcamento.getValorBase());
			stmt.setDouble(5, orcamento.getValorOrcamento());
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	
	public void update(Orcamento orcamento) throws SQLException,ClassNotFoundException{
		String sqlQuery = "UPDATE orcamento SET id_cor = ?, id_estampa = ?, id_material = ?, valor_base = ?, valor_orcamento= ? WHERE id_orcamento = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setInt(1, orcamento.getCorOrcamento().getIdCor());
			stmt.setInt(2, orcamento.getEstampaOrcamento().getIdEstampa());
			stmt.setInt(3, orcamento.getMaterialOrcamento().getIdMaterial());
			stmt.setDouble(4, orcamento.getValorBase());
			stmt.setDouble(5, orcamento.getValorOrcamento());
			stmt.setInt(6, orcamento.getIdOrcamento());
			stmt.execute();
			this.conexao.commit();
		} catch (Exception e) {
			this.conexao.rollback();
			throw e;
		}
	}
	
	public void delete(int id) throws SQLException,ClassNotFoundException{
		String sqlQuery = "DELETE FROM orcamento WHERE id_orcamento = ?";
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
	
	
	
	
	
	
	private Orcamento parser(ResultSet resultSet) throws SQLException {
		
		Cor c = new Cor();
		Estampa e = new Estampa();
		Material m = new Material();
		Orcamento o = new Orcamento();
	


		o.setIdOrcamento(resultSet.getInt("id_orcamento"));
		o.setValorBase(resultSet.getDouble("valor_base"));
		o.setValorOrcamento(resultSet.getDouble("valor_orcamento"));
		c.setIdCor(resultSet.getInt("id_cor"));		
		e.setIdEstampa(resultSet.getInt("id_estampa"));		
		m.setIdMaterial(resultSet.getInt("id_material"));
		o.setCorOrcamento(c);
		o.setEstampaOrcamento(e);
		o.setMaterialOrcamento(m);
		
		return o;
	}
	
}
