<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tag" uri="http://eficacia/paginacion.tld"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contraseña Expirada</title>
<jsp:include page="../fragments/resources.jsp" />
<jsp:include page="../fragments/header.jsp" />
</head>
<body>
	<div class="container"
		style="width: 100%; margin-top: 6em; z-index: 1; position: absolute;">

		<form action="contrasenaExpirada">
			<div class="contenidoUsuarios">
				<div style="margin-top: 50px;" class="container bootstrap snippet">
					<div>
						<div class="col-xs-12 col-sm-12 col-md-6 col-md-offset-2">
							<div style="border-color: #003478;" class="panel panel-info">
								<div
									style="background: #003478; color: #FFFFFF; font-weight: bold;"
									class="panel-heading">
									<h3 class="panel-title">
										<span style="font-weight: bold;"
											class="glyphicon glyphicon-th"></span> Cambiar contraseña
									</h3>
								</div>
								<div style="width: 33%; margin-left: 35%;">
									<div class="bootstrap snippet">
										<div>
											<div>
												<div style="border-color: #00548B; border-width: 0.2em;"
													class="panel panel-info">


													<div class="panel-body" style="text-align: left;">
														<div style="text-align: center;">
															<h2 class="text-center">Tu contraseña expiró</h2>
															<i style="font-size: 50px; color: black;"
																class="glyphicon glyphicon-lock"></i> <br>
															<p style="color: #0F1A25;">Puedes cambiar tu
																contraseña aquí.</p>
														</div>
														<br />
														<br />
														<div class="row">
															<div class="form-group  col-md-12">
																<label style="width: 11em"
																	class="col-md-3 control-lable" for="passwordActual">Contraseña
																	actual</label>
																<div class="input-group">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-lock"></span>
																	</div>
																	<input class="form-control" name="passwordActual"
																		id="passwordActual" type="password"
																		placeholder="Contraseña actual" value="${passActual}">
																	<input style="width: 12em" class="form-control"
																		name="passwordActual" id="passwordActual"
																		type="password" placeholder="Contraseña Actual"
																		value="${passActual}">
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group col-md-12">
																<label style="width: 11em"
																	class="col-md-3 control-lable" for="passwordNuevo">Contraseña
																	nueva</label>
																<div class="input-group">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-log-in"></span>
																	</div>
																	<input class="form-control" name="passwordNuevo"
																		id="passwordNuevo" type="password"
																		placeholder="Contraseña nueva" value="${passNuevo}"
																		data-toggle="tooltip"
																		title="Debe contener al menos una letra mayúscula, minúsculas, al menos un número, al menos un caracter especial y no debe contener espacios en blanco">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-log-in"></span>
																	</div>
																	<input style="width: 12em" class="form-control"
																		name="passwordNuevo" id="passwordNuevo"
																		type="password" placeholder="Contraseña Nueva"
																		value="${passNuevo}" data-toggle="tooltip"
																		title="Debe contener al menos una letra mayúscula, minúsculas, al menos un número, al menos un caracter especial y no debe contener espacios en blanco">
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group col-md-12">
																<label style="width: 11em"
																	class="col-md-3 control-lable"
																	for="confirmacionPasswordNuevo">Confirmar
																	contraseña</label>
																<div class="input-group">
																	<div class="input-group-addon">
																		<span class="glyphicon glyphicon-log-in"></span>
																	</div>
																	<input style="width: 12em" class="form-control"
																		name="confirmacionPasswordNuevo"
																		id="confirmacionPasswordNuevo" type="password"
																		placeholder="Confirmar contraseña"
																		value="${confirmacionPasswordNuevo}">
																</div>
															</div>
														</div>

														<div class="row">
															<div class="form-group col-md-12">
																<c:if test="${not empty error}">
																	<div
																		style="text-align: center; color: red; margin: 10px 0px; font-weight: bold;">
																		Error<br /> ${error}<br />
																	</div>
																</c:if>
															</div>
														</div>

													</div>
													<div style="text-align: center;">
														<button type="submit" class="btn btn-primary"
															style="background-color: #013F7A;">
															<i style="height: 19px;"
																class="glyphicon glyphicon-check"></i>&nbsp; Guardar
														</button>
														&nbsp;&nbsp; <a href="<c:url value='/logout' />"
															class="btn btn-primary custom-width"
															style="background-color: #941A26; border-color: #941A26">
															Cancelar</a>
													</div>
													<br>
												</div>
											</div>
										</div>
									</div>
								</div>
		</form>

		<script>
			$(document).ready(function() {
				$('[data-toggle="tooltip"]').tooltip();

			});
		</script>
	</div>
</body>
<footer style="position: fixed; bottom: 0; width: 100%">
	<jsp:include page="../fragments/footer.jsp" />
</footer>
</html>