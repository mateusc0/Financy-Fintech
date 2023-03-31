package br.com.fintech.acao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.BO.EmailBO;
import br.com.fintech.DAO.DAOFactory;
import br.com.fintech.DAO.UserDAO;
import br.com.fintech.Exception.EmailException;
import br.com.fintech.entity.User;
import br.com.fintech.utils.Criptografia;

public class Login implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String senhaCP = null;
		try {
			senhaCP = Criptografia.criptografar(senha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Logando " + email);
		UserDAO dao = DAOFactory.getUserDAO();
		EmailBO bo = new EmailBO();
		
		User usuario = dao.buscarPorEmail(email, senhaCP);
		if(usuario != null) {
			System.out.println("Usuario existe");
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuario);
			session.setAttribute("senhaUsuarioLogado", senha);
			
			return "redirect:controller?acao=AbrirDashboard";
		} else {
			request.setAttribute("error", "Usuário e/ou senha inválidos");
			return "forward:index.jsp";
		}
	}

}
