package edu.modelo;

import java.util.Date;

public class Entrega {
	
	Date dataSaida;
	Date dataRetorno;
	String observacao;
	Recebimento recebimento;

	public Date getDataSaida() {
		return dataSaida;
	} 
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Recebimento getRecebimento() {
		return recebimento;
	}
	public void setRecebimento(Recebimento recebimento) {
		this.recebimento = recebimento;
	}
}
