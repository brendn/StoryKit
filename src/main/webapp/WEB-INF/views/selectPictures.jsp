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
	<h2>Select the picture you want</h2>
	<img src="${url}">

	
	<form class="form" method="POST" action="/selectPictures">

		<div class="searchOptions">
			<input type="hidden" id="id" name="id" value="${id}">
			<c:forEach var="url" items="${urls}" varStatus="loop">
			<input type="radio" name="searchType" value="${url}" checked="checked">
				<div class="card">
					<label for="picture"> <img src="${urls[loop.index]}">
					</label>

					<div class="container"></div>
				</div>
			</c:forEach>

		</div>
		<input type="submit" />
	</form>
 




	<p>
		Feeling Creative? <a href="/createStory" class="button1">Start
			Your Own Story</a>
	</p>

</body>
</html>