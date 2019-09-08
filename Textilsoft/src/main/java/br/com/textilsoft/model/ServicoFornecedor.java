package br.com.textilsoft.model;
import br.com.textilsoft.model.util.UndMedidaServForn;

public class ServicoFornecedor {

	private int idServForn;
	private String nmServForn;
	private String descServForn;
	private double valorServForn;
	private UndMedidaServForn UndMedidaServForn;
	private Fornecedor fornecedor;
	
	public int getIdServForn() {
		return idServForn;
	}
	public void setIdServForn(int idServForn) {
		this.idServForn = idServForn;
	}
	public String getNmServForn() {
		return nmServForn;
	}
	public void setNmServForn(String nmServForn) {
		this.nmServForn = nmServForn;
	}
	public String getDescServForn() {
		return descServForn;
	}
	public void setDescServForn(String descServForn) {
		this.descServForn = descServForn;
	}
	public double getValorServForn() {
		return valorServForn;
	}
	public void setValorServForn(double valorServForn) {
		this.valorServForn = valorServForn;
	}
	public UndMedidaServForn getUndMedidaServForn() {
		return UndMedidaServForn;
	}
	public void setUndMedidaServForn(UndMedidaServForn undMedidaServForn) {
		UndMedidaServForn = undMedidaServForn;
	}
	public Fornecedor getfornecedor() {
		return fornecedor;
	}
	public void setfornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UndMedidaServForn == null) ? 0 : UndMedidaServForn.hashCode());
		result = prime * result + ((descServForn == null) ? 0 : descServForn.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + idServForn;
		result = prime * result + ((nmServForn == null) ? 0 : nmServForn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorServForn);
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
		ServicoFornecedor other = (ServicoFornecedor) obj;
		if (UndMedidaServForn != other.UndMedidaServForn)
			return false;
		if (descServForn == null) {
			if (other.descServForn != null)
				return false;
		} else if (!descServForn.equals(other.descServForn))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (idServForn != other.idServForn)
			return false;
		if (nmServForn == null) {
			if (other.nmServForn != null)
				return false;
		} else if (!nmServForn.equals(other.nmServForn))
			return false;
		if (Double.doubleToLongBits(valorServForn) != Double.doubleToLongBits(other.valorServForn))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ServicoFornecedor [idServForn=" + idServForn + ", nmServForn=" + nmServForn + ", descServForn="
				+ descServForn + ", valorServForn=" + valorServForn + ", UndMedidaServForn=" + UndMedidaServForn
				+ ", fornecedor=" + fornecedor + "]";
	}
	
	
	
	
	
	
}
