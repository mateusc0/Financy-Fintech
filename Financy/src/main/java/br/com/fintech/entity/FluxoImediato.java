package br.com.fintech.entity;

import java.sql.Date;

public abstract class FluxoImediato {
	
	protected double valor;
	protected Date data;
	protected String descricao;
	protected int cdUser;
	
	protected FluxoImediato() {
	}

	
	protected FluxoImediato(double valor, Date data, String descricao, int cdUser) {
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.cdUser = cdUser;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCdUser() {
		return cdUser;
	}

}
