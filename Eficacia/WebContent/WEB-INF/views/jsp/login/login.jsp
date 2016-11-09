<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema de usuarios</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
	<jsp:include page="../fragments/header.jsp" />
	<div class="container  "
		style="position: fixed; height: 100%; width: 100%">

		<form id="loginform" role="form"
			action="${pageContext.request.contextPath}/j_spring_security_check"
			method="POST">
			<div style="margin-top: 10px; text-align: center;" class="form-group">
				<!-- Button -->

				<div class="col-sm-12 controls" style="margin-left: 0%">
					<input type="submit" class="btn btn-primary"
						style="width: 60px; height: 27px; font-size: 12px;"
						value="Validar" />
					<div class="logueo">
						<div
							style="border-bottom: 1px solid #0099FF; margin-bottom: 3.5%; text-align: left;">
							User <span style="color: #0099FF">Login</span>
						</div>

						<div style="display: none" id="login-alert"
							class="alert alert-danger col-sm-12"></div>
						<div style="margin-bottom: 5px; width: 100%; height: 3px"
							class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" name="soeid" value=""
								placeholder="Soeid" required="required">
						</div>
						<div style="margin-bottom: 5px; width: 100%" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="contrasena"
								placeholder="Contraseña" required="required">
						</div>

						<div></div>
						<br>
						<div style="margin-top: 10px; text-align: center;"
							class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls" style="margin-left: 0%">
								<input type="submit" class="btn btn-primary"
									style="width: 60px; height: 27px; font-size: 12px;"
									value="Validar" />
							</div>
						</div>
						<br> <br> <br>
					</div>
					<c:if test="${param.error == 'true'}">
						<div class="alert alert-danger" style="text-align: c">
							<strong>Autenticación incorrecta!</strong> Usuario o contraseña
							incorrectos.
						</div>
					</c:if>
				</div>
			</div>
		</form>

	</div>

</body>
<footer style="position: fixed; bottom: 0; width: 100%">
	<jsp:include page="../fragments/footer.jsp" />
</footer>
</html>