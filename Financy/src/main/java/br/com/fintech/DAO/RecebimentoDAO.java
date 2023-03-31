package br.com.fintech.DAO;

import java.util.List;

import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.Recebimento;

public interface RecebimentoDAO {
	
	public void cadastrar(Recebimento recebimento) throws DBException;
	
	public void atualizar(Recebimento recebimento) throws DBException;
	
	public void remover(int cdRecebimento, int cdUser) throws DBException;
	
	public List<Recebimento> listar(int cdUser);
	
	public Recebimento buscar(int cdRecebimento, int cdUser);

}
