package br.com.textilsoft.model;

public class Produto {

	private int idProduto;
	private String descProduto;
	private Cor corProduto;
	private Material materialProduto;
	private Estampa estampaProduto;
	private double valorProduto;
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getDescProduto() {
		return descProduto;
	}
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	public Cor getCorProduto() {
		return corProduto;
	}
	public void setCorProduto(Cor corProduto) {
		this.corProduto = corProduto;
	}
	public Material getMaterialProduto() {
		return materialProduto;
	}
	public void setMaterialProduto(Material materialProduto) {
		this.materialProduto = materialProduto;
	}
	public Estampa getEstampaProduto() {
		return estampaProduto;
	}
	public void setEstampaProduto(Estampa estampaProduto) {
		this.estampaProduto = estampaProduto;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corProduto == null) ? 0 : corProduto.hashCode());
		result = prime * result + ((descProduto == null) ? 0 : descProduto.hashCode());
		result = prime * result + ((estampaProduto == null) ? 0 : estampaProduto.hashCode());
		result = prime * result + idProduto;
		result = prime * result + ((materialProduto == null) ? 0 : materialProduto.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorProduto);
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
		Produto other = (Produto) obj;
		if (corProduto == null) {
			if (other.corProduto != null)
				return false;
		} else if (!corProduto.equals(other.corProduto))
			return false;
		if (descProduto == null) {
			if (other.descProduto != null)
				return false;
		} else if (!descProduto.equals(other.descProduto))
			return false;
		if (estampaProduto == null) {
			if (other.estampaProduto != null)
				return false;
		} else if (!estampaProduto.equals(other.estampaProduto))
			return false;
		if (idProduto != other.idProduto)
			return false;
		if (materialProduto == null) {
			if (other.materialProduto != null)
				return false;
		} else if (!materialProduto.equals(other.materialProduto))
			return false;
		if (Double.doubleToLongBits(valorProduto) != Double.doubleToLongBits(other.valorProduto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", descProduto=" + descProduto + ", corProduto=" + corProduto
				+ ", materialProduto=" + materialProduto + ", estampaProduto=" + estampaProduto + ", valorProduto="
				+ valorProduto + "]";
	}
	




}
