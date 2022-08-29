<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Choose Your Own Adventure</title>
    </head>
    <body>
    	<p>Description: ${description}</p>
        
        <c:forEach var="scene" items="${options}">
						<p>${scene.description}</p>
		</c:forEach>
        
    </body>
</html>