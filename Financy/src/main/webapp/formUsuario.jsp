<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.rtl.min.css"
      integrity="sha384-dc2NSrAXbAkjrdm9IYrX10fQq9SDG6Vjz7nQVKdKcJl3pC+k37e7qJR5MVSCS+wR"
      crossorigin="anonymous"
    />
    <script
      defer
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
      crossorigin="anonymous"
    ></script>
    
    <script 
    	defer 
    	type="text/javascript" 
    	src="./js/jquery-3.6.1.js"
    ></script>
	<script 
		defer 
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js"
	></script>

    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="./assets/css/dashboard.css" />
    <link rel="stylesheet" href="./assets/css/user.css" />

    <title>Tela Usuário</title>
  </head>
  <body class="d-flex flex-row justify-content-center">
    <div class="sidebar d-none d-lg-flex flex-column align-items-center">
      <h1 class="logo align-self-center">Financy</h1>
      <div class="links d-flex flex-column justify-content-around">
        <a href="controller?acao=AbrirDashboard">Dashboard</a>
        <a href="controller?acao=Listar&tipo=recebimento">Recebimentos</a>
        <a href="controller?acao=Listar&tipo=gasto">Gastos</a>
        <a href="controller?acao=Listar&tipo=investimento">Investimentos</a>
        <a href="controller?acao=Listar&tipo=objetivoFinanceiro">Objetivos Financeiros</a>
        <a class="current-page" href="controller?acao=AbrirFormUsuario">Usuário</a>
        <a href="controller?acao=Logout">Logout</a>
      </div>
    </div>
    <div class="main-box d-flex flex-column justify-content-around">
      <div class="navbar d-flex flex-row d-lg-none justify-content-between">
        <h1 class="nav-logo">Financy</h1>
        <button class="navbar-toggler hamburger" type="button" data-bs-toggle="collapse" data-bs-target="#sidebar" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon hamburger-icon"></span>
        </a>
      </div>
      <c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty error }">
		<div class="alert alert-danger">${error}</div>
	</c:if>
      <div class="user-container col-12 d-flex flex-column justify-content-center align-items-center">
      <h2 class="username">Tela Usuário</h2>
        <div class="user-box d-flex flex-column">
          <form class="info-container d-flex flex-column justify-content-evenly align-items-center" action="controller" method="post">
          	<input type="hidden" value="Atualizar" name="acao">
			<input type="hidden" value="usuario" name="tipo">
			<input type="hidden" value="${usuario.cdUser}}" name="codigoUsuario">	
            <div class="d-flex flex-column col-8">
              <label class="user-tag">Nome completo</label>
              <input class="user-info" type="text" name="nomeUsuario" id="nome" value="${usuario.nome}">
            </div>  
            <div class="d-flex flex-column col-8">
              <label class="user-tag ">Data de Nascimento</label>
              <div class="d-flex flex-row">
                <input class="date user-info" type="text" name="dataNascimento" id="dtNasc" value="<fmt:formatDate value="${usuario.dtNascimento}" pattern="dd/MM/yyy"/>">
                  <svg class="svg" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" style="fill: rgba(255, 255, 255, 1);transform: 0;msFilter:0;">
                    <path d="M7 11h2v2H7zm0 4h2v2H7zm4-4h2v2h-2zm0 4h2v2h-2zm4-4h2v2h-2zm0 4h2v2h-2z"></path>
                    <path d="M5 22h14c1.103 0 2-.897 2-2V6c0-1.103-.897-2-2-2h-2V2h-2v2H9V2H7v2H5c-1.103 0-2 .897-2 2v14c0 1.103.897 2 2 2zM19 8l.001 12H5V8h14z"></path>
                  </svg>
              </div>
            </div> 
              <div class="d-flex flex-column col-8">
              <label class="user-tag">Gênero</label>
              <select name="sexoUsuario" id="genero" class="user-info">
				<c:forEach items="${sexo}" var="sx">
   					<c:if test="${sx == usuario.sexo}">
						<option class="text-light" value="${sx}" selected="selected">${sx}</option>
					</c:if>
					<c:if test="${sx != usuario.sexo}">
						<option class="text-light" value="${sx}">${sx}</option>
					</c:if>
				</c:forEach>
			</select>  
            </div> 
            <div class="d-flex flex-column col-8">
              <label class="user-tag">Email</label>
              <input class="user-info" type="email" name="emailUsuario" id="email" value="${usuario.email}">
            </div> 
              <div class="d-flex flex-column col-8">
              <label class="user-tag">Senha</label>
              <input class="user-info" type="password" name="senhaUsuario" id="senha" value="${sessionScope.senhaUsuarioLogado}">
            </div>
            <div class="d-flex flex-row justify-content-around align-items-center my-2 w-100">
            <button class="btn-save btn btn-outline-light" type="submit">Salvar</button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#excluirModal" >
  				Remover usuário
			</button>
            </div> 
          </form>
        </div>
      </div>
    </div>
    
      <!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-danger">
        <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo excluir seu usuário?</h5>
      </div>
      <div class="modal-body">
        <form action="controller" method="post">
        	<input type="hidden" name="acao" value="Remover">
        	<input type="hidden" name="tipo" value="usuario">
        	<button type="submit" class="btn btn-danger">Excluir</button>
        	<button type="button" class="btn btn-warning" data-bs-dismiss="modal" aria-label="Close">Cancelar</button>        
        </form>
      </div>
    </div>
  </div>
</div>
    
    <div
    class="offcanvas offcanvas-end container-fluid sidebar"
    tabindex="-1"
    id="sidebar"
    aria-labelledby="tela de login"
  >
    <div class="d-flex flex-row justify-content-end">
      <button
        type="button"
        class="btn-close btn-close-white sidebar-close"
        data-bs-target="#sidebar"
        data-bs-toggle="collapse"

      ></button>
    </div>
    <h1 class="logo align-self-center">Financy</h1>
    <div class="links d-flex flex-column justify-content-around">
      <a href="controller?acao=AbrirDashboard">Dashboard</a>
      <a href="controller?acao=Listar&tipo=recebimento">Recebimentos</a>
      <a href="controller?acao=Listar&tipo=gasto">Gastos</a>
      <a href="controller?acao=Listar&tipo=investimento">Investimentos</a>
      <a href="controller?acao=Listar&tipo=objetivoFinanceiro">Objetivos Financeiros</a>
      <a class="current-page" href="controller?acao=AbrirFormUsuario">Usuário</a>
      <a href="controller?acao=Logout">Logout</a>
    </div>
  </div>
  </body>
</html>
