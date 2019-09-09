package br.com.textilsoft.model;


import java.util.Date;


public class Fornecedor {
	private long idFornecedor;
	private String nmFornecedor;
	private String cnpjFornecedor;
	private String endFornecedor;
	private int cepFornecedor;
	private String bairroFornecedor;
	private String cidadeFornecedor;
	private String estadoFornecedor;
	private String compFornecedor;
	private int telFornecedor;
	private int tel2Fornecedor;
	private String emailFornecedor;
	private Date dtCadFornecedor;
	
	
	
	public long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getNmFornecedor() {
		return nmFornecedor;
	}
	public void setNmFornecedor(String nmFornecedor) {
		this.nmFornecedor = nmFornecedor;
	}
	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}
	public void setCnpjFornecedor(String cnpjFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
	}
	public String getEndFornecedor() {
		return endFornecedor;
	}
	public void setEndFornecedor(String endFornecedor) {
		this.endFornecedor = endFornecedor;
	}
	public int getCepFornecedor() {
		return cepFornecedor;
	}
	public void setCepFornecedor(int cepFornecedor) {
		this.cepFornecedor = cepFornecedor;
	}
	public String getBairroFornecedor() {
		return bairroFornecedor;
	}
	public void setBairroFornecedor(String bairroFornecedor) {
		this.bairroFornecedor = bairroFornecedor;
	}
	public String getCidadeFornecedor() {
		return cidadeFornecedor;
	}
	public void setCidadeFornecedor(String cidadeFornecedor) {
		this.cidadeFornecedor = cidadeFornecedor;
	}
	public String getEstadoFornecedor() {
		return estadoFornecedor;
	}
	public void setEstadoFornecedor(String estadoFornecedor) {
		this.estadoFornecedor = estadoFornecedor;
	}
	public String getCompFornecedor() {
		return compFornecedor;
	}
	public void setCompFornecedor(String compFornecedor) {
		this.compFornecedor = compFornecedor;
	}
	public int getTelFornecedor() {
		return telFornecedor;
	}
	public void setTelFornecedor(int telFornecedor) {
		this.telFornecedor = telFornecedor;
	}
	public int getTel2Fornecedor() {
		return tel2Fornecedor;
	}
	public void setTel2Fornecedor(int tel2Fornecedor) {
		this.tel2Fornecedor = tel2Fornecedor;
	}
	public String getEmailFornecedor() {
		return emailFornecedor;
	}
	public void setEmailFornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}
	public Date getDtCadFornecedor() {
		return dtCadFornecedor;
	}
	public void setDtCadFornecedor(Date dtCadFornecedor) {
		this.dtCadFornecedor = dtCadFornecedor;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairroFornecedor == null) ? 0 : bairroFornecedor.hashCode());
		result = prime * result + cepFornecedor;
		result = prime * result + ((cidadeFornecedor == null) ? 0 : cidadeFornecedor.hashCode());
		result = prime * result + ((cnpjFornecedor == null) ? 0 : cnpjFornecedor.hashCode());
		result = prime * result + ((compFornecedor == null) ? 0 : compFornecedor.hashCode());
		result = prime * result + ((dtCadFornecedor == null) ? 0 : dtCadFornecedor.hashCode());
		result = prime * result + ((emailFornecedor == null) ? 0 : emailFornecedor.hashCode());
		result = prime * result + ((endFornecedor == null) ? 0 : endFornecedor.hashCode());
		result = prime * result + ((estadoFornecedor == null) ? 0 : estadoFornecedor.hashCode());
		result = prime * result + (int) (idFornecedor ^ (idFornecedor >>> 32));
		result = prime * result + ((nmFornecedor == null) ? 0 : nmFornecedor.hashCode());
		result = prime * result + tel2Fornecedor;
		result = prime * result + telFornecedor;
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
		Fornecedor other = (Fornecedor) obj;
		if (bairroFornecedor == null) {
			if (other.bairroFornecedor != null)
				return false;
		} else if (!bairroFornecedor.equals(other.bairroFornecedor))
			return false;
		if (cepFornecedor != other.cepFornecedor)
			return false;
		if (cidadeFornecedor == null) {
			if (other.cidadeFornecedor != null)
				return false;
		} else if (!cidadeFornecedor.equals(other.cidadeFornecedor))
			return false;
		if (cnpjFornecedor == null) {
			if (other.cnpjFornecedor != null)
				return false;
		} else if (!cnpjFornecedor.equals(other.cnpjFornecedor))
			return false;
		if (compFornecedor == null) {
			if (other.compFornecedor != null)
				return false;
		} else if (!compFornecedor.equals(other.compFornecedor))
			return false;
		if (dtCadFornecedor == null) {
			if (other.dtCadFornecedor != null)
				return false;
		} else if (!dtCadFornecedor.equals(other.dtCadFornecedor))
			return false;
		if (emailFornecedor == null) {
			if (other.emailFornecedor != null)
				return false;
		} else if (!emailFornecedor.equals(other.emailFornecedor))
			return false;
		if (endFornecedor == null) {
			if (other.endFornecedor != null)
				return false;
		} else if (!endFornecedor.equals(other.endFornecedor))
			return false;
		if (estadoFornecedor == null) {
			if (other.estadoFornecedor != null)
				return false;
		} else if (!estadoFornecedor.equals(other.estadoFornecedor))
			return false;
		if (idFornecedor != other.idFornecedor)
			return false;
		if (nmFornecedor == null) {
			if (other.nmFornecedor != null)
				return false;
		} else if (!nmFornecedor.equals(other.nmFornecedor))
			return false;
		if (tel2Fornecedor != other.tel2Fornecedor)
			return false;
		if (telFornecedor != other.telFornecedor)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Fornecedor [idFornecedor=" + idFornecedor + ", nmFornecedor=" + nmFornecedor + ", cnpjFornecedor="
				+ cnpjFornecedor + ", endFornecedor=" + endFornecedor + ", cepFornecedor=" + cepFornecedor
				+ ", bairroFornecedor=" + bairroFornecedor + ", cidadeFornecedor=" + cidadeFornecedor
				+ ", estadoFornecedor=" + estadoFornecedor + ", compFornecedor=" + compFornecedor + ", telFornecedor="
				+ telFornecedor + ", tel2Fornecedor=" + tel2Fornecedor + ", emailFornecedor=" + emailFornecedor
				+ ", dtCadFornecedor=" + dtCadFornecedor + "]";
	}
	
	
	
	

}


