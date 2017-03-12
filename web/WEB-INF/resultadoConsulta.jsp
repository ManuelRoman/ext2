<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="gesError.jsp?pagOrigen=resultadoConsulta.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultado consulta</title>
<style>
	div{
	position: absolute;
	left: 30%;
	width: 30%;
	}
</style>
</head>
<body>
	<div>
	<h4>Resultado de la consulta</h4>
	<c:set var="lista" value="${modelo}"/>
	<table>
	<thead>
  		<tr>
    		<th>DNI</th>
    		<th>Nombre</th>
    		<th>Apellidos</th>
    		<th>Edad</th>
    		<th>Actuacion</th>
  		</tr>
  	</thead>
  	<tbody>
        <c:forEach var="artista" items="${lista}">
  		<tr>
    		<td><c:out value="${artista.getDni()}"/></td>
    		<td><c:out value="${artista.getNombre()}"/></td>
    		<td><c:out value="${artista.getApellidos()}"/></td>
    		<td><c:out value="${artista.getEdad()}"/></td>
    		<td><c:out value="${artista.getActuacion()}"/></td>
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
	</div>
</body>
</html>