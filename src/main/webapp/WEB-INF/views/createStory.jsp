<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start A Story</title>
</head>
	<body>
		<a href="/index">Home</a>
		
		<h2>Start A New Story</h2>
		
		<label for="option">Scene Option:</label><br>
		<form action="/create/${id}" method="post">
			<input type="text" id="option" name="title" value=""><br>
	
		<label for="option">Scene Description:</label>
		<br>
		
			<textarea name = "description" rows="4" cols="50"></textarea>
			<input type="submit" value="Submit">
		</form>
	
	</body>
</html>