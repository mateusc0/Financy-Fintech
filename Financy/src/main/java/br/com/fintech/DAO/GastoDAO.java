package br.com.fintech.DAO;

import java.util.List;

import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.Gasto;

public interface GastoDAO {
	
	public void cadastrar(Gasto gasto) throws DBException;
	
	public void atualizar(Gasto gasto) throws DBException;
	
	public void remover(int cdGasto, int cdUser) throws DBException;
	
	public List<Gasto> listar(int cdUser);
	
	public Gasto buscar(int cdGasto, int cdUser);
}
