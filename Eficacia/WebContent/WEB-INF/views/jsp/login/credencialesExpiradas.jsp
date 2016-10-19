<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambiar contraseña</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
<jsp:include page="../fragments/header.jsp" />
	
	<div class="well lead tituloPagina">CAMBIAR CONTRASEÑA</div>
	
	<form action="contrasenaExpirada">
	<div style="margin-top:50px;auto;" class="container bootstrap snippet">
    <div style="border:solid;margin: 0px auto;" >
        <div class="col-xs-12 col-sm-12 col-md-6 col-md-offset-2">
            <div style="border-color: #003478;" class="panel panel-info">
                <div style="background:#003478;color:#FFFFFF;font-weight:bold;" class="panel-heading">
                    <h3 class="panel-title">
                        <span style="font-weight:bold;" class="glyphicon glyphicon-th"></span>
                        Cambiar password   
                    </h3>
                </div>
               
                <div class="panel-body">
                	<div style="text-align:center;">
                		<i style="font-size:50px;" class="glyphicon glyphicon-lock"></i>                     
                        <h2 class="text-center">Tu contraseña expiró</h2>
                        <p>Puedes cambiar tu contraseña aquí.</p>
                	</div>
                	<br/><br/>
                	<div class="row">
			            <div class="form-group col-md-12">
			                <label class="col-md-3 control-lable" for="passwordActual">Contraseña actual</label>
			                <div class="input-group">
			                    <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                              	<input class="form-control" name="passwordActual" id="passwordActual" type="password" placeholder="Contraseña Actual" value="${passActual}">
			                </div>
			            </div>
			        </div>
			        
			        <div class="row">
			            <div class="form-group col-md-12">
			                <label class="col-md-3 control-lable" for="passwordNuevo">Contraseña nueva</label>
			                <div class="input-group">
			                    <div class="input-group-addon"><span class="glyphicon glyphicon-log-in"></span></div>
                              	<input class="form-control" name="passwordNuevo" id="passwordNuevo" type="password" placeholder="Contraseña Nueva" value="${passNuevo}" data-toggle="tooltip" title="Debe contener al menos una letra mayúscula, minúsculas, al menos un número, al menos un caracter especial y no debe contener espacios en blanco">
			                </div>
			            </div>
			        </div>
			        
			        <div class="row">
			            <div class="form-group col-md-12">
			                <label class="col-md-3 control-lable" for="confirmacionPasswordNuevo">Confirmación contraseña</label>
			                <div class="input-group">
			                    <div class="input-group-addon"><span class="glyphicon glyphicon-log-in"></span></div>
                              	<input class="form-control" name="confirmacionPasswordNuevo" id="confirmacionPasswordNuevo" type="password" placeholder="Confirmación contraseña" value="${confirmacionPasswordNuevo}">
			                </div>
			            </div>
			        </div>

					 <div class="row">
			            <div class="form-group col-md-12">
			                <c:if test="${not empty error}">
						         <div style="text-align:center;color:red;margin:10px 0px;font-weight:bold;">
						                Error!!!<br />
						                ${error}<br/>						            
						         </div>
				    		</c:if>			                
			            </div>
			        </div>

                </div>
                
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6"></div>
                        <div class="col-xs-6 col-sm-6 col-md-6 col-sm-12"  style="text-align:left;">                        
                           <!--  <a href="<c:url value='/contrasenaExpirada' />" type="submit" class="btn btn-success custom-width"><span class="glyphicon glyphicon-floppy-disk"></span>Guardar</a>-->
                           <button type="submit" class="btn btn-success"><i style="height:19px;" class="glyphicon glyphicon-check"></i>&nbsp; Guardar</button> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	</form>
	
	

<jsp:include page="../fragments/footer.jsp" />
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
    
});
</script>
</body>
</html>