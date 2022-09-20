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
    <a href="/" class="button1">Home</a>

    <div class="main">
    <h2>Start A New Story</h2>

    <form action="/createStory" method="post">
        <input type="text" id="title" name="title" placeholder="Title" value="">
        <input type="text" id="picture" name="picture" placeholder="Picture" value="">
        <br />
        <input type="text" id="option" name="option" placeholder="Starting Scene Title" value=""><br>
        <textarea name="description" rows="4" cols="50" placeholder="Starting Scene Description"></textarea>
        <input type="submit" value="Submit">
    </form>
    </div>
</body>

</html>