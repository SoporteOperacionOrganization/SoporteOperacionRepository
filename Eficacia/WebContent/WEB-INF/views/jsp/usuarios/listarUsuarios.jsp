<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tag" uri="http://eficacia/paginacion.tld"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios</title>
<jsp:include page="../fragments/resources.jsp" />
<jsp:include page="../fragments/header.jsp" />
</head>
<body>
	<div class="container"
		style="width: 100%; margin-top: 10em;; z-index: 1; position: absolute;">
		<div style="text-align: center; font-weight: bold; font-size: 20px">CONSULTAR
			USUARIOS</div>


		<form class="navbar-form "
			style="background-color: white; text-align: right; margin: 0em 11em 0em 0em"
			role="search" action="filtrarUsuarios">
			<div class="form-group">
				<input type="text" name="soeid" class="form-control"
					placeholder="Buscar por soeid">
			</div>
			<button type="submit" class="btn btn-primary">
				<i style="height: 19px;" class="glyphicon glyphicon-search"></i>
			</button>
		</form>
		<br />
		<div class="contenidoUsuarios">

			<c:if test="${not empty usuarios}">
				<table class="table table-default table-striped tabla"
					style="font-size: 14px">
					<tr class="cabeceraTabla">
						<th>#</th>
						<th>SOEID</th>
						<th>Nombre</th>
						<th>Apellido paterno</th>
						<th>Apellido Materno</th>
						<th>Teléfono</th>
						<th>Rol</th>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<th>Editar</th>
							<th>Eliminar</th>
						</sec:authorize>
					</tr>
					<c:forEach items="${usuarios}" var="usuario" varStatus="indice">
						<tr>
							<c:choose>
								<c:when
									test="${pageContext.request.userPrincipal.name == usuario.soeid}">

									<td style="color: #D9D1C7;">${indice.index + 1}</td>
									<td style="color: #D9D1C7;">${usuario.soeid}</td>
									<td style="color: #D9D1C7;">${usuario.nombre}</td>
									<td style="color: #D9D1C7;">${usuario.apellidoPaterno}</td>
									<td style="color: #D9D1C7;">${usuario.apellidoMaterno}</td>
									<td style="color: #D9D1C7;">${usuario.telefono}</td>
									<td style="color: #D9D1C7;">${usuario.rol.nombre}</td>

								</c:when>
								<c:otherwise>
									<td>${indice.index + 1}</td>
									<td>${usuario.soeid}</td>
									<td>${usuario.nombre}</td>
									<td>${usuario.apellidoPaterno}</td>
									<td>${usuario.apellidoMaterno}</td>
									<td>${usuario.telefono}</td>
									<td>${usuario.rol.nombre}</td>
								</c:otherwise>
							</c:choose>



							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<td><c:choose>
										<c:when
											test="${pageContext.request.userPrincipal.name == usuario.soeid}">
											<a href="<c:url value='/editarUsuario/${usuario.soeid}' />"
												class="btn glyphicon glyphicon-edit disabled"
												style="font-size: 16px; text-align: center; color: #D9D1C7;"></a>
										</c:when>
										<c:otherwise>
											<a href="<c:url value='/editarUsuario/${usuario.soeid}' />"
												class="btn glyphicon glyphicon-edit"
												style="font-size: 16px; color: black; text-align: center;"></a>
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when
											test="${pageContext.request.userPrincipal.name == usuario.soeid}">
											<a href="<c:url value='/eliminarUsuario/${usuario.soeid}' />"
												class="btn glyphicon glyphicon-trash disabled"
												style="font-size: 16px; text-align: center; color: #D9D1C7;"></a>
										</c:when>
										<c:otherwise>
											<a href="<c:url value='/eliminarUsuario/${usuario.soeid}' />"
												class="btn glyphicon glyphicon-trash"
												style="font-size: 16px; color: #941A26; text-align: center;"></a>
										</c:otherwise>
									</c:choose></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</table>
				<div class="paginacion">
					<div>
						<tag:paginate limite="5" offset="${offset}" conteo="${count}"
							uri="listarUsuarios" siguiente="&raquo;" anterior="&laquo;" />
						<span class="label label-primary">Total: ${count}</span>
					</div>
				</div>

			</c:if>
		</div>
	</div>


</body>
	<footer style="position: fixed; bottom: 0; width: 100%">
<jsp:include page="../fragments/footer.jsp"  />	
	</footer>
</html>