package br.com.fintech.entity;

import java.sql.Date;

public abstract class FluxoMediato {
	
	protected String nome;
	protected double valor;
	public Date dataConclusao;
	protected int CdUser;
	

	protected FluxoMediato() {
	}
	
	protected FluxoMediato(String nome, double valor, Date dataConclusao, int CdUser) {
		this.nome = nome;
		this.valor = valor;
		this.dataConclusao = dataConclusao;
		this.CdUser = CdUser;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConslusao(Date data) {
		this.dataConclusao = data;
	}
	public int getCdUser() {
		return CdUser;
	}
	
}
