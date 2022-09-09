<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Choose Your Own Adventure</title>
    </head>
    <body>
         
    	<h3><p>${title}</p></h3>
    	
     	<h4><p><i>${description}</i></p></h4>
      
         <c:forEach var="option" items="${options}">
            <p><a href="/scene/${option.id}">${option.title}</a></p>
		</c:forEach>
		
		<a href="/create/${id}">Create your own scene</a>
		<br/>
		<a href="/">Home</a>
        
        
    </body>
</html>