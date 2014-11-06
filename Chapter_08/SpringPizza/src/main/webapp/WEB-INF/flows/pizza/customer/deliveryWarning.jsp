<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head><title>Spring Pizza</title></head>
  
  <body>
		<h2>Delivery Unavailable</h2>
		
		<p>The address is outside of our delivery area. The order
		may still be taken for carry-out.</p>
		
		<a href="${flowExecutionUrl}&_eventId=accept">Accept</a> | 
		<a href="${flowExecutionUrl}&_eventId=cancel">Cancel</a>
  </body>
</html>