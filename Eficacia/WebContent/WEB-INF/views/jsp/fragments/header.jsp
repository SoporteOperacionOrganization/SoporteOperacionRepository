<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div style="background-image:url('resources/Images/Logo_CitiBanamex.png'); background-repeat:repeat-x; 
	top: 0%; ;	z-index:3 ; position: fixed; width: 100%; height: 100px">
		
		<table style="width: 100%; position: fixed; margin: 0%">
			<tr>
				<td  width="68%">
					<h3
						style="text-align: right; color: #D7D7D7">SISTEMA DE GESTION EFICASIA</h3>
				</td>
				<td style="text-align: right; width: 32%;"><br /> 
				
						<h4>
							<span style="color: #C01722; font-size: 16px; font-weight: bold; letter-spacing: 1px; margin-right:10px;'">
							<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">Bienvenido:</sec:authorize></span>
						</h4>
						<h5>
							<span style="color: #fff; text-align: left; font-size: 14px"> <c:if
									test="${UsuarioSesion != null}">
									<span style="margin-right:30px;" class="tranformarMayusculas"><sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">
									${UsuarioSesion.nombre} ${UsuarioSesion.apellidoPaterno}</sec:authorize> </span>
								</c:if>

							</span>
						</h5></td>
				<td ></td>

			</tr>
		</table>
		
		<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">
			<table style="width: 100%; margin-top: 5.1em; height: 2em;">
			<tr >
					<td colspan="4" style="font-size: 14px">
			<nav class="navbar-default " 
							style="margin: -0.2% 0% 0% 0px;width: 100%;
							background-color:#013F7A ;border-color: #013F7A;
							 border-radius: 0px 0px 0px 0px;">
							<div class="container" style="width: 100%; height: 3.4em" >
								<ul class="nav navbar-nav navbar-left" >
									<li style=" height: 3.4em;" class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false"  >Usuarios <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value='/listarUsuarios' />">Consultar</a></li>
											<li><a href="<c:url value='/agregarUsuario' />">Agregar</a></li>
										</ul>
										
										</li>
								</ul>
								<ul class="nav navbar-nav navbar-left">
									<li style=" height: 3.4em;" class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Agenda <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value='/listarAgendas' />">Consultar</a></li>
											<li><a href="<c:url value='/agregarAgenda' />">Agregar</a></li>
											<li role="separator" class="divider dividerColor"></li>
											<li><a class="subtitulosDropDown" style="color: #000; font-weight: bold; cursor: default;">Carga masiva</a></li>
											<li role="separator" class="divider"></li>
											<li><a href="<c:url value='/cargaMasiva' />">Agregar</a></li>
											<li><a href="<c:url value='/eliminacionMasiva' />">Eliminar</a></li>
										</ul></li>
								</ul>

								<ul  style="text-align: right; margin: 20px 0px 0px 0px">
									<a style="color: white; font-weight: bold;" href="<c:url value='/logout' />"><span class="glyphicon glyphicon-log-in"></span>&nbsp;Log out</a>
								</ul>

								
							</div>
						</nav>

					</td>
				
			</tr>
		</table>
		
		</sec:authorize>
		
		
	</div>
	<br>
</body>

</html>

