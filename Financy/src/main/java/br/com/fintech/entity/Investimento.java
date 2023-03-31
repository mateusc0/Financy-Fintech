package br.com.fintech.entity;

import java.sql.Date;

public class Investimento extends FluxoMediato {
	
	private int cdInvestimento;
	private Date dataRealizacao;
	private String tipoInvestimento;
	private String bancoCorretora;
	
	public Investimento() {
	}
	
	public Investimento(int cdUser, String nome, double valor, Date dataRealizacao, Date dataConclusao,
			String tipoInvestimento, String bancoCorretora) {
		super(nome, valor, dataConclusao, cdUser);
		this.dataRealizacao = dataRealizacao;
		this.tipoInvestimento = tipoInvestimento;
		this.bancoCorretora = bancoCorretora;
	}
	
	public Investimento(int cdInvestimento, int cdUser, String nome, double valor, Date dataRealizacao, Date dataConclusao,
			String tipoInvestimento, String bancoCorretora) {
		super(nome, valor, dataConclusao, cdUser);
		this.cdInvestimento = cdInvestimento;
		this.dataRealizacao = dataRealizacao;
		this.tipoInvestimento = tipoInvestimento;
		this.bancoCorretora = bancoCorretora;
	}
	
	public Date getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}
	public String getTipoInvestimento() {
		return tipoInvestimento;
	}
	public void setTipoInvestimento(String tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}
	public String getBancoCorretora() {
		return bancoCorretora;
	}
	public void setBancoCorretora(String bancoCorretora) {
		this.bancoCorretora = bancoCorretora;
	}

	public int getCdInvestimento() {
		return cdInvestimento;
	}
 
}
