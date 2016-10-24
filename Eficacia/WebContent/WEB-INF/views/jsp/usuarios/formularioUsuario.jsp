<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../fragments/resources.jsp" />
<spring:url value="resources/js/password-score.js" var="passwordScoreJs" />
<spring:url value="resources/js/bootstrap-strength-meter.js" var="strength" />
<script src="${passwordScoreJs}"></script>
<script src="${strength}"></script>
<jsp:include page="../fragments/header.jsp" />

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
    
    $('#example-getting-started-input').strengthMeter('text', {
        container: $('#example-getting-started-text')
    });
    
});
</script>
</head>
<body>

       <div class="container" style="width: 100% ; margin-top: 10em; ; z-index:1; position: absolute  ;">
		<div style="text-align: center; font-weight: bold;font-size: 20px" >REGISTRAR USUARIO</div>
		<br>
       <div class="contenidoUsuarios">
       <div class="contenedorFormulario">
       <div class="generic-container">
    
    <form:form method="POST" modelAttribute="usuario" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        
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
                <label class="col-md-3 control-lable" for="nombre">Nombre usuario</label>
                <div class="col-md-7">
                    <form:input type="text" path="nombre" id="nombre" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="nombre" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="apellidoPaterno">Apellido paterno</label>
                <div class="col-md-7">
                    <form:input type="text" path="apellidoPaterno" id="apellidoPaterno" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="apellidoPaterno" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="apellidoMaterno">Apellido materno</label>
                <div class="col-md-7">
                    <form:input type="text" path="apellidoMaterno" id="apellidoMaterno" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="apellidoMaterno" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="telefono">Teléfono</label>
                <div class="col-md-7">
                    <form:input type="text" path="telefono" id="telefono" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="telefono" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Contraseña</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="example-getting-started-input"
                     class="form-control input-sm"  data-toggle="tooltip" 
                     title="Debe contener al menos una letra mayúscula, minúsculas, al menos un número, al menos un caracter especial y no debe contener espacios en blanco"/>               
                    <div class="has-error errores">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
                <div class="col-md-2" id="example-getting-started-text" style="display:inline;font-weight:bold;padding:6px 12px;">
                    </div>
            </div>
            
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="passwordConfirmation">Confirmación de contraseña</label>
                <div class="col-md-7">
                    <form:input type="password" path="passwordConfirmation" id="passwordConfirmation" class="form-control input-sm" data-toggle="tooltip" title="Debe contener al menos una letra mayúscula, minúsculas, al menos un número, al menos un caracter especial y no debe contener espacios en blanco"/>
                    <div class="has-error errores">
                        <form:errors path="passwordConfirmation" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
      
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="rol">Perfil</label>
                <div class="col-md-7">
                    <form:select path="rol" class="form-control input-sm">
                                        <form:options itemValue="id" itemLabel="nombre"  items="${roles}"></form:options>
                                  </form:select>
                
                    <div class="has-error errores">
                        <form:errors path="rol" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>      
        
        <br/>
        
        <div class="row">
            <div class="form-actions floatLeft">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Modificar" class="btn btn-primary btn-sm"/> o <a href="<c:url value='/listarUsuarios' />">Cancelar</a>
                    </c:when>
                    <c:otherwise>
                                       <input type="submit" value="Agregar"
											class="btn btn-primary custom-width" style="background-color: #013F7A" /> 
											
												<a href="<c:url value='/listarUsuarios' />"
										class="btn btn-primary custom-width" style="background-color: #941A26; border-color:#941A26 "> Cancelar</a>
						
                    </c:otherwise>
                    
                    
                    
                </c:choose>
            </div>
        </div>
        
    </form:form>
    </div>
    </div>
    </div>
<jsp:include page="../fragments/footer.jsp"  />	
</div>

</html>
