package br.com.textilsoft.model;

import java.util.Date;

public class Estoque {
	private long idEstoque;
	private ProdutoFornecedor produtoFornecedor;
	private double qtdEstoque;
	private Date dataRegistro;	
	
	
	public long getIdEstoque() {
		return idEstoque;
	}
	public void setIdEstoque(long idEstoque) {
		this.idEstoque = idEstoque;
	}
	public ProdutoFornecedor getProdutoFornecedor() {
		return produtoFornecedor;
	}
	public void setProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
		this.produtoFornecedor = produtoFornecedor;
	}
	public double getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(double qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRegistro == null) ? 0 : dataRegistro.hashCode());
		result = prime * result + (int) (idEstoque ^ (idEstoque >>> 32));
		result = prime * result + ((produtoFornecedor == null) ? 0 : produtoFornecedor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(qtdEstoque);
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
		Estoque other = (Estoque) obj;
		if (dataRegistro == null) {
			if (other.dataRegistro != null)
				return false;
		} else if (!dataRegistro.equals(other.dataRegistro))
			return false;
		if (idEstoque != other.idEstoque)
			return false;
		if (produtoFornecedor == null) {
			if (other.produtoFornecedor != null)
				return false;
		} else if (!produtoFornecedor.equals(other.produtoFornecedor))
			return false;
		if (Double.doubleToLongBits(qtdEstoque) != Double.doubleToLongBits(other.qtdEstoque))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Estoque [idEstoque=" + idEstoque + ", produtoFornecedor=" + produtoFornecedor + ", qtdEstoque="
				+ qtdEstoque + ", dataRegistro=" + dataRegistro + "]";
	}
	
	
}
