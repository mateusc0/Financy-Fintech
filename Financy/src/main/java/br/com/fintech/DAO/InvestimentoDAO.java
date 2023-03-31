package br.com.fintech.DAO;

import java.util.List;

import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.Investimento;

public interface InvestimentoDAO {
	
	public void cadastrar(Investimento investimento) throws DBException;
	
	public void atualizar(Investimento investimento) throws DBException;
	
	public void remover(int cdInvestimento, int cdUser) throws DBException;
	
	public List<Investimento> listar(int cdUser);
	
	public Investimento buscar(int cdInvestimento, int cdUser);

}
