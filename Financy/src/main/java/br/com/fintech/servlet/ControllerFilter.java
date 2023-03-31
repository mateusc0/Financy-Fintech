package br.com.fintech.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.acao.Acao;

//@WebFilter("/controller")
public class ControllerFilter implements Filter {
       
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Controller running");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String paramAcao = request.getParameter("acao");
		String nomeClasse = "br.com.fintech.acao." + paramAcao;
		String nome;
		
		try {
			Class<?> classe = Class.forName(nomeClasse);
			Acao acao = (Acao) classe.getDeclaredConstructor().newInstance();
			nome = acao.execute(request, response);
			
			String[] tipoDeRespostaEEndereco = nome.split(":");
			if (tipoDeRespostaEEndereco[0].equals("forward")) {
				RequestDispatcher rd = request.getRequestDispatcher(tipoDeRespostaEEndereco[1]);
				rd.forward(request, response);
		
			}else {
				response.sendRedirect(tipoDeRespostaEEndereco[1]);
			}
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
