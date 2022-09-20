<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Choose Your Own Adventure</title>
<link rel="stylesheet" href="/styles.css">
</head>
<body>
    <a href="/" class="button1">Home</a>
    <div class="main">
    <h2>Confirm Deletion</h2>
	<p>Easy there partner! You sure you want to delete that?</p>

    <form action="/deleteStory" method="POST">
        <input type="hidden" id="id" name="id" value="${id}">
        <input type="checkbox" id="confirm" name="confirm" value="confirm">
        <label for="confirm">I understand!</label>
        <br />
        <input type="submit" value = "Submit" />
    </form>
    </div>
</body>
</html>