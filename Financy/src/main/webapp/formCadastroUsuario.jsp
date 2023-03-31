<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />

    <!-- Bootstrap CSS -->
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

    <link rel="stylesheet" href="./assets/css/sign-up.css" />

    <title>Cadastro</title>
  </head>
  <body>
    <c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty error }">
		<div class="alert alert-danger">${error}</div>
	</c:if>
    <div class="container-fluid under-image">
      <div class="d-flex flex-row justify-content-center sign-up-header">
        <h1 class="col-auto sign-up-title display-1">Cadastro</h1>
      </div>
      <form class="d-flex flex-column align-items-center sign-up-form" action="controller" method="post">
      <input type="hidden" value="CadastrarUsuario" name="acao">
        <div class="form-group">
          <label class="sign-up-label" for="fullName"
            ><h2 class="h4">Nome Completo</h2></label
          >
          <input
            type="text"
            class="form-control"
            id="fullName"
            name="nomeUsuario"
            placeholder="Seu nome completo"
            required
          />
        </div>
        <div class="form-group">
          <label class="sign-up-label" for="email"
            ><h2 class="h4">Email</h2></label
          >
          <input
            type="email"
            class="form-control"
            id="email"
            name="emailUsuario"
            placeholder="Seu enderço de email"
            required
          />
        </div>
        <div class="form-group">
          <label class="sign-up-label" for="birthday"
            ><h2 class="h4">Data de Nascimento</h2></label
          >
          <input type="date" class="form-control" id="birthday" name="dataNascimento" required />
        </div>
        <div class="form-group">
          <label class="sign-up-label" for="id-sexo"
            ><h2 class="h4">Sexo</h2></label
          >
          <select  name="sexoUsuario" id="id-sexo" class="form-control" required="required">
				<option class="text-dark" value="0">Selecione</option>
					<c:forEach items="${sexo}" var="sx">
   						<option class="text-dark" value="${sx}">${sx}</option>
					</c:forEach>
			</select>
        </div>
        <div class="form-group">
          <label class="sign-up-label" for="password"
            ><h2 class="h4">Senha</h2></label
          >
          <input
            type="password"
            class="form-control"
            id="password"
            name="senhaUsuario"
            placeholder="Sua senha"
            required
          />
        </div>
        <button type="submit" class="btn log-sg-bt">
          <span class="material-icons px-2 pt-1 text-secondary"> arrow_forward </span>
        </button>
      </form>
    </div>
    <!-- Option 1: Bootstrap Bundle with Popper -->
  </body>
</html>