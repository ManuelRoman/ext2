<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="WEB-INF/gesError.jsp?pagOrigen=index.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<script type="text/javascript" src="js/jquery320.js"></script>
<script type="text/javascript" src="js/funciones.js"></script>
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
	<p>Seleccione una opción:</p>
	<fieldset>
	<p>Añadir un artista</p>
	<form action="/ext2romangracia/controlador" method="post">
	<label>Tipo de artista</label><br>
		<input type="radio" name="tipo" value="cantante" required="required"> Cantante<br>
  		<input type="radio" name="tipo" value="acrobata"> Acróbata<br>
  		<input type="radio" name="tipo" value="musico"> Musico<br>
		<label for="dni">DNI:</label><input id="dni" type="text" name="dni" maxlength="9" pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]{1}))" placeholder="Ej: 12345678A" required="required" ><br>
		<label for="nombre">Nombre:</label><input id="nombre" type="text" name="nombre" maxlength="30" placeholder="Máximo 30" required="required"><br>
		<label for="apellidos">Apellidos:</label><input id="apellidos" type="text" name="apellidos" maxlength="40" placeholder="Máximo 40" required="required"><br>
		<label for="edad">edad:</label><input id="edad" type="number" name="edad" min="1" max="100" step="1" placeholder="Máximo 100" required="required" autocomplete="off" pattern="\S*"><br>
		<label for="actuacion">Actuacion:</label><input id="actuacion" type="text" name="actuacion" maxlength="100" placeholder="Máximo 100" required="required"><br>
		<input type="hidden" name="accion" value="altaArtista">
		<input type="submit" value="Alta">
	</form>
	</fieldset>
	<fieldset>	
	<form action="/ext2romangracia/controlador" method="post">
		<label>Buscar por tipo de artista</label><br>
		<input type="radio" name="tipo" value="cantante" required="required"> Cantante<br>
  		<input type="radio" name="tipo" value="acrobata"> Acróbata<br>
  		<input type="radio" name="tipo" value="musico"> Musico<br>
		<input type="hidden" name="accion" value="consultaCategoria">
		<input type="submit" value="Consultar artistas por categoría">
	</form>
	<hr>
	<form action="/ext2romangracia/controlador" method="post">
		<label for="buscaDNI">Buscar por DNI:<input list="dnis" name="dni" id="buscaDNI" required="required">
  		<datalist id="dnis">	
  		</datalist>
		<input type="hidden" name="accion" value="consultadni">
		<input type="submit" value="Consultar artistas por dni">
	</form>
	<hr>
	<p>Listar todos los artistas</p>
	<form action="/ext2romangracia/controlador" method="post">
		<input type="hidden" name="accion" value="consultaTotal">
		<input type="submit" value="Consultar todos los artistas">
	</form>
	</fieldset>
	</div>
</body>
</html>