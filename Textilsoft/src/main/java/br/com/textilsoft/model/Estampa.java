package br.com.textilsoft.model;

public class Estampa {

	private int idEstampa;
	private String codEstampa;
	private double valorEstampa;
	
	public int getIdEstampa() {
		return idEstampa;
	}
	public void setIdEstampa(int idEstampa) {
		this.idEstampa = idEstampa;
	}
	public String getCodEstampa() {
		return codEstampa;
	}
	public void setCodEstampa(String codEstampa) {
		this.codEstampa = codEstampa;
	}
	public double getValorEstampa() {
		return valorEstampa;
	}
	public void setValorEstampa(double valorEstampa) {
		this.valorEstampa = valorEstampa;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEstampa == null) ? 0 : codEstampa.hashCode());
		result = prime * result + idEstampa;
		long temp;
		temp = Double.doubleToLongBits(valorEstampa);
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
		Estampa other = (Estampa) obj;
		if (codEstampa == null) {
			if (other.codEstampa != null)
				return false;
		} else if (!codEstampa.equals(other.codEstampa))
			return false;
		if (idEstampa != other.idEstampa)
			return false;
		if (Double.doubleToLongBits(valorEstampa) != Double.doubleToLongBits(other.valorEstampa))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Estampa [idEstampa=" + idEstampa + ", codEstampa=" + codEstampa + ", valorEstampa=" + valorEstampa
				+ "]";
	}
	
	
	
	
	
	
	
}
