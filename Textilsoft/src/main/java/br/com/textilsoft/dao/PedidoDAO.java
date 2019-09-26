package br.com.textilsoft.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.textilsoft.data.ConexaoJDBC;
import br.com.textilsoft.data.ConexaoMysqlJDBC;
import br.com.textilsoft.model.Cliente;
import br.com.textilsoft.model.Pedido;
import br.com.textilsoft.model.util.StatusPedido;


public class PedidoDAO {
	
	
	private final ConexaoJDBC conexao;

	public PedidoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}

	
	
	public Pedido inserir(Pedido pedido) throws SQLException, ClassNotFoundException {
		
		String sqlQuery = "INSERT INTO pedido (id_cliente, qtd_prod, "
				+ "valor_total, data_pedido, status_pedido) VALUES (?, ?, ?, ?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, pedido.getCliente().getIdCliente());
			stmt.setLong(2, pedido.getQtdProd());
			stmt.setDouble(3, pedido.getValorTotal());
			stmt.setTimestamp(4, new java.sql.Timestamp(pedido.getDataPedido().getTime()));	
			stmt.setString(5, pedido.getStatusPedido().toString());
			
			stmt.execute();			
			this.conexao.commit();
			
		}
		
		catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
		
		return null;
	
		
}
	
	public int alterar(Pedido pedido) throws SQLException, ClassNotFoundException {
		String sqlQuery = "UPDATE pedido SET id_cliente = ?, qtd_prod= ?,"
				+ " valor_total = ?, status_pedido = ? WHERE id_pedido = ?";
		int linhasAfetadas = 0;

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, pedido.getCliente().getIdCliente());
			stmt.setLong(2, pedido.getQtdProd());
			stmt.setDouble(3, pedido.getValorTotal());
			stmt.setString(4, pedido.getStatusPedido().toString());
			stmt.setLong(5, pedido.getIdPedido());
			

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
		String sqlQuery = "DELETE FROM pedido WHERE id_pedido = ?";

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
	
	
	public Pedido selecionar(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM pedido inner join cliente using(id_cliente) WHERE id_pedido = ?";

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
	
	
	
	public List<Pedido> listarpedidos() throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM pedido inner join cliente using(id_cliente) ORDER BY id_pedido";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			ResultSet rs = stmt.executeQuery();

			List<Pedido> pedidos = new ArrayList<>();

			while (rs.next()) {
				pedidos.add(parser(rs));
			}

			return pedidos;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	public List<Pedido> listarpedidoscliente(long id) throws SQLException, ClassNotFoundException {
		String sqlQuery = "SELECT * FROM pedido inner join cliente using(id_cliente) where id_cliente = ?";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			List<Pedido> pedidos = new ArrayList<>();

			while (rs.next()) {
				pedidos.add(parser(rs));
			}

			return pedidos;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	private Pedido parser2(ResultSet resultSet) throws SQLException {
		Pedido p = new Pedido();
		
		p.setIdPedido(resultSet.getInt("id_pedido"));
		
		return p;
	}
	
	
	private Pedido parser(ResultSet resultSet) throws SQLException {
		Pedido p = new Pedido();
		Cliente c = new Cliente();
		
		c.setIdCliente(resultSet.getInt("id_cliente"));
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
		c.setDtCadCliente(resultSet.getDate("dt_cad_cliente"));
		
		p.setIdPedido(resultSet.getInt("id_pedido"));
		p.setQtdProd(resultSet.getInt("qtd_prod"));
		p.setValorTotal(resultSet.getDouble("valor_total"));
		p.setDataPedido(resultSet.getDate("data_pedido"));
		p.setStatusPedido(StatusPedido.valueOf(resultSet.getString("status_pedido")));		
		p.setCliente(c);

		return p;
	}
	
	
}
