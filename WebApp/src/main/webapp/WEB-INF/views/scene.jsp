<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <title>Choose Your Own Adventure</title>
    <link rel="stylesheet" href="/styles.css">
</head>

<body>
    <a href="/" class="button1">Home</a>
    <a href="/editStory/${scene.getStoryId()}" class="button1">Edit Story</a>
    <a href="/editScene/${id}" class="button1">Edit Scene</a>
    <div class="mainStory">
    <h3>
        <p>${title}</p>
    </h3>
    <h4>
        <p><i>${description}</i></p>
    </h4>

    <c:forEach var="option" items="${options}">
        <p><a href="/scene/${option.getID()}" class="button1">${option.title}</a></p>
    </c:forEach>

    <a href="/create/${id}" class="button1">(Add a new option)</a>
    </div>
</body>
</html>