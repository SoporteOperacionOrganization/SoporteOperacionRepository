<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<jsp:include page="../fragments/header.jsp" />

	<div class="well lead tituloPagina">Modificar agenda</div>
	<div class="contenidoAgendas">
	<div class="contenedorFormulario">
	<div class="generic-container">
    
    <form:form method="POST" modelAttribute="agenda" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="codigoTransaccion">Codigo transacci�n</label>
                <div class="col-md-7">
                    <form:input type="text" path="codigoTransaccion" id="codigoTransaccion" class="form-control input-sm"/>
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
                    <form:input type="text" path="fechaTransaccion" id="fechaTransaccion" class="form-control input-sm datepicker"/>
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
                
                 
                        <input type="submit" value="Modificar" class="btn btn-primary custom-width"/> o <a href="<c:url value='/listarAgendas' />">Cancelar</a>
                    
            </div>
        </div>
        
    </form:form>
    </div>
    </div>
    </div>

<jsp:include page="../fragments/footer.jsp" />
</body>
</html>