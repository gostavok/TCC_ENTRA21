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
	
	
public int alterar(VendaPedido vendapedido, long id_venda, long id_pedido) throws SQLException, ClassNotFoundException {
	String sqlQuery = "UPDATE venda_pedido SET id_venda = ?, id_pedido = ? WHERE id_venda = ? and id_pedido = ?";
	int linhasAfetadas = 0;

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
		stmt.setLong(1, vendapedido.getVenda().getIdVenda());
		stmt.setLong(2, vendapedido.getPedido().getIdPedido());
		stmt.setDouble(3, id_venda);
		stmt.setDouble(4, id_pedido);
		

		linhasAfetadas = stmt.executeUpdate();
		this.conexao.commit();
	} catch (SQLException e) {
		this.conexao.rollback();
		throw e;
	}

	return linhasAfetadas;
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

public List<VendaPedido> listar() throws SQLException, ClassNotFoundException {
	String sqlQuery = "SELECT * FROM textilsoft.venda_pedido VP WHERE id_venda = ? ORDER BY VP.id_pedido";

	try {
		PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
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
	private VendaPedido parser(ResultSet resultSet) throws SQLException {
		VendaPedido vp 	= new VendaPedido();
		Venda 		v 	= new Venda();
		Pedido 		p 	= new Pedido();
		Cliente 	c	= new Cliente();

		vp.setVenda(v);
		vp.setPedido(p);
		
		v.setIdVenda(resultSet.getInt("id_venda"));
		v.setValorTotal(resultSet.getDouble("valor_total"));
		v.setDataPagamento(resultSet.getDate("data_pagamento"));
		v.setDataVenda(resultSet.getDate("data_venda"));
		
		p.setCliente(c);
		
		c.setIdCliente(resultSet.getLong("id_cliente"));
		c.setNmCliente(resultSet.getString("nm_cliente"));
		
		p.setIdPedido(resultSet.getInt("id_pedido"));
		p.setQtdProd(resultSet.getInt("qtd_prod"));
		p.setValorTotal(resultSet.getDouble("valor_total"));
		p.setDataPedido(resultSet.getDate("data_pedido"));
		p.setStatusPedido(StatusPedido.valueOf(resultSet.getString("status_pedido")));						
		
		return vp;
	}
}
