package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Cliente;



public class ClienteDAO {

	private final ConexaoJDBC conexao;

	public ClienteDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	public Long inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
		Long id = null;
		String sqlQuery = 
		
		"INSERT INTO textilsoft.cliente "+
		"(`nm_cliente`, "+
		"`cnpj_cliente`, "+
		"`end_cliente`, "+
		"`cep_cliente`, "+
		"`bairro_cliente`, "+
		"`cidade_cliente`, "+
		"`estado_cliente`, "+
		"`comp_cliente`, "+
		"`tel_cliente` ,"+
		"`tel2_cliente`, "+
		"`email_cliente`) "+
		"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, cliente.getNmCliente());
			stmt.setString(2, cliente.getCnpjCliente());
			stmt.setString(3, cliente.getEndCliente());
			stmt.setLong(4, cliente.getCepCliente());
			stmt.setString(5, cliente.getBairroCliente());
			stmt.setString(6, cliente.getCidadeCliente());
			stmt.setString(7, cliente.getEstadoCliente());
			stmt.setString(8, cliente.getCompCliente());
			stmt.setInt(9, cliente.getTelCliente1());
			stmt.setInt(10, cliente.getTelCliente2());
			stmt.setString(11, cliente.getEmailCliente());
			
			stmt.execute();
			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return id;
	}

	public int alterar(Cliente cliente) throws SQLException, ClassNotFoundException {
		
		int linhasAfetadas = 0;
		String sqlQuery = 
				
		"UPDATE textilsoft.cliente SET"+
		
		"nm_cliente = ?,"+
		"cnpj_cliente = ?,"+
		"end_cliente = ?,"+
		"cep_cliente = ?,"+
		"bairro_cliente = ?,"+
		"cidade_cliente = ?,"+
		"estado_cliente = ?,"+
		"comp_cliente = ?,"+
		"tel_cliente = ?,"+
		"tel2_cliente = ?,"+
		"email_cliente = ?,"+

		"WHERE id_cliente = ?";
	
		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setString(1, cliente.getNmCliente());
			stmt.setString(2, cliente.getCnpjCliente());
			stmt.setString(3, cliente.getEndCliente());
			stmt.setLong(4, cliente.getCepCliente());
			stmt.setString(5, cliente.getBairroCliente());
			stmt.setString(6, cliente.getCidadeCliente());
			stmt.setString(7, cliente.getEstadoCliente());
			stmt.setString(8, cliente.getCompCliente());
			stmt.setInt(9, cliente.getTelCliente1());
			stmt.setInt(10, cliente.getTelCliente2());
			stmt.setString(11, cliente.getEmailCliente());

			linhasAfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAfetadas;
	}

	public int excluir(long idCliente) throws SQLException, ClassNotFoundException {
		int linhasAlfetadas = 0;
		String sqlQuery = "DELETE FROM textilsoft.cliente WHERE id_cliente = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, idCliente);
			linhasAlfetadas = stmt.executeUpdate();
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}

		return linhasAlfetadas;
	}

	public Cliente selecionar(long idCliente) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM textilsoft.cliente WHERE id_cliente = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, idCliente);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return parser(rs);
			}
		} catch (SQLException e) {
			throw e;
		}

		return null;
	}

	public List<Cliente> listar() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM textilsoft.cliente ORDER BY id_cliente";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Cliente> clientes = new ArrayList<>();

			while (rs.next()) {
				clientes.add(parser(rs));
			}

			return clientes;
		} catch (SQLException e) {
			throw e;
		}
	}

	private Cliente parser(ResultSet resultSet) throws SQLException {
		Cliente c = new Cliente();

		c.setIdCliente(resultSet.getLong("id_cliente"));
		c.setNmCliente(resultSet.getString("nm_cliente"));
		c.setCnpjCliente(resultSet.getString("cnpj_cliente"));
		c.setEndCliente(resultSet.getString("end_cliente"));
		c.setCepCliente(resultSet.getInt("cep_cliente"));
		c.setBairroCliente(resultSet.getString("bairro_cliente"));
		c.setCidadeCliente(resultSet.getString("cidade_cliente"));
		c.setEstadoCliente(resultSet.getString("estado_cliente"));
		c.setCompCliente(resultSet.getString("comp_cliente"));
		c.setTelCliente1(resultSet.getInt("tel_cliente"));
		c.setTelCliente2(resultSet.getInt("tel2_cliente"));
		c.setEmailCliente(resultSet.getString("email_cliente"));
		
		return c;
	}
}
