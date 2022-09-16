<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Select a Picture</title>
<link rel="stylesheet" href="/styles.css">
</head>
<body>
	<h2>Select the picture you want</h2>
	<form class="form" method="POST" action="/selectPictures">
		<div class="searchOptions">
			<input type="hidden" id="id" name="id" value="${id}">
			<c:forEach var="url" items="${urls}" varStatus="loop">
				<div class="card">
					<input type="radio" name="searchType" value="${url}" checked="checked">
					<label> <img src="${urls[loop.index]}"> </label>
					<div class="container"></div>
				</div>
			</c:forEach>
		</div>
		<input type="submit" />
	</form>
</body>
</html>