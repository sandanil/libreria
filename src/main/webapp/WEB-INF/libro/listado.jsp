<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Libros</title>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${path}/static/js/autor.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
	
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	
</head>
<body style="background: url('http://www.extradigital.es/wp-content/uploads/2015/04/vladstudio_googlelibrary_1920x14401.jpg');">

<h1 style="color: yellow;">${titulo}</h1>

<input type="button"
value="Autor" name="autor" class="btn btn-primary" onclick="self.location.href = '/libreria/autor'" />
<input type="button"
value="Ejemplares" name="ejemplar" class="btn btn-primary" onclick="self.location.href = '/libreria/ejemplar'" />
<input type="button"
value="Tienda" name="tienda" class="btn btn-primary" onclick="self.location.href = '/libreria/tienda'" />

	<table
		class="table table-hover table-condensed table-striped table-bordered">
		<thead>
			<tr style="font-weight:bold;font-size: large;color: yellow;text-align: center; ">
				<td style="width: 10%">#</td>
				<td style="width: 30%">Nombre</td>
				<td style="width: 20%">Categoria</td>
				<td style="width: 20%">Autor</td>
				<td style="width: 10%">Editar</td>
				<td style="width: 10%">Borrar</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${libros}" var="libro">
				<tr style="background-color:black; font-weight:bold;font-size: large;color: white;text-align: center;" data-id="${libro.id}">
					<td>${libro.id}</td>
					<td><a href="<c:url value="/libro/detalles/${libro.id}" />">${libro.nombre}</a></td>
					<td>${libro.categoria}</td>
					<td>${libro.autor.nombre}</td>
					<td><button type="submit" class="btn btn-warning btn-editar">Editar</button></td>
					<td><button type="submit" class="btn btn-danger btn-confirmar-borrar">Borrar</button></td>
					
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">Libros Registrados: <span
					id="cantidades-libro">${libros.size()}</span></td>
			</tr>
			<tr>
				<td colspan="5">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#modal-libro">Registrar Libro</button>
						<form action="${path}/salir" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button id="btn-salir" type="submit" class="btn btn-danger">Salir</button>
                        </form>
				</td>
			</tr>
		</tfoot>
	</table>
	<div class="modal fade" id="modal-libro" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="form-libro" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Informacion del libro</h4>
					</div>
					<div class="modal-body">
							<label for="nombre">Nombre: </label>
							 <input id="nombre" name="nombre" class="form-control"/> 
							
							<label for="categoria">Categoria: </label> 
							<input id="categoria" name="categoria" class="form-control"/> 
						
							<label for="autor">Autor: </label> 
							<select id="autor" name="autor" class="form-control">
								
							<c:forEach items="${autor}" var="autor">
								<option value="${autor.id}">${autor.nombre}</option>
							</c:forEach>
							</select> 
						
						<input id="id" name="id" type="hidden" value=""/>
						
						<input id="csrf" name="_csrf" type="hidden" value="${_csrf.token}">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button id="btn-salvar" type="submit" class="btn btn-primary">Guardar
							Informacion</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-confirmar-borrar" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="form-confirmar-borrar" method="post">
					<div class="modal-header">
						
						<h4 class="modal-title">Borrando Libro</h4>
					</div>
					<div class="modal-body">
												
						<input id="id-borrar" name="id" type="hidden" value=""/>						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button id="btn-borrar" type="button" class="btn btn-primary">confirmar borrar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>