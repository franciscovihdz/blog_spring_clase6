<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!doctype html/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

   <h1>POST ACTUAL</h1>
   
   <c:out value="${nombre}" />
   
   
   <h2>${post.titulo}</h2>
   
   <p>${post.contenido}</p>
   
   <h2>COMENTARIOS:</h2>
   
   <c:forEach var="comentario" items="${post.comentarios}">
   		<h4><c:out value="${comentario.comentarista}"></c:out></h4>
   		<p><c:out value="${comentario.comentario}"/></p>
   </c:forEach>

</body>
</html>