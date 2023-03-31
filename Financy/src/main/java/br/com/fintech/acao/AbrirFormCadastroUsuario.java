package br.com.fintech.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.utils.ListadorEnum;

public class AbrirFormCadastroUsuario implements Acao {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List <String> sexo = ListadorEnum.listarTipoSexo();
		request.setAttribute("sexo", sexo);
		String msg = request.getParameter("msg");
		request.setAttribute("msg", msg );
		String error = request.getParameter("error");
		request.setAttribute("error", error );
		
		return "forward:formCadastroUsuario.jsp";
	}

}
