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

    <title>Cadastrar Objetivo Financeiro</title>
  </head>
<body>
<div class="container">
	<div class="d-flex flex-row justify-content-end align-items-center my-3">
	<a class="btn btn-warning" href="/Financy/controller?acao=Listar&tipo=objetivoFinanceiro">Voltar</a>
	</div>
	<h2 class="username">Novo Objetivo Financeiro</h2>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty error }">
		<div class="alert alert-danger">${error}</div>
	</c:if>
	<form action="controller" method="post">
		<input type="hidden" value="Adicionar" name="acao">
		<input type="hidden" value="objetivoFinanceiro" name="tipo">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nomeObjetivoFinanceiro" id="id-nome" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valorObjetivoFinanceiro" id="id-valor" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-data">Data</label>
			<input type="date" name="dataObjetivoFinanceiro" id="id-data" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-descricao">Descri��o</label>
			<input type="text" name="descricaoObjetivoFinanceiro" id="id-descricao" class="form-control">
		</div>
		<div class="d-flex flex-row justify-content-start align-items-center my-2">
		<input type="submit" value="Salvar" class="btn btn-light">
		</div>
	</form>
</div>
</body>
</html>