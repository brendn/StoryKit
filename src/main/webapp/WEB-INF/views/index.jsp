<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Choose Your Own Adventure!</title>
</head>
<body>
	<h1>Choose Your Own Adventure Game!</h1>
	<p>title: ${title}</p>

	<p>Description: ${description}</p>

	<c:forEach var="option" items="${options}">
		<c:out value="${option.id}" />
		<c:out value="${option.description}" />
	</c:forEach>
	

	<a href="/create/${id}">Create your own scene</a>

</body>
</html>