<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Permisos insuficientes</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
<div style="text-align:center;vertical-align:middle;padding-top:100px;">
	<!-- <i style="font-size:200px;color:#FF8000;" class="glyphicon glyphicon-lock"></i>-->
	<img src="resources/Images/Logos/accesoDenegado.jpg" alt="">
	<br/><br/>
	<h1>No cuentas con los <span style="color:#FF9900;font-weight:bold;">permisos</span> necesarios para visualizar esta página</h1>
	<span style="color:#FF9900;font-weight:bold;font-size:28px;">Acceso denegado</span><br/>
	<ul class="pager">
    	<li><a style="color:#FF9900;font-weight:bold;" href="${pageContext.request.contextPath}/inicio">&larr; Inicio</a></li>
    </ul>
    <!-- FF8000 -->
</div>
</body>
</html>