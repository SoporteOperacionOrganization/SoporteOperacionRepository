<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema de Usuarios</title>
<jsp:include page="fragments/resources.jsp" />
</head>
<body>
	<jsp:include page="fragments/header.jsp" />
	<div class="container" style="position:fixed; margin-top: 10em; width: 100% ; text-align: center;" >
		<h1>ACCESO DENEGADO</h1>
			<img style="width: 8%; height: 18%" class="inicioImages" src="resources/Images/Logos/seguridad.png">
			<h5>No cuentas con los permisos necesrios para visualizar esta pagina</h5>
	</div>

</body>
<footer style="position: fixed; bottom: 0; width: 100%">
	<jsp:include page="fragments/footer.jsp" />
</footer>
</html>