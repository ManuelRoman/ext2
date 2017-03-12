<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="errores.jsp?pagOrigen=resultadoConsultaCategoria.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultado Consulta por Categoria</title>
</head>
<body>
	<c:set var="lista" value="${modelo}"/>
	<h3>Resultado Consulta por Categoria</h3>
	<table>
	<thead>
  		<tr>
    		<th>DNI</th>
    		<th>Nombre</th>
    		<th>Apellidos</th>
    		<th>Edad</th>
    		<th>Actuacion</th>
    		<th>Categoria</th>
  		</tr>
  	</thead>
  	<tbody>
        <c:forEach var="artista" items="${lista}">
  		<tr>
    		<td><c:out value="${artista[0]}"/></td>
    		<td><c:out value="${artista[1]}"/></td>
    		<td><c:out value="${artista[2]}"/></td>
    		<td><c:out value="${artista[3]}"/></td>
    		<td><c:out value="${artista[4]}"/></td>
    		<td><c:out value="${artista[5]}"/></td>
    	</tr>
    	</c:forEach>
    <tbody>
	</table>
	<c:if test="${not empty listaVacia}">
   		<p>No hay artistas<p>
	</c:if>
	<form action="/ext2romangracia/controlador" method="post">
		<input type="hidden" name="accion" value="index">
		<input type="submit" value="Volver al indice">
	</form>
</body>
</html>