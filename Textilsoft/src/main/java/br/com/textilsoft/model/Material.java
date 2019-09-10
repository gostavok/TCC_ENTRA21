package br.com.textilsoft.model;

public class Material {

	private int idMaterial;
	private String nmMatarial;
	private double valorMaterial;
	public int getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}
	public String getNmMatarial() {
		return nmMatarial;
	}
	public void setNmMatarial(String nmMatarial) {
		this.nmMatarial = nmMatarial;
	}
	public double getValorMaterial() {
		return valorMaterial;
	}
	public void setValorMaterial(double valorMaterial) {
		this.valorMaterial = valorMaterial;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMaterial;
		result = prime * result + ((nmMatarial == null) ? 0 : nmMatarial.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorMaterial);
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
		Material other = (Material) obj;
		if (idMaterial != other.idMaterial)
			return false;
		if (nmMatarial == null) {
			if (other.nmMatarial != null)
				return false;
		} else if (!nmMatarial.equals(other.nmMatarial))
			return false;
		if (Double.doubleToLongBits(valorMaterial) != Double.doubleToLongBits(other.valorMaterial))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Material [idMaterial=" + idMaterial + ", nmMatarial=" + nmMatarial + ", valorMaterial=" + valorMaterial
				+ "]";
	}
	
	
	
}
