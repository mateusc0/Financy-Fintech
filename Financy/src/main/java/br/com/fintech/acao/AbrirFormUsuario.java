package br.com.fintech.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.DAO.DAOFactory;
import br.com.fintech.DAO.DashboardDAO;
import br.com.fintech.DAO.UserDAO;
import br.com.fintech.entity.Dashboard;
import br.com.fintech.entity.User;
import br.com.fintech.utils.ListadorEnum;

public class AbrirFormUsuario implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();
		List <String> sexo = ListadorEnum.listarTipoSexo();
		String msg = request.getParameter("msg");
		request.setAttribute("msg", msg );
		String error = request.getParameter("error");
		request.setAttribute("error", error );
		
		UserDAO dao = DAOFactory.getUserDAO();
		User usuario = dao.buscarPorId(cdUser);
		request.setAttribute("usuario", usuario);
		request.setAttribute("sexo", sexo);
		
		return "forward:formUsuario.jsp";
	}

}
