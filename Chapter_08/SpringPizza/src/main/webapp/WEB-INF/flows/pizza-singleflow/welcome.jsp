<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

  <head><title>Spring Pizza</title></head>

  <body>
  	<h2>Welcome to Spring Pizza!!!</h2>
	
		<form:form>
      <input type="hidden" name="_flowExecutionKey" 
             value="${flowExecutionKey}"/>
		  <input type="text" name="phoneNumber"/><br/>
      <input type="submit" name="_eventId_phoneEntered" value="Lookup Customer" />
		</form:form>
	</body>
</html>