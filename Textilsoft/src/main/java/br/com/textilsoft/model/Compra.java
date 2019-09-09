package br.com.textilsoft.model;

import java.util.Date;


public class Compra {
	
	private int idCompra;
	private ProdutoFornecedor produtoFornecedor;
	private double qtdCompra;
	private double valorTotal;
	private Date dataCompra;
	private Date dataVenc;
	
	
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public ProdutoFornecedor getProdutoFornecedor() {
		return produtoFornecedor;
	}
	public void setProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
		this.produtoFornecedor = produtoFornecedor;
	}
	public double getQtdCompra() {
		return qtdCompra;
	}
	public void setQtdCompra(double qtdCompra) {
		this.qtdCompra = qtdCompra;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Date getDataVenc() {
		return dataVenc;
	}
	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCompra == null) ? 0 : dataCompra.hashCode());
		result = prime * result + ((dataVenc == null) ? 0 : dataVenc.hashCode());
		result = prime * result + idCompra;
		result = prime * result + ((produtoFornecedor == null) ? 0 : produtoFornecedor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(qtdCompra);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Compra other = (Compra) obj;
		if (dataCompra == null) {
			if (other.dataCompra != null)
				return false;
		} else if (!dataCompra.equals(other.dataCompra))
			return false;
		if (dataVenc == null) {
			if (other.dataVenc != null)
				return false;
		} else if (!dataVenc.equals(other.dataVenc))
			return false;
		if (idCompra != other.idCompra)
			return false;
		if (produtoFornecedor == null) {
			if (other.produtoFornecedor != null)
				return false;
		} else if (!produtoFornecedor.equals(other.produtoFornecedor))
			return false;
		if (Double.doubleToLongBits(qtdCompra) != Double.doubleToLongBits(other.qtdCompra))
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", produtoFornecedor=" + produtoFornecedor + ", qtdCompra=" + qtdCompra
				+ ", valorTotal=" + valorTotal + ", dataCompra=" + dataCompra + ", dataVenc=" + dataVenc + "]";
	}
	
	
}
