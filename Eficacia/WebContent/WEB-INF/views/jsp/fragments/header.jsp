<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<nav class="navbar navbar-default navbar-static-top cabecera">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><spring:message code="header.lbl.titulo" /></a>
    </div>
    <ul class="nav navbar-nav navbar-left">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="header.lbl.seccionUsuarios" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<c:url value='/listarUsuarios' />"><spring:message code="header.lbl.consultarUsuarios" /></a></li>
            <li><a href="<c:url value='/agregarUsuario' />"><spring:message code="header.lbl.agregarUsuario" /></a></li>
          </ul>
        </li>
     </ul>
     <ul class="nav navbar-nav navbar-left">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="header.lbl.seccionAgendas" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<c:url value='/listarAgendasPaginacion' />"><spring:message code="header.lbl.consultarAgendas" /></a></li>
            <li><a href="<c:url value='/agregarAgenda' />"><spring:message code="header.lbl.agregarAgendas" /></a></li>
            <li role="separator" class="divider dividerColor" ></li>
            <li><a class="subtitulosDropDown" style="color:#000;font-weight:bold;cursor:default;" href="#"><spring:message code="header.lbl.CargaMasivaAgendas" /> &nbsp;<span class="glyphicon glyphicon-upload"></span></a></li>
            <li role="separator" class="divider" ></li>
            <li><a href="<c:url value='/cargaMasiva' />"><spring:message code="header.lbl.cargaMasivaAgendas" /></a></li>
            <li><a href="<c:url value='/eliminacionMasiva' />"><spring:message code="header.lbl.eliminarMasivaAgendas" /></a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li style="color:#FFFFFF;vertical-align:middle;font-weight:bold;margin-top:16px;"><spring:message code="header.lbl.bienvenida" /> <c:if test="${UsuarioSesion != null}"><span class="tranformarMayusculas">${UsuarioSesion.nombre}</span></c:if></li>
      	<li class="dropdown">
      	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span style="font-size:25px;text-align:center;" class="glyphicon glyphicon-user pull-right iconoUsuario"></span></a>
      	<ul class="dropdown-menu">
            <li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;<spring:message code="header.lbl.opciones" /></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-th"></span>&nbsp;<spring:message code="header.lbl.utilerias" /></a></li>
            <li role="separator" class="divider dividerColor"></li>
            <li><a href="<c:url value='/logout' />"><span class="glyphicon glyphicon-log-in"></span>&nbsp;<spring:message code="header.lbl.logout" /></a></li>
          </ul>
        </li>
      </ul>
  </div>
</nav> 
