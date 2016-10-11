<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default navbar-static-top cabecera">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">JAVA APP DEMO</a>
    </div>
    <ul class="nav navbar-nav navbar-left">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Usuarios <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<c:url value='/listarUsuarios' />">Consultar</a></li>
            <li><a href="<c:url value='/agregarUsuario' />">Agregar</a></li>
          </ul>
        </li>
     </ul>
     <ul class="nav navbar-nav navbar-left">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Agenda <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<c:url value='/listarAgendas' />">Consultar</a></li>
            <li><a href="<c:url value='/agregarAgenda' />">Agregar</a></li>
            <li role="separator" class="divider dividerColor" ></li>
            <li><a class="subtitulosDropDown" style="color:#000;font-weight:bold;cursor:default;" href="#">Carga masiva &nbsp;<span class="glyphicon glyphicon-upload"></span></a></li>
            <li role="separator" class="divider" ></li>
            <li><a href="<c:url value='/cargaMasiva' />">Agregar</a></li>
            <li><a href="<c:url value='/eliminacionMasiva' />">Eliminar</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li style="color:#FFFFFF;vertical-align:middle;font-weight:bold;margin-top:16px;">Bienvenido <c:if test="${UsuarioSesion != null}"><span class="tranformarMayusculas">${UsuarioSesion.nombre}</span></c:if></li>
      	<li class="dropdown">
      	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span style="font-size:25px;text-align:center;" class="glyphicon glyphicon-user pull-right iconoUsuario"></span></a>
      	<ul class="dropdown-menu">
            <li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;Opciones</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-th"></span>&nbsp;Utilerias</a></li>
            <li role="separator" class="divider dividerColor"></li>
            <li><a href="<c:url value='/logout' />"><span class="glyphicon glyphicon-log-in"></span>&nbsp;Log out</a></li>
          </ul>
        </li>
      </ul>
  </div>
</nav> 
