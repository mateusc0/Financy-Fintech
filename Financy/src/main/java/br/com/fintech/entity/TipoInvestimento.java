package br.com.fintech.entity;

public enum TipoInvestimento {
	TESOURO_DIRETO ("Tesouro Direto"),
	CDB ("CDB"),
	FII ("FII"),
	LCI ("LCI"),
	LCA ("LCA"),
	LC ("LC"),
	DEBENTURES ("Debêntures"),
	POUPANCA ("Poupança"),
	ACOES ("Ações"),
	ALUGUEL_DE_ACOES ("Aluguel de Ações"),
	CONTRATOS_FUTUROS ("Contratos Futuros"),
	MINICONTRATOS ("Minicontratos"),
	OPCOES ("Opções"),
	FUTURO_DE_ACOES ("Futuro de Ações"),
	MICRO_SNP_500 ("Micro SNP 500"),
	FUNDO_IMOBILIARIO ("Fundo Imobiliário"),
	IPO ("IPO"),
	CRI ("CRI"),
	CRA ("CRA "),
	COE ("COE");
	
	private String name;

	TipoInvestimento(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
