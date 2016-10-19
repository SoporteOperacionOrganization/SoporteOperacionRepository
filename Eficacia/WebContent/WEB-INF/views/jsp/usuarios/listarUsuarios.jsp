<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://eficacia/paginacion.tld"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
<jsp:include page="../fragments/header.jsp" />
	<div class="well lead tituloPagina" style="margin-bottom:0px;">GESTIONAR USUARIOS</div>
	<div class="cajaBusqueda">
	    <form class="navbar-form navbar-left" role="search" action="filtrarUsuarios">
	      <div style="margin-left:72px;" class="form-group">
	        <input type="text" name="soeid" class="form-control" placeholder="Buscar por soeid">
	      </div>
	      <button type="submit" class="btn btn-primary"><i style="height:19px;" class="glyphicon glyphicon-search"></i></button> 
	    </form>
    </div>
	<br /><br />
	<div class="contenidoUsuarios" style="margin-bottom:20px;">
		<table class="table table-default table-striped tabla">
			<tr class="cabeceraTabla">
				<th>#</th>
				<th>SOEID</th>
				<th>Nombre</th>
				<th>Apellido paterno</th>
				<th>Apellido Materno</th>
				<th>Teléfono</th>
				<th>Rol</th>
				<th>Modificar</th>
				<th>Eliminar</th>
			</tr>
			<c:forEach items="${usuarios}" var="usuario" varStatus="indice">
			<tr>
				<td class="negrita">${indice.index + 1}</td>
				<td>${usuario.soeid}</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.apellidoPaterno}</td>
				<td>${usuario.apellidoMaterno}</td>
				<td>${usuario.telefono}</td>
				<td>${usuario.rol.nombre}</td>
				<td>
					<c:choose>
	                    <c:when test="${pageContext.request.userPrincipal.name == usuario.soeid}">
	                        <a href="<c:url value='/editarUsuario/${usuario.soeid}' />" class="btn btn-primary btn-sm disabled">Modificar</a>
	                    </c:when>
	                    <c:otherwise>
	                        <a href="<c:url value='/editarUsuario/${usuario.soeid}' />" class="btn btn-primary btn-sm">Modificar</a>
	                    </c:otherwise>
	                </c:choose>
	            </td>
	            ${usuario.soeid}
				<td>
					<c:choose>
	                    <c:when test="${pageContext.request.userPrincipal.name == usuario.soeid}">
	                        <a href="<c:url value='/eliminarUsuario/${usuario.soeid}' />" class="btn btn-danger btn-sm disabled">Eliminar</a>
	                    </c:when>
	                    <c:otherwise>
	                        <a href="<c:url value='/eliminarUsuario/${usuario.soeid}' />" class="btn btn-danger btn-sm">Eliminar</a>
	                    </c:otherwise>
	                </c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<div class="paginacion">
			<div >
				<tag:paginate limite="5" offset="${offset}" conteo="${count}" uri="listarUsuarios" siguiente="&raquo;" anterior="&laquo;" />
				<span class="label label-primary">Total: ${count}</span>
			</div>
		</div>
		
	</div>
	
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>