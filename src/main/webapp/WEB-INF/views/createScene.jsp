<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create A New Scene</title>
</head>
<body>
	<a href="/index">Home</a>
	
	<h2>Create a new scene</h2>
	
	<label for="option">Scene Option:</label><br>
	<form action="/create/${id}" method="post">
		<input type="text" id="option" name="title" value=""><br>

	<label for="option">Scene Description:</label>
	<br>
	
		<textarea name = "description" rows="4" cols="50"></textarea>
		<input type="submit" value="Submit">
	</form>

	<!-- <form class="form" method = "POST" action ="/createScene">
		<div class="wrap">
			<div class="search">
				<input type="text" placeholder="Start typing..." class="searchTerm" name="searchParam" placeholder="Search here!">
				<button type="submit" class="searchButton">
					<i class="fa fa-search"></i>
				</button>
			</div>
		</div>
	</form>	
	 -->

</body>
</html>