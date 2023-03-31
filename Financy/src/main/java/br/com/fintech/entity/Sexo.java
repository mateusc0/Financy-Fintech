package br.com.fintech.entity;

public enum Sexo {
	MASCULINO ("Masculino"),
	FEMININO ("Feminino");
	
	private String name;

	Sexo(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
                               