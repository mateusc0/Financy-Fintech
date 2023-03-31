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
import br.com.fintech.utils.ListadorEnum;

public class AbrirFormAtualizar implements Acao {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String tipo = request.getParameter("tipo");
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();
		String error = request.getParameter("error");
		request.setAttribute("error", error );
		
		switch (tipo) {
		case "gasto":
			GastoDAO daoG = DAOFactory.getGastoDAO();
			
				int cdGasto = Integer.parseInt(request.getParameter("codigoGasto"));
				List <String> categoriaGasto = ListadorEnum.listarCategoriaGasto();
				
				System.out.println(cdGasto);
				
				Gasto gasto = daoG.buscar(cdGasto, cdUser);
				request.setAttribute("gasto", gasto);
				request.setAttribute("categoriaGasto", categoriaGasto);
				
				System.out.println(gasto.getCdGasto());
				System.out.println(gasto.getCdUser());
				System.out.println(gasto.getCategoriaGasto());
				System.out.println(gasto.getDescricao());
				
				return "forward:formAtualizarGasto.jsp";
			
		case "recebimento":
			RecebimentoDAO daoR = DAOFactory.getRecebimentoDAO();

				int cdRecebimento = Integer.parseInt(request.getParameter("codigoRecebimento"));
				
				Recebimento recebimento = daoR.buscar(cdRecebimento, cdUser);
				request.setAttribute("recebimento", recebimento);
				
				return "forward:formAtualizarRecebimento.jsp";
			
		case "investimento":
			InvestimentoDAO daoI = DAOFactory.getInvestimentoDAO();

				int cdInvest = Integer.parseInt(request.getParameter("codigoInvestimento"));
				List <String> tipoInvestimento = ListadorEnum.listarTipoInvestimento();
				List <String> bancoCorretora = ListadorEnum.listarBancoCorretora();

				Investimento investimento = daoI.buscar(cdInvest, cdUser);
				request.setAttribute("investimento", investimento);	
				request.setAttribute("tipoInvestimento", tipoInvestimento);
				request.setAttribute("bancoCorretora", bancoCorretora);
				
				return "forward:formAtualizarInvestimento.jsp";
			
		case "objetivoFinanceiro":
			ObjetivoFinanceiroDAO daoO = DAOFactory.getObjetivoFinanceiroDAO();

				int cdObjetivo = Integer.parseInt(request.getParameter("codigoObjetivoFinanceiro"));

				ObjetivoFinanceiro objetivoFinanceiro = daoO.buscar(cdObjetivo, cdUser);
				request.setAttribute("objetivoFinanceiro", objetivoFinanceiro);

				return "forward:formAtualizarObjetivoFinanceiro.jsp";

		default:
			System.out.println("Error");
			request.setAttribute("error", "Algum problema ocorreu");
			return "forward:dashboard.jsp";
		}

	}
}
