package br.com.fintech.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.utils.ListadorEnum;


public class AbrirFormCadastro implements Acao {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tipo = request.getParameter("tipo");
		String msg = request.getParameter("msg");
		request.setAttribute("msg", msg );
		String error = request.getParameter("error");
		request.setAttribute("error", error );
		
	
		switch (tipo) {
		case "gasto":
			List <String> categoriaGasto = ListadorEnum.listarCategoriaGasto();
			request.setAttribute("categoriaGasto", categoriaGasto);

			
				return "forward:formCadastroGasto.jsp";
			
		case "recebimento":

				return "forward:formCadastroRecebimento.jsp";
			
		case "investimento":
			List <String> tipoInvestimento = ListadorEnum.listarTipoInvestimento();
			List <String> bancoCorretora = ListadorEnum.listarBancoCorretora();

			request.setAttribute("tipoInvestimento", tipoInvestimento);
			request.setAttribute("bancoCorretora", bancoCorretora);

				return "forward:formCadastroInvestimento.jsp";
			
		case "objetivoFinanceiro":

				return "forward:formCadastroObjetivoFinanceiro.jsp";

		default:
			System.out.println("Error");
			request.setAttribute("error", "Algum problema ocorreu");
			return "forward:dashboard.jsp";
		}

	}
}
