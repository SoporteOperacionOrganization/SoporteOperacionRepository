<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agendas</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
<jsp:include page="../fragments/header.jsp" />
	<div class="well lead tituloPagina" style="margin-bottom:0px;">GESTIONAR AGENDAS</div>
	<div class="cajaBusqueda">
    <form class="navbar-form navbar-left" role="search" action="filtrarAgendas">
      <div style="margin-left:72px;" class="form-group">
        <input type="text" name="razonSocial" class="form-control" placeholder="Buscar por razón social">
      </div>
      <!-- <a class="btn btn-primary custom-width" href=""><i style="height:19px;color:#FFFFFF;" class="glyphicon glyphicon-search"></i></a> -->
      <button type="submit" class="btn btn-primary"><i style="height:19px;" class="glyphicon glyphicon-search"></i></button> 

    </form>
	
    </div>
	<br /><br />
	<div class="contenidoAgendas">
		<table class="table table-default table-striped tabla">
			<tr class="cabeceraTabla" style="vertical-align:middle;">
				<th>#</th>
				<th>Código transacción</th>
				<th>Fecha transacción</th>
				<th>Fecha de cierre</th>
				<th>No. cliente</th>
				<th>Razón social</th>
				<th>Nombre representante</th>
				<th>No. teléfono</th>
				<th>Soeid</th>
				<th>Ejecutivo</th>
				<th>Sede</th>
				<th colspan="2">Acciones</th>
			</tr>
			<c:forEach items="${agendas}" var="agenda" varStatus="indice">
			<tr>
				<td class="negrita">${indice.index + 1}</td>
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
				<td>
					<c:choose>
	                    <c:when test="${pageContext.request.userPrincipal.name == agenda.soeid}">
	                        <a href="<c:url value='/editarAgenda/${agenda.codigoTransaccion}'/>" class="btn btn-primary btn-sm disabled">Modificar</a>
	                    </c:when>
	                    <c:otherwise>
	                        <a href="<c:url value='/editarAgenda/${agenda.codigoTransaccion}'/>" class="btn btn-primary btn-sm">Modificar</a>
	                    </c:otherwise>
	                </c:choose>
				</td>
				<td><a href="<c:url value='/eliminarAgenda/${agenda.codigoTransaccion}' />" class="btn btn-danger btn-sm">Eliminar</a></td>
			</tr>
			</c:forEach>
		</table>
		<div class="exportacionExcel">
		
			<a href="<c:url value='/exportarAgendas' />" type="submit" class="btn btn-success custom-width"><span class="glyphicon glyphicon-download-alt"></span>Exportar</a>
			<!-- <a href="<c:url value='/exportarAgendas' />" class="btn btn-success custom-width">Exportar</a> -->
		
		</div>
	</div>
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>