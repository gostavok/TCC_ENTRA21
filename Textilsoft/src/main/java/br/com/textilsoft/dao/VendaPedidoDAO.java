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
import br.com.textilsoft.model.Venda;
import br.com.textilsoft.model.VendaPedido;
import br.com.textilsoft.model.util.StatusPedido;

public class VendaPedidoDAO {
	
	private final ConexaoJDBC conexao;

	public VendaPedidoDAO() throws SQLException, ClassNotFoundException {
		this.conexao = new ConexaoMysqlJDBC();
	}
	
	
	
public void inserir(VendaPedido vendapedido) throws SQLException, ClassNotFoundException {
		
		
		String sqlQuery = "INSERT INTO venda_pedido (id_venda, id_pedido) VALUES (?, ?) ";

		try {
			PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
			stmt.setLong(1, vendapedido.getVenda().getIdVenda());
			stmt.setLong(2, vendapedido.getPedido().getIdPedido());
			
			stmt.execute();			
			this.conexao.commit();
		} catch (SQLException e) {
			this.conexao.rollback();
			throw e;
		}
	
	}
	

public int excluirtotal(long id_venda) throws SQLException, ClassNotFoundException {
	int linhasAlfetadas = 0;
	String sqlQuery = "DELETE FROM venda_pedido WHERE id_venda = ?";

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		stmt.setLong(1, id_venda);

		linhasAlfetadas = stmt.executeUpdate();
		this.conexao.commit();
	} catch (SQLException e) {
		this.conexao.rollback();
		throw e;
	}

	return linhasAlfetadas;
}

public int excluir(long id_venda, long id_pedido) throws SQLException, ClassNotFoundException {
	int linhasAlfetadas = 0;
	String sqlQuery = "DELETE FROM venda_pedido WHERE id_venda = ? and id_pedido = ?";

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		stmt.setLong(1, id_venda);
		stmt.setLong(2, id_pedido);
		linhasAlfetadas = stmt.executeUpdate();
		this.conexao.commit();
	} catch (SQLException e) {
		this.conexao.rollback();
		throw e;
	}

	return linhasAlfetadas;
}

public List<VendaPedido> listarVendasPedidos(long id) throws SQLException, ClassNotFoundException {
	String sqlQuery = "SELECT * FROM textilsoft.venda v"
			+ " inner join textilsoft.venda_pedido vp ON v.id_venda= vp.id_venda "
			+ " inner join textilsoft.pedido p ON vp.id_pedido = p.id_pedido "
			+ " inner join textilsoft.cliente c ON c.id_cliente = p.id_cliente "
			+ " WHERE v.id_venda = ?";		
	

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		
		stmt.setLong(1, id);	
		
		ResultSet rs = stmt.executeQuery();

		List<VendaPedido> vendaspedidos = new ArrayList<>();

		while (rs.next()) {
			vendaspedidos.add(parser(rs));
		}

		return vendaspedidos;
	} catch (SQLException e) {
		throw e;
	}
}	

public int alterar(Venda venda) throws SQLException, ClassNotFoundException {
	String sqlQuery = "UPDATE venda SET valor_total_venda = ? WHERE id_venda = ?";
	int linhasAfetadas = 0;

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		stmt.setDouble(1, venda.getValorTotal());	
		stmt.setLong(2, venda.getIdVenda());

		linhasAfetadas = stmt.executeUpdate();
		this.conexao.commit();
	} catch (SQLException e) {
		this.conexao.rollback();
		throw e;
	}

	return linhasAfetadas;
}

	private VendaPedido parser(ResultSet resultSet) throws SQLException {
		VendaPedido vp 	= new VendaPedido();
		Venda 		v 	= new Venda();
		Pedido 		p 	= new Pedido();
		Cliente 	c	= new Cliente();

		vp.setVenda(v);
		vp.setPedido(p);
		
		v.setIdVenda(resultSet.getInt("id_venda")); 
		v.setValorTotal(resultSet.getDouble("valor_total_venda"));
		v.setDataPagamento(resultSet.getDate("data_pagamento"));
		v.setDataVenda(resultSet.getDate("data_venda"));
		
		p.setCliente(c);
		
		c.setIdCliente(resultSet.getInt("id_cliente"));
		c.setNmCliente(resultSet.getString("nm_cliente"));
		
		p.setIdPedido(resultSet.getInt("id_pedido"));
		p.setQtdProd(resultSet.getInt("qtd_prod"));
		p.setValorTotalPedido(resultSet.getDouble("valor_total_pedido"));
		p.setDataPedido(resultSet.getDate("data_pedido"));
		p.setStatusPedido(StatusPedido.valueOf(resultSet.getString("status_pedido")));						
		
		return vp;
	}
}
