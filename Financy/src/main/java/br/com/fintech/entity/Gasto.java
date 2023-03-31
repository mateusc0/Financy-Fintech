package br.com.fintech.entity;

import java.sql.Date;

public class Gasto extends FluxoImediato {
	
	private int cdGasto;
	private String categoriaGasto;

	public Gasto() {
	}
	
	public Gasto(int cdUser, double vlGasto, Date data, String descricao, String categoriaGasto) {
		super(vlGasto, data, descricao, cdUser);
		this.categoriaGasto = categoriaGasto;
	}
	
	public Gasto(int cdGasto, int cdUser, double vlGasto, Date data, String descricao, String categoriaGasto) {
		super(vlGasto, data, descricao, cdUser);
		this.cdGasto = cdGasto;
		this.categoriaGasto = categoriaGasto;
	}
	
	public String getCategoriaGasto() {
		return categoriaGasto;
	}

	public void setCategoriaGasto(String categoriaGasto) {
		this.categoriaGasto = categoriaGasto;
	}

	public int getCdGasto() {
		return cdGasto;
	}

}
