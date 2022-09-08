<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Choose Your Own Adventure</title>
</head>
<body>

	<p>Choose A Story From The List Below</p>

 	<c:forEach var="option" items="${options}" varStatus="loop">
		<p>
		    <img src="${urls[loop.index]}"/> <br/>
			<a href="/scene/${option.getStartingScene()}">${option.title}</a>
		</p>
	</c:forEach>
	
	<p>Feeling Creative? <a href="/createStory">Start Your Own Story</a></p>

</body>
</html>