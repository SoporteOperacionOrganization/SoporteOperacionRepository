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
<div class="container" style="width: 100% ; margin-top: 8em; ; z-index:1; position: absolute  ;">
		<div style="text-align: center; font-weight: bold;font-size: 1.4em" >
		GESTIONAR AGENDAS</div>
	

    <form class="navbar-form " role="search" action="filtrarAgendas" 
   style="background-color: white; text-align: left;  margin: 0% 0% 0% 9%;">
      <div  class="form-group">
        <input type="text" name="razonSocial" class="form-control" placeholder="Buscar por raz�n social">
      </div>      
     <button type="submit" class="btn btn-primary"><i style="height:19px;" class="glyphicon glyphicon-search"></i></button> 
    </form>
	<br />

	<div class="contenidoAgendas">
	
	<c:if test="${empty agendas}">
	<div class="alert alert-warning">
  <strong>No existe!</strong> La raz�n social que ingresaste no existe o ha sido eliminada.
</div>
	
	</c:if>
	
	
	<c:if test="${not empty agendas}">
		<table class="table table-striped table-hover tabla" style="font-size: 12px; width: 100%">
		<thead>
			<tr class="cabeceraTabla">
				<th>C�digo transacci�n</th>
				<th>Fecha transacci�n</th>
				<th>Fecha de cierre</th>
				<th>No. cliente</th>
				<th>Raz�n social</th>
				<th>Nombre representante</th>
				<th>No. tel�fono</th>
				<th>Soeid</th>
				<th>Ejecutivo</th>
				<th>Sede</th>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<th colspan="2">Acciones</th>
				</sec:authorize>
			</tr>
			</thead>
			<tbody>
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
			</tbody>
		</table>

		
		<c:choose>
			<c:when test="${filtro == true}">
				<div class="paginacion">
              		<div>
                    	<tag:paginate limite="5" offset="${offset}" conteo="${count}" razonSocial="${razonSocial}" uri="filtrarAgendas" siguiente="&raquo;" anterior="&laquo;" />
                    	<span class="label label-primary">Total: ${count}</span>
             		</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="paginacion">
              		<div>
                    	<tag:paginate limite="0" offset="${offset}" conteo="${count}" uri="listarAgendas" siguiente="&raquo;" anterior="&laquo;" />
                    	<span class="label label-primary">Total: ${count}</span>
             		</div>
				</div>
			</c:otherwise>
		</c:choose>
		
		<br>
		<div class="exportacionExcel">
			<a href="<c:url value='/exportarAgendas' />" type="submit" class="btn btn-success custom-width"><span class="glyphicon glyphicon-download-alt"></span>Exportar</a>
			<!-- <a href="<c:url value='/exportarAgendas' />" class="btn btn-success custom-width">Exportar</a> -->
		
		</div>
		</c:if>
		<br><br>
	</div>
	</div>
	
</body>
<footer style="position: fixed; bottom: 0; width: 100%">
<jsp:include page="../fragments/footer.jsp"  />	
	</footer>
</html>