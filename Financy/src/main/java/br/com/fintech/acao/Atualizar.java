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

public class Atualizar implements Acao {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Atualizar running");
		
		HttpSession session = request.getSession();
		String tipo = request.getParameter("tipo");
		User usuarioLogado = (User) session.getAttribute("usuarioLogado");
		int cdUser = usuarioLogado.getCdUser();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		switch (tipo) {
		case "gasto":
			GastoDAO daoG = DAOFactory.getGastoDAO();
			try {
				
				int cdGasto = Integer.parseInt(request.getParameter("codigoGasto"));
				double vlGasto = Double.parseDouble(request.getParameter("valorGasto"));
				java.util.Date dtGasto = dateFormat.parse(request.getParameter("dataGasto"));
				java.sql.Date dtGastoSQL = new Date(dtGasto.getTime());
				String dsGasto = request.getParameter("descricaoGasto");
				String catGasto = request.getParameter("categoriaGasto");
				
				Gasto gasto = new Gasto(cdGasto, cdUser, (double) vlGasto, dtGastoSQL, dsGasto, catGasto);
				System.out.println(gasto.getValor());
				System.out.println(gasto.getData());
				System.out.println(gasto.getCdGasto());
				System.out.println(gasto.getDescricao());
				System.out.println(gasto.getCdUser());
				System.out.println(gasto.getCdGasto());
				daoG.atualizar(gasto);
				
				System.out.println("Atualização de gastos completa");
				return "redirect:controller?acao=Listar&tipo=gasto&msg=Modificado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				int cdGasto = Integer.parseInt(request.getParameter("codigoGasto"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=gasto&error=Tivemos um problema com o banco de dados&codigoGasto=" + cdGasto;
			} catch (Exception e) {
				e.printStackTrace();
				int cdGasto = Integer.parseInt(request.getParameter("codigoGasto"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=gasto&error=Insira um dado valido&codigoGasto=" + cdGasto;
			}
			
			
			
		case "recebimento":
			RecebimentoDAO daoR = DAOFactory.getRecebimentoDAO();
			try {
				System.out.println(request.getParameter("codigoRecebimento"));
				int cdRecebimento = Integer.parseInt(request.getParameter("codigoRecebimento"));
				double vlRecebimento = Double.parseDouble(request.getParameter("valorRecebimento"));
				java.util.Date dtRecebimento = dateFormat.parse(request.getParameter("dataRecebimento"));
				java.sql.Date dtRecebimentoSQL = new Date(dtRecebimento.getTime());
				String dsRecebimento = request.getParameter("descricaoRecebimento");
				
				Recebimento recebimento = new Recebimento(cdRecebimento, cdUser,(float) vlRecebimento, dtRecebimentoSQL, dsRecebimento);
				daoR.atualizar(recebimento);
				
				System.out.println("Atualização de recebimentos completa");
				return "redirect:controller?acao=Listar&tipo=recebimento&msg=Modificado com sucesso";
				
			} catch (DBException db) {
				db.printStackTrace();
				int cdRecebimento = Integer.parseInt(request.getParameter("codigoRecebimento"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=recebimento&error=Tivemos um problema com o banco de dados&codigoRecebimento=" + cdRecebimento;
			} catch (Exception e) {
				e.printStackTrace();
				int cdRecebimento = Integer.parseInt(request.getParameter("codigoRecebimento"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=recebimento&error=Insira um dado valido&codigoRecebimento=" + cdRecebimento;
			}
			
			
			
		case "investimento":
			InvestimentoDAO daoI = DAOFactory.getInvestimentoDAO();
			try {
				
				int cdInvest = Integer.parseInt(request.getParameter("codigoInvestimento"));
				String nmInvest = request.getParameter("nomeInvestimento");
				double vlInvest = Double.parseDouble(request.getParameter("valorInvestimento"));
				java.util.Date dtRealizacaoInvest = dateFormat.parse(request.getParameter("dataRealizacao"));
				java.sql.Date dtRealizacaoInvestSQL = new Date(dtRealizacaoInvest.getTime());
				java.util.Date dtConclusaoInvest = dateFormat.parse(request.getParameter("dataConclusao"));
				java.sql.Date dtConclusaoInvestSQL = new Date(dtConclusaoInvest.getTime());
				String tpInvest = request.getParameter("tipoInvestimento");
				String bancoCorretoraInvest = request.getParameter("bancoCorretora");
				
				Investimento investimento = new Investimento(cdInvest, cdUser, nmInvest, vlInvest, dtRealizacaoInvestSQL,
						dtConclusaoInvestSQL, tpInvest, bancoCorretoraInvest);
				daoI.atualizar(investimento);
				
				System.out.println("Atualização de investimentos completa");
				return "redirect:controller?acao=Listar&tipo=investimento&msg=Modificado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				int cdInvest = Integer.parseInt(request.getParameter("codigoInvestimento"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=investimento&error=Tivemos um problema com o banco de dados&codigoInvestimento=" + cdInvest;
			} catch (Exception e) {
				e.printStackTrace();
				int cdInvest = Integer.parseInt(request.getParameter("codigoInvestimento"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=investimento&error=Insira um dado valido&codigoInvestimento=" + cdInvest;
			}
			
			
			
		case "objetivoFinanceiro":
			ObjetivoFinanceiroDAO daoO = DAOFactory.getObjetivoFinanceiroDAO();
			try {
				
				int cdObjetivo = Integer.parseInt(request.getParameter("codigoObjetivoFinanceiro"));
				String nmObjetivo = request.getParameter("nomeObjetivoFinanceiro");
				double vlObjetivo = Double.parseDouble(request.getParameter("valorObjetivoFinanceiro"));
				java.util.Date dtConclusaoObjetivo = dateFormat.parse(request.getParameter("dataObjetivoFinanceiro"));
				java.sql.Date dtConclusaoObjetivoSQL = new Date(dtConclusaoObjetivo.getTime());
				String dsObjetivo = request.getParameter("descricaoObjetivoFinanceiro");
				
				ObjetivoFinanceiro objetivoFinanceiro = new ObjetivoFinanceiro(cdObjetivo, cdUser, nmObjetivo,
						vlObjetivo, dtConclusaoObjetivoSQL, dsObjetivo);
				daoO.atualizar(objetivoFinanceiro);
				
				System.out.println("Atualização de objetivos financeiros completa");
				return "redirect:controller?acao=Listar&tipo=objetivoFinanceiro&msg=Modificado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				int cdObjetivo = Integer.parseInt(request.getParameter("codigoObjetivoFinanceiro"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=objetivoFinanceiro&error=Tivemos um problema com o banco de dados&codigoObjetivoFinanceiro=" + cdObjetivo;
			} catch (Exception e) {
				e.printStackTrace();
				int cdObjetivo = Integer.parseInt(request.getParameter("codigoObjetivoFinanceiro"));
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&tipo=objetivoFinanceiro&error=Insira um dado valido&codigoObjetivoFinanceiro=" + cdObjetivo;
			}
			
			
			
		case "usuario":
			UserDAO daoU = DAOFactory.getUserDAO();
			try {

				String nmUser = request.getParameter("nomeUsuario");
				String sexo = request.getParameter("sexoUsuario");
				java.util.Date dtNascimento = dateFormat.parse(request.getParameter("dataNascimento"));
				java.sql.Date dtNascimentoSQL = new Date(dtNascimento.getTime());
				String email = request.getParameter("emailUsuario");
				String senha = request.getParameter("senhaUsuario");
				
				session.setAttribute("senhaUsuarioLogado", senha);
				
				User usuario = new User(cdUser, nmUser, sexo, dtNascimentoSQL, email, senha);
				System.out.println(usuario.getCdUser());
				System.out.println(usuario.getNome());
				System.out.println(usuario.getSexo());
				System.out.println(usuario.getDtNascimento());
				System.out.println(usuario.getEmail());
				System.out.println(usuario.getSenha());
				daoU.atualizar(usuario);
				
				System.out.println("Atualização de usuário completa");
				return "redirect:controller?acao=AbrirFormUsuario&msg=Modificado com sucesso";
			} catch (DBException db) {
				db.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&error=Tivemos um problema com o banco de dados";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Atualização de recebimentos falhou");
				return "redirect:controller?acao=AbrirFormAtualizar&error=Insira um dado valido";
			}


		default:
			System.out.println("Error");
			request.setAttribute("error", "Algum problema ocorreu");
			return "forward:dashboard.jsp";
		}

	}
}
