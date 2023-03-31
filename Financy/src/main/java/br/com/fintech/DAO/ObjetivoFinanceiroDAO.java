package br.com.fintech.DAO;

import java.util.List;

import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.ObjetivoFinanceiro;

public interface ObjetivoFinanceiroDAO {
	
	public void cadastrar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException;
	
	public void atualizar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException;
	
	public void remover(int cdObjetivoFinanceiro, int cdUser) throws DBException;
	
	public List<ObjetivoFinanceiro> listar(int cdUser);
	
	public ObjetivoFinanceiro buscar(int cdObjetivoFinanceiro, int cdUser);
}
