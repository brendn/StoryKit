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
    <a href="/" class="button1">Home</a>
    <a href="/deleteScene/${id}" class="button1">Delete Scene</a>
    <a href="/scene/${id}" class="button1">Cancel</a>

    <div class="main">
    <h2>Edit scene</h2>

    <label for="option">Scene Title:</label><br>
    <form action="/editScene/{id}" method="post">
		<input type="hidden" id="id" name="id" value="${id}">
        <input type="text" id="option" name="title" value="${title}"><br>
        <label for="option">Scene Description:</label>
        <br/>
        <textarea name="description" rows="4" cols="50">${description}</textarea>
        <input type="submit" value="Submit">
    </form>
    </div>
</body>
</html>