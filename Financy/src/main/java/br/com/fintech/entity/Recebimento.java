package br.com.fintech.entity;

import java.sql.Date;

public class Recebimento extends FluxoImediato {
	
	private int cdRecebimento;
	

	public Recebimento() {
	}
	
	public Recebimento(int cdUser,double valor, Date data, String descricao) {
		super(valor, data, descricao, cdUser);			
	}
	
	public Recebimento(int cdRecebimento, int cdUser,double valor, Date data, String descricao) {
		super(valor, data, descricao, cdUser);
		this.cdRecebimento = cdRecebimento;
	}


	public int getCdRecebimento() {
		return cdRecebimento;
	}
	
}