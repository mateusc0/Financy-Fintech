package br.com.fintech.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.DAO.DAOFactory;
import br.com.fintech.DAO.GastoDAO;
import br.com.fintech.DAO.InvestimentoDAO;
import br.com.fintech.DAO.ObjetivoFinanceiroDAO;
import br.com.fintech.DAO.RecebimentoDAO;
import br.com.fintech.entity.Gasto;
import br.com.fintech.entity.Investimento;
import br.com.fintech.entity.ObjetivoFinanceiro;
import br.com.fintech.entity.Recebimento;
import br.com.fintech.entity.User;

public class Listar implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Listar running");
		
		HttpSession session = request.getSession();
		String tipo = request.getParameter("tipo");
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();
		String msg = request.getParameter("msg");
		request.setAttribute("msg", msg );
		
		System.out.println(msg);
		
		switch (tipo) {
		case "gasto":
			GastoDAO daoG = DAOFactory.getGastoDAO();
			List<Gasto> gastos = daoG.listar(cdUser);
			request.setAttribute("gastos", gastos);
			
			System.out.println("Listagem de gastos completa");
			return "forward:listaGasto.jsp";
			
		case "recebimento":
			RecebimentoDAO daoR = DAOFactory.getRecebimentoDAO();
			List<Recebimento> recebimentos = daoR.listar(cdUser);
			request.setAttribute("recebimentos", recebimentos);
			
			System.out.println("Listagem de recebimentos completa");
			return "forward:listaRecebimento.jsp";
			
		case "investimento":
			InvestimentoDAO daoI = DAOFactory.getInvestimentoDAO();
			List<Investimento> investimentos = daoI.listar(cdUser);
			request.setAttribute("investimentos", investimentos);
			
			System.out.println("Listagem de investimentos completa");
			return "forward:listaInvestimento.jsp";
			
		case "objetivoFinanceiro":
			ObjetivoFinanceiroDAO daoO = DAOFactory.getObjetivoFinanceiroDAO();
			List<ObjetivoFinanceiro> objetivosFinanceiros = daoO.listar(cdUser);
			request.setAttribute("objetivosFinanceiros", objetivosFinanceiros);
			
			System.out.println("Listagem de objetivos financeiros completa");
			return "forward:listaObjetivoFinanceiro.jsp";

		default:
			System.out.println("Error");
			request.setAttribute("error", "Algum problema ocorreu");
			return "forward:dashboard.jsp";
		}

	}

}
