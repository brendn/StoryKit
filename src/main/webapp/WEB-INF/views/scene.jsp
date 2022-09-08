<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Choose Your Own Adventure</title>
    </head>
    <body>
         
    	<p>${title}</p>
    	
     	<p>${description}</p>
      
         <c:forEach var="option" items="${options}">
            <p><a href="/scene/${option.id}">${option.title}</a></p>
		</c:forEach>  
		
		<a href="/create/${id}">Create your own scene</a>
        
        
    </body>
</html>