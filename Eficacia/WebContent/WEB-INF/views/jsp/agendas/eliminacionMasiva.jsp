<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminación Masiva</title>
<jsp:include page="../fragments/resources.jsp" />
<spring:url value="resources/js/bootstrap-filestyle.js" var="fileStyleJs" />
<spring:url value="resources/js/bootstrap-filestyle.min.js" var="fileStyleMinJs" />
<script src="${fileStyleJs}"></script>
<script src="${fileStyleMinJs}"></script>
</head>
<body>
<jsp:include page="../fragments/header.jsp" />
<div class="well lead tituloPagina">ELIMINACIÓN MASIVA</div>

<div class="contenidoUsuarios">
	<div class="generic-container">
		<form:form action="cargarExcelEliminacion" modelAttribute="file" method="POST" enctype="multipart/form-data"> 
			<form:input type="file" path="archivo" name="archivo" id="archivo" class="filestyle" data-buttonName="btn-primary" />
			<br/>
			<div class="has-error errores">
            	<form:errors path="archivo" class="help-inline"/>
            </div>
			<br/><br/>
			<input type="submit" class="btn btn-primary" value="Cargar archivo">
		</form:form>
		<div>
			<c:if test="${not empty estatus}">
	         <div style="color:red;margin:10px 0px;font-weight:bold;">
	                Error en Layout!!!<br />
	                ${estatus}<br/>
	                Verifica que el campo no este vacio y que tenga el formato adecuado.
	         </div>
    		</c:if>
    		<c:if test="${not empty procesoCorrecto}">
	         <div style="color:green;margin:10px 0px;font-weight:bold;">
	                ${procesoCorrecto}	
	                <!-- validacion de git -->            
	         </div>
    		</c:if>
		</div>
		
		<div>
			<c:if test="${not empty noEncontrados}">
	         	<div style="color:blue;margin:10px 0px;font-weight:bold;">
	                Los siguientes  Codigos de Transaccion, es probable que <br/>
	                los eliminaron anteriormente o nunca hayan existido <br/> 
	                <br/>
	                ${noEncontrados}
	                      
	         	</div>
    		</c:if>
		</div>
	
	</div>
</div>



<jsp:include page="../fragments/footer.jsp" />
</body>
</html>