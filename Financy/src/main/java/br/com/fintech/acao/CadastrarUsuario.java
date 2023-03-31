package br.com.fintech.acao;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.DAO.DAOFactory;
import br.com.fintech.DAO.UserDAO;
import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.User;

public class CadastrarUsuario implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO daoU = DAOFactory.getUserDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			String nmUser = request.getParameter("nomeUsuario");
			String sexo = request.getParameter("sexoUsuario");
			java.util.Date dtNascimento = dateFormat.parse(request.getParameter("dataNascimento"));
			java.sql.Date dtNascimentoSQL = new Date(dtNascimento.getTime());
			String email = request.getParameter("emailUsuario");
			String senha = request.getParameter("senhaUsuario");
			
			User usuario = new User(nmUser, sexo, dtNascimentoSQL, email, senha);
			daoU.cadastrar(usuario);
		
			System.out.println("Cadastro de usuário completa");
			return "redirect:controller?acao=AbrirFormCadastroUsuario&msg=Usuario cadastrado com sucesso";
		} catch (DBException db) {
			db.printStackTrace();
			System.out.println("Cadastro de usuário falhou");
			return "redirect:controller?acao=AbrirFormCadastroUsuario&error=Tivemos um problema com o banco de dados";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cadastro de usuário falhou");
			return "redirect:controller?acao=AbrirFormCadastroUsuario&error=Insira um dado valido";
	
		}
	}
}
