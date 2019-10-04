package br.com.textilsoft.model;

public class Cor {

	private int idCor;
	private String nmCor;
	private double valorCor;
	
	public int getIdCor() {
		return idCor;
	}
	public void setIdCor(int idCor) {
		this.idCor = idCor;
	}
	public String getNmCor() {
		return nmCor;
	}
	public void setNmCor(String nmCor) {
		this.nmCor = nmCor;
	}
	public double getValorCor() {
		return valorCor;
	}
	public void setValorCor(double valorCor) {
		this.valorCor = valorCor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCor;
		result = prime * result + ((nmCor == null) ? 0 : nmCor.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorCor);
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
		Cor other = (Cor) obj;
		if (idCor != other.idCor)
			return false;
		if (nmCor == null) {
			if (other.nmCor != null)
				return false;
		} else if (!nmCor.equals(other.nmCor))
			return false;
		if (Double.doubleToLongBits(valorCor) != Double.doubleToLongBits(other.valorCor))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Cor [idCor=" + idCor + ", nmCor=" + nmCor + ", valorCor=" + valorCor + "]";
	}
	
	
	
}
