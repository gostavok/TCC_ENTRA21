package br.com.textilsoft.model;
import br.com.textilsoft.model.util.UndMedidaProdForn;

public class ProdutoFornecedor {
	
	private int idProdForn;
	private  String nmProdForn;
	private String descProdForn;
	private  UndMedidaProdForn UndMedidaProdForn;
	private double valorProdForn;
	private Fornecedor idFornecedor;
	
	
	
	public int getIdProdForn() {
		return idProdForn;
	}
	public void setIdProdForn(int idProdForn) {
		this.idProdForn = idProdForn;
	}
	public String getNmProdForn() {
		return nmProdForn;
	}
	public void setNmProdForn(String nmProdForn) {
		this.nmProdForn = nmProdForn;
	}
	public String getDescProdForn() {
		return descProdForn;
	}
	public void setDescProdForn(String descProdForn) {
		this.descProdForn = descProdForn;
	}
	public UndMedidaProdForn getUndMedidaProdForn() {
		return UndMedidaProdForn;
	}
	public void setUndMedidaProdForn(UndMedidaProdForn undMedidaProdForn) {
		UndMedidaProdForn = undMedidaProdForn;
	}
	public double getValorProdForn() {
		return valorProdForn;
	}
	public void setValorProdForn(double valorProdForn) {
		this.valorProdForn = valorProdForn;
	}
	public Fornecedor getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Fornecedor idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UndMedidaProdForn == null) ? 0 : UndMedidaProdForn.hashCode());
		result = prime * result + ((descProdForn == null) ? 0 : descProdForn.hashCode());
		result = prime * result + idProdForn;
		result = prime * result + ((nmProdForn == null) ? 0 : nmProdForn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorProdForn);
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
		ProdutoFornecedor other = (ProdutoFornecedor) obj;
		if (UndMedidaProdForn != other.UndMedidaProdForn)
			return false;
		if (descProdForn == null) {
			if (other.descProdForn != null)
				return false;
		} else if (!descProdForn.equals(other.descProdForn))
			return false;
		if (idProdForn != other.idProdForn)
			return false;
		if (nmProdForn == null) {
			if (other.nmProdForn != null)
				return false;
		} else if (!nmProdForn.equals(other.nmProdForn))
			return false;
		if (Double.doubleToLongBits(valorProdForn) != Double.doubleToLongBits(other.valorProdForn))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProdutoFornecedor [idProdForn=" + idProdForn + ", nmProdForn=" + nmProdForn + ", descProdForn="
				+ descProdForn + ", UndMedidaProdForn=" + UndMedidaProdForn + ", valorProdForn=" + valorProdForn + "]";
	}
	
	

}
