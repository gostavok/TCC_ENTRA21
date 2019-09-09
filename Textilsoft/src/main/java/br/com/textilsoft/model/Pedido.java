package br.com.textilsoft.model;

import br.com.textilsoft.model.util.StatusPedido;
import java.util.Date;

public class Pedido {
	
	private int idPedido;
	private Cliente cliente;
	private int qtdProd;
	private double valorTotal;
	private Date dataPedido;
	private StatusPedido StatusPedido;
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getQtdProd() {
		return qtdProd;
	}
	public void setQtdProd(int qtdProd) {
		this.qtdProd = qtdProd;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public StatusPedido getStatusPedido() {
		return StatusPedido;
	}
	public void setStatusPedido(StatusPedido statusPedido) {
		StatusPedido = statusPedido;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((StatusPedido == null) ? 0 : StatusPedido.hashCode());
		result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result + idPedido;
		result = prime * result + qtdProd;
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (StatusPedido != other.StatusPedido)
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (idPedido != other.idPedido)
			return false;
		if (qtdProd != other.qtdProd)
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", qtdProd=" + qtdProd + ", valorTotal=" + valorTotal + ", dataPedido="
				+ dataPedido + ", StatusPedido=" + StatusPedido + "]";
	}
	
	
	
	
	
	
	
	
}
