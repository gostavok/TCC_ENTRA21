package br.com.textilsoft.model;

import java.util.Date;

public class Cliente {

	private long 	idCliente;
	private String 	nmCliente;
	private String 	cnpjCliente;
	private String 	endCliente;
	private int 	cepCliente;
	private String 	bairroCliente;
	private String 	cidadeCliente;
	private String 	estadoCliente;
	private String 	compCliente;
	private long 	telCliente1;
	private long 	telCliente2;
	private String 	emailCliente;
	private Date	dtCadCliente;	
	
	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getEndCliente() {
		return endCliente;
	}

	public void setEndCliente(String endCliente) {
		this.endCliente = endCliente;
	}

	public int getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(int cepCliente) {
		this.cepCliente = cepCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getEstadoCliente() {
		return estadoCliente;
	}

	public void setEstadoCliente(String estadoCliente) {
		this.estadoCliente = estadoCliente;
	}

	public String getCompCliente() {
		return compCliente;
	}

	public void setCompCliente(String compCliente) {
		this.compCliente = compCliente;
	}

	public long getTelCliente1() {
		return telCliente1;
	}

	public void setTelCliente1(long telCliente1) {
		this.telCliente1 = telCliente1;
	}

	public long getTelCliente2() {
		return telCliente2;
	}

	public void setTelCliente2(long telCliente2) {
		this.telCliente2 = telCliente2;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public Date getDtCadCliente() {
		return dtCadCliente;
	}

	public void setDtCadCliente(Date dtCadCliente) {
		this.dtCadCliente = dtCadCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpjCliente == null) ? 0 : cnpjCliente.hashCode());
		result = prime * result + ((emailCliente == null) ? 0 : emailCliente.hashCode());
		result = (int) (prime * result + idCliente);
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
		Cliente other = (Cliente) obj;
		if (cnpjCliente == null) {
			if (other.cnpjCliente != null)
				return false;
		} else if (!cnpjCliente.equals(other.cnpjCliente))
			return false;
		if (emailCliente == null) {
			if (other.emailCliente != null)
				return false;
		} else if (!emailCliente.equals(other.emailCliente))
			return false;
		if (idCliente != other.idCliente)
			return false;
		return true;
	}		
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nmCliente=" + nmCliente + ", cnpjCliente=" + cnpjCliente
				+ ", endCliente=" + endCliente + ", cepCliente=" + cepCliente + ", bairroCliente=" + bairroCliente
				+ ", cidadeCliente=" + cidadeCliente + ", estadoCliente=" + estadoCliente + ", compCliente="
				+ compCliente + ", telCliente1=" + telCliente1 + ", telCliente2=" + telCliente2 + ", emailCliente="
				+ emailCliente + ", dtCadCliente=" + dtCadCliente + "]";
	}

}
