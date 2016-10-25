<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-glyphicons.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/customStyle.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/datepicker.css" rel="stylesheet" ></link>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script>
$( document ).ready(function() {
    $('.datepicker').datepicker({
    	format: 'dd/mm/yyyy',
        startDate: '-3d'
    });
});
</script>
</head>
<body>
<!-- INICIO CABECERA -->
<div style="background-image:url('${pageContext.request.contextPath}/resources/Images/Logos/Logo_CitiBanamex.png'); background-repeat:repeat-x; 
	top: 0%; ;	z-index:3 ; position: fixed; width: 100%; height: 100px">
	
		<table style="width: 100%; position: fixed;">
			<tr>
				<td  width="68%">
					<h3
						style="text-align: right; color: #D7D7D7">SISTEMA DE GESTION EFICASIA</h3>
				</td>
				<td style="width: 32%; text-align: right;  margin: 0px 10px 0px 0px "> 
				
						<h4>
							<span style="color: #C01722; font-size: 16px; font-weight: bold; letter-spacing: 1px; margin: 0px 10px 0px 0px ">
							<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">Bienvenido:</sec:authorize></span>
						</h4>
						<h5>
							<span style="color: #fff; text-align: left; font-size: 14px;  margin: 0px 10px 0px 0px"> <c:if
									test="${UsuarioSesion != null}">
									<span class="tranformarMayusculas"><sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">
									${UsuarioSesion.nombre} ${UsuarioSesion.apellidoPaterno}</sec:authorize> </span>
								</c:if>

							</span>
						</h5></td>
				<td ></td>

			</tr>
		</table>
		
		<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">
			<table style="width: 100%; margin-top: 4.5em; height: 2em;">
			<tr >
					<td colspan="4" style="font-size: 14px">
			<nav class="navbar-default " 
							style="margin: -0.2% 0% 0% 0px;width: 100%;
							background-color:#013F7A ;border-color: #013F7A;
							 border-radius: 0px 0px 0px 0px;">
							<div class="container" style="width: 100%; height: 3.4em" >
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								
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
								</sec:authorize>
								<ul class="nav navbar-nav navbar-left">
									<li style=" height: 3.4em;" class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Agenda <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value='/listarAgendas' />">Consultar</a></li>
											<li><a href="<c:url value='/agregarAgenda' />">Agregar</a></li>
											<li role="separator" class="divider dividerColor"></li>
											<li><a class="subtitulosDropDown"
												style="color: #000; font-weight: bold; cursor: default;"
												>Carga masiva&nbsp;<span class="glyphicon glyphicon-upload"></span></a></li>
											<li role="separator" class="divider"></li>
											<li><a href="<c:url value='/cargaMasiva' />">Agregar</a></li>
											<li><a href="<c:url value='/eliminacionMasiva' />">Eliminar</a></li>
										</ul></li>
								</ul>

								<ul  style="text-align: right; margin: 20px 0px 0px 0px">
									<a style="color: white; font-weight: bold;"
										href="<c:url value='/logout' />"><span
											class="glyphicon glyphicon-log-in"></span>&nbsp;Log out</a>
								</ul>

								
							</div>
						</nav>

					</td>
				
			</tr>
		</table>
		
		</sec:authorize>
		
		
		
	</div>
	<br>

<!-- FIN CABECERA -->

<div class="container" style="width: 100% ; margin-top: 10em; ; z-index:1; position: absolute  ;">
		<div style="text-align: center; font-weight: bold;font-size: 20px" >Modificar agenda</div>
		<br>
	<div class="contenidoUsuarios">
	<div class="contenedorFormulario">
	<div class="generic-container">
    
    <form:form method="POST" modelAttribute="agenda" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="codigoTransaccion">Codigo transacci�n</label>
                <div class="col-md-7">
                    <form:input type="text" path="codigoTransaccion" id="codigoTransaccion" class="form-control input-sm" readonly="true"/>
                    <div class="has-error errores">
                        <form:errors path="codigoTransaccion" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="fechaTransaccion">Fecha transacci�n</label>
                <div class="col-md-7">
                    <form:input type="text" path="fechaTransaccion" id="fechaTransaccion" class="form-control input-sm" readonly="true"/>
                    <div class="has-error errores">
                        <form:errors path="fechaTransaccion" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="fechaCierre">Fecha cierre</label>
                <div class="col-md-7">
                    <form:input type="text" path="fechaCierre" id="fechaCierre" class="form-control input-sm datepicker"/>
                    <div class="has-error errores">
                        <form:errors path="fechaCierre" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="numeroCliente">No. cliente</label>
                <div class="col-md-7">
                    <form:input type="text" path="numeroCliente" id="numeroCliente" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="numeroCliente" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="razonSocial">Raz�n social</label>
                <div class="col-md-7">
                    <form:input type="text" path="razonSocial" id="razonSocial" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="razonSocial" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="nombreRepresentante">Nombre representante</label>
                <div class="col-md-7">
                    <form:input type="text" path="nombreRepresentante" id="nombreRepresentante" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="nombreRepresentante" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="numeroTelefono">Telefono</label>
                <div class="col-md-7">
                    <form:input type="text" path="numeroTelefono" id="numeroTelefono" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="numeroTelefono" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="soeid">Soeid</label>
                <div class="col-md-7">
                    <form:input type="text" path="soeid" id="soeid" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="soeid" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
      
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="ejecutivo">Ejecutivo</label>
                <div class="col-md-7">
                    <form:input type="text" path="ejecutivo" id="ejecutivo" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="ejecutivo" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div> 	
        
         <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="sede">Sede</label>
                <div class="col-md-7">
                	<form:select path="sede" class="form-control input-sm">
   						<form:option label="--- Selecciona---" value=""/>
   						<form:option label="Cuajimalpa" value="Cuajimalpa" />
   						<form:option label="Santa f�" value="Santa f�" />
					</form:select>
					<div class="has-error errores">
                        <form:errors path="sede" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div> 
        
        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Modificar"
											class="btn btn-primary custom-width" style="background-color: #013F7A" /> 
											
												<a href="<c:url value='/listarAgendas' />"
										class="btn btn-primary custom-width" style="background-color: #941A26; border-color:#941A26 "> Cancelar</a>
								
				
                    
            </div>
        </div>
        
    </form:form>
    </div>
    </div>
    </div>
    <br>
         	<footer class="footerA" >
         <table style="width: 100%">
        <tbody><tr>
            <td align="left"><img src="${pageContext.request.contextPath}/resources/Images/Logos/banamex_logo_footer.gif" alt="">
            <br><a href="http://intranet.banamex.com/" class="FontFooterLinkBlue"  style="font-size: 11px">http://intranet.banamex.com/</a> 
            <span style="font-size: 11px">v 1.0.1.1</span> </td>
            <td align="right"><img src="${pageContext.request.contextPath}/resources/Images/Logos/citi_logo.gif" alt="">
            <br><div class="FontFooterLinkBlue"  style="font-size: 11px">Copyright � Citigroup 2012. Todos los derechos reservados.</div></td>
        </tr>
        </tbody></table>
      
    
</footer>	
</div>

</body>
</html>