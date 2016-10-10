<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>test</h1>

<table class="table table-default tabla">
			<tr>
				<th>Codigo transacción</th>
				<th>Fecha transacción</th>
				<th>Fecha cierre</th>
				<th>No. cliente</th>
				<th>Razón social</th>
				<th>Nombre representante</th>
				<th>No. telefono</th>
				<th>Soeid</th>
				<th>Ejecutivo</th>
				<th>Sede</th>
			</tr>
			<c:forEach items="${agendas}" var="agenda">
			<tr>
				<td>${agenda.codigoTransaccion}</td>
				<td>${agenda.fechaTransaccion}</td>
				<td>${agenda.fechaCierre}</td>
				<td>${agenda.numeroCliente}</td>
				<td>${agenda.razonSocial}</td>
				<td>${agenda.nombreRepresentante}</td>
				<td>${agenda.numeroTelefono}</td>
				<td>${agenda.soeid}</td>
				<td>${agenda.ejecutivo}</td>
				<td>${agenda.sede}</td>
			</tr>
			</c:forEach>
		</table>
		<div>
			${error}
		</div>
</body>
</html>