package br.com.textilsoft.model;

public class Orcamento {

	private int idOrcamento;
	private double valorBase;
	private double valorOrcamento;
	private int quantidade;
	private Cor corOrcamento;
	private Material materialOrcamento;
	private Estampa estampaOrcamento;
	
	public int getIdOrcamento() {
		return idOrcamento;
	}
	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	public double getValorBase() {
		return valorBase;
	}
	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}
	public double getValorOrcamento() {
		return valorOrcamento;
	}
	public void setValorOrcamento(double valorOrcamento) {
		this.valorOrcamento = valorOrcamento;
	}
	public Cor getCorOrcamento() {
		return corOrcamento;
	}
	public void setCorOrcamento(Cor corOrcamento) {
		this.corOrcamento = corOrcamento;
	}
	public Material getMaterialOrcamento() {
		return materialOrcamento;
	}
	public void setMaterialOrcamento(Material materialOrcamento) {
		this.materialOrcamento = materialOrcamento;
	}
	public Estampa getEstampaOrcamento() {
		return estampaOrcamento;
	}
	public void setEstampaOrcamento(Estampa estampaOrcamento) {
		this.estampaOrcamento = estampaOrcamento;
	}
	
	
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corOrcamento == null) ? 0 : corOrcamento.hashCode());
		result = prime * result + ((estampaOrcamento == null) ? 0 : estampaOrcamento.hashCode());
		result = prime * result + idOrcamento;
		result = prime * result + ((materialOrcamento == null) ? 0 : materialOrcamento.hashCode());
		result = prime * result + quantidade;
		long temp;
		temp = Double.doubleToLongBits(valorBase);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorOrcamento);
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
		Orcamento other = (Orcamento) obj;
		if (corOrcamento == null) {
			if (other.corOrcamento != null)
				return false;
		} else if (!corOrcamento.equals(other.corOrcamento))
			return false;
		if (estampaOrcamento == null) {
			if (other.estampaOrcamento != null)
				return false;
		} else if (!estampaOrcamento.equals(other.estampaOrcamento))
			return false;
		if (idOrcamento != other.idOrcamento)
			return false;
		if (materialOrcamento == null) {
			if (other.materialOrcamento != null)
				return false;
		} else if (!materialOrcamento.equals(other.materialOrcamento))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(valorBase) != Double.doubleToLongBits(other.valorBase))
			return false;
		if (Double.doubleToLongBits(valorOrcamento) != Double.doubleToLongBits(other.valorOrcamento))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Orcamento [idOrcamento=" + idOrcamento + ", valorBase=" + valorBase + ", valorOrcamento="
				+ valorOrcamento + ", quantidade=" + quantidade + ", corOrcamento=" + corOrcamento
				+ ", materialOrcamento=" + materialOrcamento + ", estampaOrcamento=" + estampaOrcamento + "]";
	}
	
	

	
}
