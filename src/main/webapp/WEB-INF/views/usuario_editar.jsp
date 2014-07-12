<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="usuario.editar.titulo"/></title>
</head>
<body>

   <!--  SOLUCION EJERCICIO -->
   <c:url var="urlLogout" value="/logout"/>
   <a href="${urlLogout}">cerrar sesión</a>
   <c:url var="urlGuardar" value="/usuario/guardar"/>
   <c:if test="${actualizado} ">
   	<h3><spring:message code="usuario.editar.datosGuardados"/></h3>
   </c:if>
   <form:form action="${urlGuardar}"  method="post" commandName="usuario">
	<form:hidden path="id" />
     <div>
       <label><spring:message code="usuario.editar.email"/></label>
       <form:input type="text" path="email"/>
       <div>
       	<form:errors path="email"/>
       </div>
     </div>
     <div>
       <label><spring:message code="usuario.editar.nombre"/></label>
       <form:input type="text" path="nombre" />
       <div>
       	<form:errors path="nombre"/>
       </div>
     </div>
     <div>
       <label><spring:message code="usuario.editar.password"/></label>
       <form:input type="password" path="password"/>
       <div>
       	<form:errors path="password"/>
       </div>
     </div>
     <input type="submit" value="enviar"/>
   </form:form>

</body>
</html>