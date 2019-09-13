package br.com.textilsoft.model;

import br.com.textilsoft.model.util.StatusContaReceber;

public class ContaReceber {
	private long idContaReceber;
	private Venda venda;
	private StatusContaReceber statusContaReceber;	
	
	public StatusContaReceber getStatusContaReceber() {
		return statusContaReceber;
	}
	public void setStatusContaReceber(StatusContaReceber statusContaReceber) {
		this.statusContaReceber = statusContaReceber;
	}
	public long getIdContaReceber() {
		return idContaReceber;
	}
	public void setIdContaReceber(long idContaReceber) {
		this.idContaReceber = idContaReceber;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	@Override
	public String toString() {
		return "ContaReceber [idContaReceber=" + idContaReceber + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idContaReceber ^ (idContaReceber >>> 32));
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
		ContaReceber other = (ContaReceber) obj;
		if (idContaReceber != other.idContaReceber)
			return false;
		return true;
	}
	
	
	
}
