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
    
    <script 
    	defer 
    	type="text/javascript" 
    	src="./js/jquery-3.6.1.js"
    ></script>
	<script 
		defer 
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js"
	></script>

    <link rel="stylesheet" href="./assets/css/style.css" />
    <link rel="stylesheet" href="./assets/css/log-nv-bt.css" />
    <link rel="stylesheet" href="./assets/css/navbar.css" />
    <link rel="stylesheet" href="./assets/css/svg.css" />
    <link rel="stylesheet" href="./assets/css/sidebar.css" />
    <link rel="stylesheet" href="./assets/css/forms-contato.css" />

    <title>Financy</title>
  </head>
  <body>
    <div class="container-fluid text-white home">
      <div id="header" class="row header fixed-top pt-1 pb-1">
        <h1 id="home" class="col-lg-2 d-none d-sm-block text-center logo">
          Financy
        </h1>
        <div class="col-lg-9 navbar text-center">
          <div class="col-3"><a href="#">HOME</a></div>
          <div class="col-3"><a href="#objetivos">OBJETIVOS</a></div>
          <div class="col-3"><a href="#contato">CONTATO</a></div>
          <div class="col-3 d-flex justify-content-center">
            <button
              type="button"
              class="btn btn-outline login bt"
              data-bs-toggle="offcanvas"
              data-bs-target="#sidebar"
              aria-control="tela de login"
            >
              Login
            </button>
          </div>
        </div>
      </div>
      <div class="row text-center main-bg">
        <h2 class="col-12 display-1">Financy</h2>
        <p class="col-12 display-4 slogan">
          Uma nova maneira de organizar suas finanças
        </p>
      </div>

      <div class="container-fluid objetivos">
        <div class="d-flex flex-row dark-color-text">
          <h2 id="objetivos" class="col-12 text-center display-4 section-name">
            Objetivos
          </h2>
        </div>
        <div class="d-flex flex-wrap dark-color-text">
          <p
            id="txt1"
            class="col-12 col-md-6 align-self-center text-center text-margin h2 align-left slide-in"
          >
            Ajudar você a alcançar seus objetivos financeiros
          </p>
          <div class="col-12 col-md-6 text-center">
            <object
              id="svg1"
              class="svg-fluid align-right slide-in"
              type="image/svg+xml"
              data="./assets/svgs/goals.svg"
            ></object>
          </div>
        </div>
        <div class="d-flex flex-wrap-reverse dark-color-text">
          <div class="col-12 col-md-6 text-center">
            <object
              id="svg2"
              class="svg-fluid align-left slide-in"
              type="image/svg+xml"
              data="./assets/svgs/crisis.svg"
            ></object>
          </div>
          <p
            id="txt2"
            class="col-12 col-md-6 align-self-center text-center text-margin h2 align-right slide-in"
          >
            Ajudar você a controlar sua vida financeira
          </p>
        </div>
        <div class="d-flex flex-wrap dark-color-text">
          <p
            id="txt3"
            class="col-12 col-md-6 align-self-center text-center text-margin h2 align-left slide-in"
          >
            Ajudar você a perder menos tempo com finanças e ter mais tempo livre
          </p>
          <div class="col-12 col-md-6 text-center">
            <object
              id="svg3"
              class="svg-fluid align-right slide-in"
              type="image/svg+xml"
              data="./assets/svgs/relaxing.svg"
            ></object>
          </div>
        </div>
      </div>
      <form class="container contact-box">
        <div class="row form-text">
          <h2 id="contato" class="col-12 text-center display-4 section-name">
            Contato
          </h2>
        </div>
        <div class="row form-text">
          <div class="col-md-6">
            <label class="form-label contact-label h3" for="nome">Nome</label>
            <input
              type="text"
              class="form-control ct-input-box"
              id="nome"
              placeholder="Seu nome"
              required
            />
          </div>
          <div class="col-md-6">
            <label class="form-label contact-label h3" for="sobrenome"
              >Sobrenome</label
            >
            <input
              type="text"
              class="form-control ct-input-box"
              id="sobrenome"
              placeholder="Seu sobrenome"
              required
            />
          </div>
          <div class="col-md-8">
            <label class="form-label contact-label h3" for="email">Email</label>
            <input
              type="email"
              class="form-control ct-input-box"
              id="email"
              placeholder="Seu email"
              required
            />
          </div>
          <div class="col-md-4">
            <label class="form-label contact-label h3" for="phone"
              >Telefone</label
            >
            <input
              type="tel"
              class="form-control ct-input-box"
              id="phone"
              placeholder="+XX (XX)XXXXX-XXXX"
            />
          </div>
          <div class="col-md-12">
            <label class="form-label contact-label h3" for="menssage"
              >Sua mensagem</label
            >
            <textarea
              class="form-control ct-input-box"
              id="menssage"
              rows="8"
              placeholder="Sua mensagem"
              required
            ></textarea>
          </div>
        </div>
        <div class="d-flex flex-row justify-content-center">
          <div class="d-flex flex-row justify-content-center">
            <button type="submit" class="btn send-button">
              <p class="h5 bt-text">Enviar</p>
            </button>
          </div>
        </div>
      </form>

      <div
        class="offcanvas offcanvas-start container-fluid sidebar"
        tabindex="-1"
        id="sidebar"
        aria-labelledby="tela de login"
      >
      <c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	  </c:if>
	  <c:if test="${not empty error }">
		<div class="alert alert-danger">${error}</div>
	  </c:if>
        <div class="d-flex flex-row justify-content-end">
          <button
            type="button"
            class="btn-close btn-close-white text-reset sidebar-close"
            data-bs-dismiss="offcanvas"
          ></button>
        </div>
        <div class="d-flex flex-row justify-content-center sidebar-header">
          <div class="col-auto sidebar-title display-4">Financy</div>
        </div>
        <div class="d-flex flex-row justify-content-center">
          <div class="col-10 col-sm-8">
            <form action="controller" method="post">
            <input type="hidden" name="acao" value="Login">
              <div class="form-group">
                <label class="sidebar-label h4" for="InputEmail">Email</label>
                <input
                  type="email"
                  class="form-control"
                  id="InputEmail"
                  name="email"
                  placeholder="Enter email"
                  required
                />
              </div>
              <div class="form-group">
                <label class="sidebar-label h4" for="inputPassword"
                  >Senha</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword"
                  name="senha"
                  placeholder="Password"
                  required
                />
                <small id="password-reset" class="form-text"
                  ><a class="disabled" href="#" target="_blank"
                    >Esqueceu sua senha?</a
                  ></small
                >
              </div>
              <div
                class="d-flex flex-row justify-content-center align-items-center"
              >
                <button type="submit" class="btn log-sd-bt">
					<span class="material-icons px-2 pt-1"> arrow_forward </span>
                </button>
              </div>
            </form>
          </div>
        </div>
        <div class="d-flex flex-row justify-content-center align-items-center">
          <div class="col-md-8 text-center ct-input-box sign-up-link">
            <small
              ><a href="controller?acao=AbrirFormCadastroUsuario" target="_self"
                >Não é usuário cadastrado?</a
              ></small
            >
          </div>
        </div>
      </div>
    </div>

    <script src="./js/script.js"></script>
    <!-- Option 1: Bootstrap Bundle with Popper -->
  </body>
</html>
