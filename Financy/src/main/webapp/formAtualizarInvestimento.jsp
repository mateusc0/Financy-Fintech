<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="ISO-8859-1" />
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
    <link rel="stylesheet" href="./assets/css/table.css">

    <title>Atualizar Investimento</title>
  </head>
<body>
<div class="container">
	<h2 class="username">Atualizar Investimento</h2>
	<c:if test="${not empty error }">
		<div class="alert alert-danger">${error}</div>
	</c:if>
	<form action="controller" method="post">
		<input type="hidden" value="Atualizar" name="acao">
		<input type="hidden" value="investimento" name="tipo">
		<input type="hidden" value="${investimento.cdInvestimento}" name="codigoInvestimento">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nomeInvestimento" id="id-nome" class="form-control" value="${investimento.nome}">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valorInvestimento" id="id-valor" class="form-control" value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${investimento.valor}" />">
		</div>
		<div class="form-group">
			<label for="id-realização">Realização</label>
			<input type="text" name="dataRealizacao" id="id-realização" class="form-control" value="<fmt:formatDate value="${investimento.dataRealizacao}" pattern="dd/MM/yyyy"/>">
		</div>
		<div class="form-group">			
			<label for="id-vencimento">Vencimento</label>
			<input type="text" name="dataConclusao" id="id-vencimento" class="form-control" value="<fmt:formatDate value="${investimento.dataConclusao}" pattern="dd/MM/yyyy"/>">
		</div>
		<div class="form-group">			
			<label for="id-tipo">Tipo</label>
			<select  name="tipoInvestimento" id="id-tipo" class="form-control">
			<c:forEach items="${tipoInvestimento}" var="tipo">
			<c:if test="${tipo == investimento.tipoInvestimento}">
				<option class="text-dark" value="${tipo}" selected="selected">${tipo}</option>
			</c:if>
			<c:if test="${tipo != investimento.tipoInvestimento}">
				<option class="text-dark" value="${tipo}">${tipo}</option>
			</c:if>
   				
			</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="id-bancoCorretora">Corretora</label>
			<select name="bancoCorretora" id="id-bancoCorretora" class="form-control">
			<c:forEach items="${bancoCorretora}" var="bc">
   			<c:if test="${bc == investimento.bancoCorretora}">
				<option class="text-dark" value="${bc}" selected="selected">${bc}</option>
			</c:if>
			<c:if test="${bc != investimento.bancoCorretora}">
				<option class="text-dark" value="${bc}">${bc}</option>
			</c:if>
			</c:forEach>
			</select>
		</div>
		<div class="d-flex flex-row justify-content-between align-items-center my-2">
		<input type="submit" value="Salvar" class="btn btn-light">
		<a class="btn btn-warning" href="/Financy/controller?acao=Listar&tipo=investimento">Cancelar</a>
		</div>
		
	</form>
</div>
</body>
</html>