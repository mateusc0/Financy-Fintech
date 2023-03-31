package br.com.fintech.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.DAO.DAOFactory;
import br.com.fintech.DAO.DashboardDAO;
import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.Dashboard;
import br.com.fintech.entity.User;

public class AbrirDashboard implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();	
		
		DashboardDAO dao = DAOFactory.getDashboardDAO();
		Dashboard dashboard;
		try {
			dashboard = dao.createDashboard(cdUser);
			request.setAttribute("dashboard", dashboard);
			request.setAttribute("msg", "Montagem de Dashboard com sucesso");
			
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "Montagem de Dashboard falhou");
		}
		return "forward:dashboard.jsp";
		
	}

}
