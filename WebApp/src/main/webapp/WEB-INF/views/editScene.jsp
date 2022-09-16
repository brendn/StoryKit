<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Scene</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <a href="/">Home</a>
    <br />
    <br />
    <a href="/deleteScene/${id}">Delete Scene</a>
    <a href="/scene/${id}">Cancel</a>

    <h2>Edit scene</h2>

    <label for="option">Scene Option:</label><br>
    <form action="/editScene/{id}" method="post">
		<input type="hidden" id="id" name="id" value="${id}">
        <input type="text" id="option" name="title" value="${title}"><br>
        <label for="option">Scene Description:</label>
        <br>
        <textarea name="description" rows="4" cols="50">${description}</textarea>
        <input type="submit" value="Submit">
    </form>
</body>
</html>