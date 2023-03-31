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
	
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    
    <link rel="stylesheet" href="./assets/css/dashboard.css" />
    <link rel="stylesheet" href="./assets/css/user.css" />
    <link rel="stylesheet" href="./assets/css/table.css">

    <title>Tela Recebimentos</title>
  </head>
  <body class="d-flex flex-row justify-content-center">
    <div class="sidebar d-none d-lg-flex flex-column align-items-center">
      <h1 class="logo align-self-center">Financy</h1>
      <div class="links d-flex flex-column justify-content-around">
        <a href="controller?acao=AbrirDashboard">Dashboard</a>
        <a href="controller?acao=Listar&tipo=recebimento">Recebimentos</a>
        <a href="controller?acao=Listar&tipo=gasto">Gastos</a>
        <a href="controller?acao=Listar&tipo=investimento">Investimentos</a>
        <a class="current-page" href="controller?acao=Listar&tipo=objetivoFinanceiro">Objetivos Financeiros</a>
        <a href="controller?acao=AbrirFormUsuario">Usuário</a>
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
      <div class="user-container col-12 d-flex flex-column justify-content-center align-items-center">
      <h2 class="username">Objetivos Financeiros</h2>
            <div class="col-12 d-flex flex-row justify-content-end">
        	<a class="btn btn-light" href="/Financy/controller?acao=AbrirFormCadastro&tipo=objetivoFinanceiro">Adicionar</a>
        </div>
        <div class="user-box d-flex flex-column align-items-center">
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>Descrição</th>
                    <th></th>
                    <th></th>
                </tr>
                                <c:forEach items="${objetivosFinanceiros}" var="o">
                	<tr>
                		<td>${o.nome}</td>
                		<td>R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${o.valor}" /></td>
                		<td>
                			<fmt:formatDate value="${o.dataConclusao}" pattern="dd/MM/yyy"/>
                		</td>
                		<td>${o.descricao}</td>
                		<td>
                			<c:url value="controller" var="link">
                				<c:param name="acao" value="AbrirFormAtualizar"></c:param>
                				<c:param name="tipo" value="objetivoFinanceiro"></c:param>
                				<c:param name="codigoObjetivoFinanceiro" value="${o.cdObjtvFinanceiro}"></c:param>
                			</c:url>
                			
                			<a class="btn btn-warning" href="${link}">Editar</a>
                		</td>
                		<td>
                			<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#excluirModal" onclick="codigoExcluir.value = ${o.cdObjtvFinanceiro}">
  								Excluir
							</button>
						</td>                	 
					</tr>
                	</c:forEach>
            </table>
            <!-- Modal -->
<div class="modal fade" id="excluirModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-danger">
        <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo excluir?</h5>
      </div>
      <div class="modal-body">
        <form action="controller" method="post">
        	<input type="hidden" name="acao" value="Remover">
        	<input type="hidden" name="tipo" value="objetivoFinanceiro">
        	<input type="hidden" name="codigoObjetivoFinanceiro" id="codigoExcluir">
        	<button type="submit" class="btn btn-danger">Excluir</button>
        	<button type="button" class="btn btn-warning" data-bs-dismiss="modal" aria-label="Close">Cancelar</button>        
        </form>
      </div>
    </div>
  </div>
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
      <a class="current-page" href="controller?acao=Listar&tipo=objetivoFinanceiro">Objetivos Financeiros</a>
      <a href="controller?acao=AbrirFormUsuario">Usuário</a>
      <a href="controller?acao=Logout">Logout</a>
    </div>
  </div>
  </body>
</html>