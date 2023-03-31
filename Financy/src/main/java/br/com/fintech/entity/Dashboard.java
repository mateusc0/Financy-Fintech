package br.com.fintech.entity;

public class Dashboard {
	private String nomeUser;
	private double totalRecebMes;
	private double totalGastoMes;
	private double totalInvest;
	private Gasto ultimoGasto;
	private Investimento ultimoInvestimento;
	private double saldoMes;
	
	public Dashboard(String nomeUser, double totalRecebMes, double totalGastoMes,
			double totalInvest, Gasto ultimoGasto, Investimento ultimoInvestimento) {
		
		this.nomeUser = nomeUser;
		this.totalRecebMes = totalRecebMes;
		this.totalGastoMes = totalGastoMes;
		this.totalInvest = totalInvest;
		this.ultimoGasto = ultimoGasto;
		this.ultimoInvestimento = ultimoInvestimento;
		this.saldoMes = totalRecebMes - totalGastoMes;
		
	}
	
	public String getNomeUser() {
		return nomeUser;
	}

	public double getTotalRecebMes() {
		return totalRecebMes;
	}

	public double getTotalGastoMes() {
		return totalGastoMes;
	}

	public double getTotalInvest() {
		return totalInvest;
	}
	
	public Gasto getUltimoGasto() {
		return ultimoGasto;
	}

	public Investimento getUltimoInvestimento() {
		return ultimoInvestimento;
	}
	

	public double getSaldoMes() {
		return saldoMes;
	}

	
	
}
