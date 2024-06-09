<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pensieve</title>

<%--     <script src="${path}/js/jquery/jquery-3.7.1.js"></script> --%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%--     <link rel="stylesheet" href="${path}/css/bootstrap.css"> --%>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${path}/css/common/login5.css">

    
  </head>
<body>
<div class="wrapper">
  <div class="container">
    <h1>Welcome</h1>
    
    <form class="form">
      <input type="text" placeholder="Username">
      <input type="password" placeholder="Password">
      <button type="submit" id="login-button">Login</button>
    </form>
  </div>
  
  <ul class="bg-bubbles">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
  </ul>
</div>
   <script> 
$("#login-button").click(function(event){
     event.preventDefault();
     
     $('form').fadeOut(500);
     $('.wrapper').addClass('form-success');
  });
   </script>
</body>
</html>
