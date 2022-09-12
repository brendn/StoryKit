<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Start A Story</title>
    <link rel="stylesheet" href="/styles.css">
</head>

<body>
    <a href="/">Home</a>

    <h2>Start A New Story</h2>

    <form action="/createStory" method="post">
        <label for="title">Story Title:</label> <input type="text" id="title" name="title" value=""><br>
        <label for="picture">Story Picture: </label> <input type="text" id="picture" name="picture" value=""><br>
        <br />
        <label for="option">Starting Scene Title:</label> <input type="text" id="option" name="option" value=""><br>
        <label for="description">Starting Scene Description:</label>
        <br>
        <textarea name="description" rows="4" cols="50"></textarea>
        <input type="submit" value="Submit">
    </form>

</body>

</html>