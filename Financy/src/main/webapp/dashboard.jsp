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

    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="./assets/css/dashboard.css" />

    <title>Dashboard</title>
  </head>
  <body class="d-flex flex-row justify-content-center">
    <div class="sidebar d-none d-lg-flex flex-column align-items-center">
      <h1 class="logo align-self-center">Financy</h1>
      <div class="links d-flex flex-column justify-content-around">
        <a class="current-page" href="controller?acao=AbrirDashboard">Dashboard</a>
        <a href="controller?acao=Listar&tipo=recebimento">Recebimentos</a>
        <a href="controller?acao=Listar&tipo=gasto">Gastos</a>
        <a href="controller?acao=Listar&tipo=investimento">Investimentos</a>
        <a href="controller?acao=Listar&tipo=objetivoFinanceiro">Objetivos Financeiros</a>
        <a href="controller?acao=AbrirFormUsuario">Usuário</a>
        <a href="controller?acao=Logout">Logout</a>
      </div>
    </div>
    <div class="main-box d-flex flex-column">
      <div class="navbar d-flex flex-row d-lg-none justify-content-between">
        <h1 class="nav-logo">Financy</h1>
        <button class="navbar-toggler hamburger" type="button" data-bs-toggle="collapse" data-bs-target="#sidebar" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </a>
      </div>
      <h2 class="username">Dashboard ${dashboard.nomeUser}</h2>
      <div class="card-container d-flex flex-wrap">
        <div class="card col-11 col-md-5">
          <small class="tag">Valor total recebimentos no mês</small>
          <h2 class="value">R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${dashboard.totalRecebMes}" /></h2>
        </div>
        <div class="card col-11 col-md-6">
          <small class="tag">Valor total gastos no mês</small>
          <h2 class="value">R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${dashboard.totalGastoMes}" /></h2>
        </div>
        <div class="card col-11 col-md-6">
          <small class="tag">Valor total investimentos</small>
          <h2 class="value">R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${dashboard.totalInvest}" /></h2>
        </div>
        <div class="card col-11 col-md-5">
          <small class="tag">Último gasto</small>
          <h2 class="info">Valor: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${dashboard.ultimoGasto.valor}" /></h2>
          <h2 class="info">Data: <fmt:formatDate value="${dashboard.ultimoGasto.data}" pattern="dd/MM/yyy"/></h2>
          <h2 class="info">${dashboard.ultimoGasto.descricao}</h2>
        </div>
        <div class="card col-11 col-md-4">
          <small class="tag">Saldo do mês</small>
          <h2 class="value">R$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${dashboard.saldoMes}" /></h2>
        </div>
        <div class="card col-11 col-md-7">
          <small class="tag">Último investimento</small>
          <h2 class="info-inv">Valor: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${dashboard.ultimoInvestimento.valor}" /></h2>
          <h2 class="info-inv">Data: <fmt:formatDate value="${dashboard.ultimoInvestimento.dataRealizacao}" pattern="dd/MM/yyy"/></h2>
          <h2 class="info-inv">Tipo: ${dashboard.ultimoInvestimento.tipoInvestimento}</h2>
          <h2 class="info-inv">Corretora: ${dashboard.ultimoInvestimento.bancoCorretora}</h2>
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
      <a class="current-page" href="controller?acao=AbrirDashboard">Dashboard</a>
      <a href="controller?acao=Listar&tipo=recebimento">Recebimentos</a>
      <a href="controller?acao=Listar&tipo=gasto">Gastos</a>
      <a href="controller?acao=Listar&tipo=investimento">Investimentos</a>
      <a href="controller?acao=Listar&tipo=objetivoFinanceiro">Objetivos Financeiros</a>
      <a href="controller?acao=AbrirFormUsuario">Usuário</a>
      <a href="controller?acao=Logout">Logout</a>
    </div>
  </div>

  </body>
</html>
