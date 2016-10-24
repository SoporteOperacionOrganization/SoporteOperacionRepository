<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="tag" uri="http://eficacia/paginacion.tld"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agendas</title>
<jsp:include page="../fragments/resources.jsp" />
<jsp:include page="../fragments/header.jsp"  />
</head>

<body>
<div class="container" style="width: 100% ; margin-top: 10em; ; z-index:1; position: absolute  ;">
		<div style="text-align: center; font-weight: bold;font-size: 20px" >
		GESTIONAR AGENDAS</div>
	

    <form class="navbar-form " role="search" action="filtrarAgendas" 
    style="background-color: white; text-align: right; margin: 0em 6em 0em 0em">
      <div style="margin-left:72px;" class="form-group">
        <input type="text" name="razonSocial" class="form-control" placeholder="Buscar por razón social">
      </div>      
     <button type="submit" class="btn btn-primary"><i style="height:19px;" class="glyphicon glyphicon-search"></i></button> 
    </form>
	<br />
	<div class="contenidoUsuarios">
	<c:if test="${not empty agendas}">
	
		<table class="table table-default table-striped tabla" style="font-size: 14px; width: 100%">
			<tr class="cabeceraTabla">
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
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<th colspan="2">Acciones</th>
				</sec:authorize>
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
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>
					<c:choose>
	                    <c:when test="${pageContext.request.userPrincipal.name == agenda.soeid}">
	                        <a href="<c:url value='/editarAgenda/${agenda.codigoTransaccion}'/>" 
	                        class="btn glyphicon glyphicon-edit disabled" style="font-size: 16px; text-align: center; color: gray;"></a>
	                    </c:when>
	                    <c:otherwise>
	                        <a href="<c:url value='/editarAgenda/${agenda.codigoTransaccion}'/>" 
	                        class="btn glyphicon glyphicon-edit" style="font-size: 16px ; color: black;text-align: center;"></a>
	                    </c:otherwise>
	                </c:choose>
				</td>
				<td><a href="<c:url value='/eliminarAgenda/${agenda.codigoTransaccion}' />" 
				class="btn glyphicon glyphicon-trash" style="font-size: 16px ; color: #941A26;text-align: center;"></a>
			</sec:authorize>
			</tr>		
			</c:forEach>
		</table>
		
		<div class="paginacion">
              <div >
                    <tag:paginate limite="5" offset="${offset}" conteo="${count}" uri="listarAgendas" siguiente="&raquo;" anterior="&laquo;" />
                    <span class="label label-primary">Total: ${count}</span>
             </div>
</div>
		
		
		
		<br>
		<div class="exportacionExcel">
			<a href="<c:url value='/exportarAgendas' />" type="submit" class="btn btn-success custom-width"><span class="glyphicon glyphicon-download-alt"></span>Exportar</a>
			<!-- <a href="<c:url value='/exportarAgendas' />" class="btn btn-success custom-width">Exportar</a> -->
		
		</div>
		</c:if>
	</div>
	

	</div>
	
</body>
<footer style="position: fixed; bottom: 0; width: 100%">
<jsp:include page="../fragments/footer.jsp"  />	
	</footer>
</html>