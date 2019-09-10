package br.com.textilsoft.model;

import java.util.Date;

import br.com.textilsoft.model.util.StatusContaPagar;

public class ContaPagar {
	private long idContaPagar;
	private String descContaPagar;
	private String complemento;
	private double valorContaPagar;
	private Date dataVencimento;
	private Date dataInclusao;
	private StatusContaPagar statusContaPagar;
	
	public ContaPagar() {
		Date hoje = new Date();
		this.dataInclusao = hoje;
	}
	
	
	public long getIdContaPagar() {
		return idContaPagar;
	}
	public double getValorContaPagar() {
		return valorContaPagar;
	}


	public void setValorContaPagar(double valorContaPagar) {
		this.valorContaPagar = valorContaPagar;
	}


	public void setIdContaPagar(long idContaPagar) {
		this.idContaPagar = idContaPagar;
	}
	public String getDescContaPagar() {
		return descContaPagar;
	}
	public void setDescContaPagar(String descContaPagar) {
		this.descContaPagar = descContaPagar;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public StatusContaPagar getStatusContaPagar() {
		return statusContaPagar;
	}
	public void setStatusContaPagar(StatusContaPagar statusContaPagar) {
		this.statusContaPagar = statusContaPagar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((descContaPagar == null) ? 0 : descContaPagar.hashCode());
		result = prime * result + (int) (idContaPagar ^ (idContaPagar >>> 32));
		result = prime * result + ((statusContaPagar == null) ? 0 : statusContaPagar.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorContaPagar);
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
		ContaPagar other = (ContaPagar) obj;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (descContaPagar == null) {
			if (other.descContaPagar != null)
				return false;
		} else if (!descContaPagar.equals(other.descContaPagar))
			return false;
		if (idContaPagar != other.idContaPagar)
			return false;
		if (statusContaPagar != other.statusContaPagar)
			return false;
		if (Double.doubleToLongBits(valorContaPagar) != Double.doubleToLongBits(other.valorContaPagar))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ContaPagar [idContaPagar=" + idContaPagar + ", descContaPagar=" + descContaPagar + ", complemento="
				+ complemento + ", valorContaPagar=" + valorContaPagar + ", dataVencimento=" + dataVencimento
				+ ", dataInclusao=" + dataInclusao + ", statusContaPagar=" + statusContaPagar + "]";
	}
	
	
	
}
