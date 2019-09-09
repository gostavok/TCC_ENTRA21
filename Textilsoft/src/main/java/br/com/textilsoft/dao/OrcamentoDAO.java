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
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private Orcamento parser(ResultSet resultSet) throws SQLException {
		Orcamento o = new Orcamento();
		Cor c = new Cor();
		Estampa e = new Estampa();
		Material m = new Material();

		c.setIdCor(resultSet.getInt("id_cor"));
		c.setNmCor(resultSet.getString("nm_cor"));
		c.setValorCor(resultSet.getDouble("valor_cor"));
		
		e.setIdEstampa(resultSet.getInt("id_estampa"));
		e.setCodEstampa(resultSet.getString("codigo_estampa"));
		e.setValorEstampa(resultSet.getDouble("valor_estampa"));
		
		m.setIdMaterial(resultSet.getInt("id_material"));
		m.setNmMatarial(resultSet.getString("nm_material"));
		m.setValorMaterial(resultSet.getDouble("valor_material"));

		o.setIdOrcamento(resultSet.getInt("id_orcamento"));
		o.setValorBase(resultSet.getDouble("valor_base"));
		o.setValorOrcamento(resultSet.getDouble("valor_orcamento"));
		o.setCorOrcamento(c);
		o.setEstampaOrcamento(e);
		o.setMaterialOrcamento(m);
		
		return o;
	}
	
}
