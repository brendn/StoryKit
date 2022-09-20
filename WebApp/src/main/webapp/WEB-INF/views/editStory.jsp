<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Edit A Story</title>
    <link rel="stylesheet" href="/styles.css">
</head>

<body>
<a href="/" class="button1">Home</a>
<a href="/deleteStory/${id}" class="button1">Delete Story</a>

<div class="main">
    <h2>Edit Story</h2>

    <form action="/editStory" method="post">
        <input type="hidden" id="storyID" name="storyID" value="${id}">
        <label for="title">Story Title:</label> <input type="text" id="title" name="title" value="${title}"><br>
        <label for="picture">Story Picture: </label> <input type="text" id="picture" name="picture"
                                                            value="${picture}"><br>
        <br/>
        <input type="submit" value="Submit">
    </form>
</div>
</body>

</html>