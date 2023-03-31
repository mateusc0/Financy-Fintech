package br.com.fintech.entity;

public enum CategoriaGasto {
	ALUGUEL ("Aluguel"),
	ALIMENTACAO ("Alimentação"),
	VESTIMENTA ("Vestimenta"),
	ACADEMIA ("Academia"),
	TRANSPORTE ("Transporte"),
	IMPOSTO ("Imposto"),
	RECREACAO ("Recreação"),
	EDUCACAO ("Educação"),
	ELETRONICO ("Eletrônico"),
	ELETRODOMESTICO ("Eletrodoméstico");
	
	private String name;
	
	private CategoriaGasto (String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
