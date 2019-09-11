package br.com.textilsoft.model;

import java.util.Calendar;
import java.util.Date;

import br.com.textilsoft.model.util.StatusOrdemServico;

public class OrdemServico {

	private long idOrdem;
	private Fornecedor fornecedor;
	private ServicoFornecedor servicoFornecedor;
	private double qtdServico;
	private StatusOrdemServico statusOrdem;
	private Date dataAberturaOrdemServico;
	private Date dataEntregaOrdemServico;
	private double valorTotalOrdemServico;
		
	public long getIdOrdem() {
		return idOrdem;
	}
	public void setIdOrdem(long idOrdem) {
		this.idOrdem = idOrdem;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public ServicoFornecedor getServicoFornecedor() {
		return servicoFornecedor;
	}
	public void setServicoFornecedor(ServicoFornecedor servicoFornecedor) {
		this.servicoFornecedor = servicoFornecedor;
	}
	public double getQtdServico() {
		return qtdServico;
	}
	public void setQtdServico(double qtdServico) {
		this.qtdServico = qtdServico;
	}	
	
	public StatusOrdemServico getStatusOrdem() {
		return statusOrdem;
	}
	public void setStatusOrdem(StatusOrdemServico statusOrdem) {
		this.statusOrdem = statusOrdem;
	}
	public Date getDataAberturaOrdemServico() {
		return dataAberturaOrdemServico;
	}
	public void setDataAberturaOrdemServico(Date dataAberturaOrdemServico) {
		this.dataAberturaOrdemServico = dataAberturaOrdemServico;
	}
	public Date getDataEntregaOrdemServico() {
		return dataEntregaOrdemServico;
	}
	public void setDataEntregaOrdemServico(Date dataEntregaOrdemServico) {
		this.dataEntregaOrdemServico = dataEntregaOrdemServico;
	}
	public double getValorTotalOrdemServico() {
		return valorTotalOrdemServico;
	}
	public void setValorTotalOrdemServico(double valorTotalOrdemServico) {
		this.valorTotalOrdemServico = valorTotalOrdemServico;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAberturaOrdemServico == null) ? 0 : dataAberturaOrdemServico.hashCode());
		result = prime * result + ((dataEntregaOrdemServico == null) ? 0 : dataEntregaOrdemServico.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + (int) (idOrdem ^ (idOrdem >>> 32));
		result = prime * result + ((servicoFornecedor == null) ? 0 : servicoFornecedor.hashCode());
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
		OrdemServico other = (OrdemServico) obj;
		if (dataAberturaOrdemServico == null) {
			if (other.dataAberturaOrdemServico != null)
				return false;
		} else if (!dataAberturaOrdemServico.equals(other.dataAberturaOrdemServico))
			return false;
		if (dataEntregaOrdemServico == null) {
			if (other.dataEntregaOrdemServico != null)
				return false;
		} else if (!dataEntregaOrdemServico.equals(other.dataEntregaOrdemServico))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (idOrdem != other.idOrdem)
			return false;
		if (servicoFornecedor == null) {
			if (other.servicoFornecedor != null)
				return false;
		} else if (!servicoFornecedor.equals(other.servicoFornecedor))
			return false;
		return true;
	}
	
	
	
	
	//aberto fechado pendente
	
}
