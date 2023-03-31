<%@ page import="java.util.List , br.com.fintech.entity.Gasto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${not empty gastos}">
     Chegou
	</c:if>
	<c:if test="${empty gastos}">
     Não Chegou
	</c:if>
	
	
	<c:forEach var="gasto" items="${gastos}">
		<ul>${gasto}</ul>
	</c:forEach>
</body>
</html>