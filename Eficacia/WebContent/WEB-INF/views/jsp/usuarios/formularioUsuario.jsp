<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
<jsp:include page="../fragments/header.jsp" />
	<div class="well lead tituloPagina">REGISTRAR USUARIO</div>
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
                <label class="col-md-3 control-lable" for="telefono">Telefono</label>
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
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                    <div class="has-error errores">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="passwordConfirmation">Confirmación de contraseña</label>
                <div class="col-md-7">
                    <form:input type="password" path="passwordConfirmation" id="passwordConfirmation" class="form-control input-sm"/>
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
                        <input type="submit" value="Agregar" class="btn btn-primary custom-width"/> o <a href="<c:url value='/listarUsuarios' />">Cancelar</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
    </form:form>
    </div>
    </div>
    </div>
 			
<jsp:include page="../fragments/footer.jsp" />
</body>
</html>