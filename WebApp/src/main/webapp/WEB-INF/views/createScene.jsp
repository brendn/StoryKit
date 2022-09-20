<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create A New Scene</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
    <a href="/" class="button1">Home</a>
    <a href="/scene/${id}" class="button1">Cancel</a>

    <div class="main">
    <h2>Create a new scene</h2>

    <form action="/create/${id}" method="post">
        <input type="text" id="option" name="title" value="" placeholder="Title"><br>
        <textarea name="description" rows="4" cols="50" placeholder="Description"></textarea>
        <input type="submit" value="Submit">
    </form>
    </div>
</body>
</html>