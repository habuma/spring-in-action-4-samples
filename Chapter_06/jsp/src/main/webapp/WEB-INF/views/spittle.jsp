<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="spittleView">
      <div class="spittleMessage"><c:out value="${spittle.message}" /></div>
      <div>
        <span class="spittleTime"><c:out value="${spittle.time}" /></span>
      </div>
    </div>
  </body>
</html>