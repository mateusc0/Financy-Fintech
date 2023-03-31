package br.com.fintech.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.DAO.DAOFactory;
import br.com.fintech.DAO.GastoDAO;
import br.com.fintech.DAO.InvestimentoDAO;
import br.com.fintech.DAO.ObjetivoFinanceiroDAO;
import br.com.fintech.DAO.RecebimentoDAO;
import br.com.fintech.DAO.UserDAO;
import br.com.fintech.Exception.DBException;
import br.com.fintech.entity.User;

public class Remover implements Acao {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Remover running");
		
		HttpSession session = request.getSession();
		String tipo = request.getParameter("tipo");
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();	
		
		switch (tipo) {
		case "gasto":
			GastoDAO daoG = DAOFactory.getGastoDAO();
			try {
				
				int cdGasto = Integer.parseInt(request.getParameter("codigoGasto"));

				daoG.remover(cdGasto, cdUser);
				
				System.out.println("Remoção completa");				
				return "redirect:controller?acao=Listar&tipo=gasto&msg=Removido com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=gasto&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=gasto&error=Falha ao remover";
			}
			
		case "recebimento":
			RecebimentoDAO daoR = DAOFactory.getRecebimentoDAO();
			try {
				
				int cdRecebimento = Integer.parseInt(request.getParameter("codigoRecebimento"));
				
				daoR.remover(cdRecebimento, cdUser);
				
				System.out.println("Remoção completa");				
				return "redirect:controller?acao=Listar&tipo=recebimento&msg=Removido com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=recebimento&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=recebimento&error=Falha ao remover";
			}
			
		case "investimento":
			InvestimentoDAO daoI = DAOFactory.getInvestimentoDAO();
			try {
				
				int cdInvest = Integer.parseInt(request.getParameter("codigoInvestimento"));

				daoI.remover(cdInvest, cdUser);
				
				System.out.println("Remoção completa");				
				return "redirect:controller?acao=Listar&tipo=investimento&msg=Removido com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=investimento&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=investimento&error=Falha ao remover";
			}
			
		case "objetivoFinanceiro":
			ObjetivoFinanceiroDAO daoO = DAOFactory.getObjetivoFinanceiroDAO();
			try {
				
				int cdObjetivo = Integer.parseInt(request.getParameter("codigoObjetivoFinanceiro"));

				daoO.remover(cdObjetivo, cdUser);
				
				System.out.println("Remoção completa");				
				return "redirect:controller?acao=Listar&tipo=objetivoFinanceiro&msg=Removido com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=objetivoFinanceiro&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Remoção falhou");
				return "redirect:controller?acao=Listar&tipo=objetivoFinanceiro&error=Falha ao remover";
			}
			
			
		case "usuario":
			UserDAO daoU = DAOFactory.getUserDAO();
			try {

				daoU.remover(cdUser);
				
				request.setAttribute("msg", "Usuário Removido");
				return "forward:index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "A remoção falhou");
			}
			
			System.out.println("Adição de objetivos financeiros completa");
			return "redirect:controller?acao=Listar&tipo=objetivoFinanceiro";

		default:
			System.out.println("Error");
			request.setAttribute("error", "Algum problema ocorreu");
			return "forward:dashboard.jsp";
		}

	}
}
