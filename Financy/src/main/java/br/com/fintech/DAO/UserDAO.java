package br.com.fintech.DAO;

import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.User;

public interface UserDAO {
	
	public void cadastrar(User user) throws DBException;
	
	public void atualizar(User user) throws DBException;
	
	public void remover(int cdUser) throws DBException;
	
	public User buscarPorEmail(String ds_email, String ds_senha);
	
	public User buscarPorId(int cdUser);
}
