package br.com.fintech.entity;

import java.sql.Date;

public class ObjetivoFinanceiro extends FluxoMediato {
	
	private int cdObjtvFinanceiro;
	private String descricao;
	
	public ObjetivoFinanceiro() {
	}
	
	public ObjetivoFinanceiro(int cdUser, String nome, double valor, Date dataConclusao, String descricao) {
		super(nome, valor, dataConclusao, cdUser);
		this.descricao = descricao;
	}
	
	public ObjetivoFinanceiro(int cdObjtvFinanceiro, int cdUser, String nome, double valor, Date dataConclusao, String descricao) {
		super(nome, valor, dataConclusao, cdUser);
		this.cdObjtvFinanceiro = cdObjtvFinanceiro;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCdObjtvFinanceiro() {
		return cdObjtvFinanceiro;
	}

}
