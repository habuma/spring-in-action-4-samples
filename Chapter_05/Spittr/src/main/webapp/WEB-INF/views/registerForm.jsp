<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Spitter</title>
  <link rel="stylesheet" type="text/css"
        href="<c:url value="/resources/style.css" />" >
  <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
  <style>
    .error{
      color: red;
    }
  </style>
</head>
<body>
<h1 class="text-primary">Register</h1>
<sf:form method="POST" commandName="spitter">
  <div class="row">
    <div class="col-md-12">
      <sf:errors path="*" element="div" cssClass="error"/>
    </div>
    <div class="col-md-12">
      <sf:label path="email" cssErrorClass="error">Email</sf:label>:
      <sf:input type="email" path="email" /><br/>
    </div>
    <div class="col-md-12">
      <sf:label path="username" cssErrorClass="error">Username</sf:label>;
      <sf:input type="text" path="username"/><br/>
    </div>
    <div class="col-md-12">
      <sf:label path="password" cssErrorClass="error">Password</sf:label>:
      <sf:input type="password" path="password"/><br/>
    </div>
    <div class="col-md-12">
      <input type="submit" value="Register" />
    </div>
  </div>
</sf:form>
</body>
</html>
