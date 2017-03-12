<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage="gesError.jsp?pagOrigen=resultadoAlta.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultado Alta</title>
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
		<h4>Artista: <c:out value="${artista.getNombre()}"/>, <c:out value="${modelo}"/></h4>
		<form action="/ext2romangracia/controlador" method="post">
			<input type="hidden" name="accion" value="index">
			<input type="submit" value="Volver al indice">
		</form>
	</div>
</body>
</html>