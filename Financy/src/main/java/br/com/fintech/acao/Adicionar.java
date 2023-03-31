package br.com.fintech.acao;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
import br.com.fintech.entity.Gasto;
import br.com.fintech.entity.Investimento;
import br.com.fintech.entity.ObjetivoFinanceiro;
import br.com.fintech.entity.Recebimento;
import br.com.fintech.entity.User;

public class Adicionar implements Acao {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Adicionar running");
		
		HttpSession session = request.getSession();
		String tipo = request.getParameter("tipo");
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		switch (tipo) {
		case "gasto":
			GastoDAO daoG = DAOFactory.getGastoDAO();
			try {
				System.out.println(request.getParameter("dataGasto").getClass());
				double vlGasto = Double.parseDouble(request.getParameter("valorGasto"));
				java.util.Date dtGasto = dateFormat.parse(request.getParameter("dataGasto"));
				java.sql.Date dtGastoSQL = new Date(dtGasto.getTime());
				String dsGasto = request.getParameter("descricaoGasto");
				String catGasto = request.getParameter("categoriaGasto");
				
				Gasto gasto = new Gasto(cdUser, (float) vlGasto, dtGastoSQL, dsGasto, catGasto);
				daoG.cadastrar(gasto);
				
				return "redirect:controller?acao=AbrirFormCadastro&tipo=gasto&msg=Cadastro realizado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=gasto&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=gasto&error=Insira um dado valido";
			}
			
		case "recebimento":
			RecebimentoDAO daoR = DAOFactory.getRecebimentoDAO();
			try {
				
				double vlRecebimento = Double.parseDouble(request.getParameter("valorRecebimento"));
				java.util.Date dtRecebimento = dateFormat.parse(request.getParameter("dataRecebimento"));
				java.sql.Date dtRecebimentoSQL = new Date(dtRecebimento.getTime());
				String dsRecebimento = request.getParameter("descricaoRecebimento");
				
				Recebimento recebimento = new Recebimento(cdUser,(float) vlRecebimento, dtRecebimentoSQL, dsRecebimento);
				daoR.cadastrar(recebimento);
				
				return "redirect:controller?acao=AbrirFormCadastro&tipo=recebimento&msg=Cadastro realizado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=recebimento&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=recebimento&error=Insira um dado valido";
			}
			
		case "investimento":
			InvestimentoDAO daoI = DAOFactory.getInvestimentoDAO();
			try {
				
				String nmInvest = request.getParameter("nomeInvestimento");
				double vlInvest = Double.parseDouble(request.getParameter("valorInvestimento"));
				java.util.Date dtRealizacaoInvest = dateFormat.parse(request.getParameter("dataRealizacao"));
				java.sql.Date dtRealizacaoInvestSQL = new Date(dtRealizacaoInvest.getTime());
				java.util.Date dtConclusaoInvest = dateFormat.parse(request.getParameter("dataConclusao"));
				java.sql.Date dtConclusaoInvestSQL = new Date(dtConclusaoInvest.getTime());
				String tpInvest = request.getParameter("tipoInvestimento");
				String bancoCorretoraInvest = request.getParameter("bancoCorretora");
				
				Investimento investimento = new Investimento(cdUser, nmInvest, vlInvest, dtRealizacaoInvestSQL,
						dtConclusaoInvestSQL, tpInvest, bancoCorretoraInvest);
				daoI.cadastrar(investimento);
				
				return "redirect:controller?acao=AbrirFormCadastro&tipo=investimento&msg=Cadastro realizado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=investimento&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=investimento&error=Insira um dado valido";
			}
			
		case "objetivoFinanceiro":
			ObjetivoFinanceiroDAO daoO = DAOFactory.getObjetivoFinanceiroDAO();
			try {
				String nmObjetivo = request.getParameter("nomeObjetivoFinanceiro");
				double vlObjetivo = Double.parseDouble(request.getParameter("valorObjetivoFinanceiro"));
				java.util.Date dtConclusaoObjetivo = dateFormat.parse(request.getParameter("dataObjetivoFinanceiro"));
				java.sql.Date dtConclusaoObjetivoSQL = new Date(dtConclusaoObjetivo.getTime());
				String dsObjetivo = request.getParameter("descricaoObjetivoFinanceiro");
				
				ObjetivoFinanceiro objetivoFinanceiro = new ObjetivoFinanceiro(cdUser, nmObjetivo,
						vlObjetivo, dtConclusaoObjetivoSQL, dsObjetivo);
				daoO.cadastrar(objetivoFinanceiro);
				
				return "redirect:controller?acao=AbrirFormCadastro&tipo=objetivoFinanceiro&msg=Cadastro realizado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=objetivoFinanceiro&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormCadastro&tipo=objetivoFinanceiro&error=Insira um dado valido";
			}

		default:
			System.out.println("Error");
			request.setAttribute("error", "Algum problema ocorreu");
			return "forward:dashboard.jsp";
		}

	}
}
