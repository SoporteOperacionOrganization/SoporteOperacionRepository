<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carga masiva</title>
<jsp:include page="../fragments/resources.jsp" />
<spring:url value="resources/js/bootstrap-filestyle.js" var="fileStyleJs" />
<spring:url value="resources/js/bootstrap-filestyle.min.js" var="fileStyleMinJs" />
<script src="${fileStyleJs}"></script>
<script src="${fileStyleMinJs}"></script>
<jsp:include page="../fragments/header.jsp" />
</head>
<body>

<div class="container"
		style="width: 100%; margin-top: 10em; z-index: 1; position: absolute;">
		<div style="text-align: center; font-weight: bold;font-size: 20px">CARGA MASIVA</div>
<br>

<div class="contenidoUsuarios">
	<div class="generic-container">
		<form:form action="cargarExcel" modelAttribute="file" method="POST" enctype="multipart/form-data"> 
			<form:input type="file" path="archivo" name="archivo" id="archivo" class="filestyle" data-buttonName="btn-primary" />
			<br/>
			<div class="has-error errores">
            	<form:errors path="archivo" class="help-inline"/>
            </div>
			<br/><br/>
			<input type="submit" class="btn btn-primary" value="Cargar archivo">
		</form:form>
		
		
		 <form:form modelAttribute="file" method="GET" enctype="multipart/form-data"> 
             <div>
                    <a href="<c:url value='/descargarArchivo/1' />">Descargar Layout</a>
             </div>
             </form:form>
		
		
		
		<div>
			<c:if test="${not empty estatus}">
	         <div style="color:red;margin:10px 0px;font-weight:bold;">
	                Error en Layout!!!<br />
	                ${estatus}<br/>
	                Verifica que el campo no este vacio y que tenga el formato adecuado.
	         </div>
    		</c:if>
    		<c:if test="${not empty extensionError}">
	         <div style="color:red;margin:10px 0px;font-weight:bold;">
	                Error en tipo de archivo!!!<br />
	                ${extensionError}<br/>	            
	         </div>
    		</c:if>
    		<c:if test="${not empty procesoCorrecto}">
	         <div style="color:green;margin:10px 0px;font-weight:bold;">
	                ${procesoCorrecto}	            
	         </div>
    		</c:if>
		</div>
		
	</div>
</div>

</div>
</body>
<footer style="position: fixed; bottom: 0; width: 100%">
<jsp:include page="../fragments/footer.jsp"  />	
	</footer>
</html>