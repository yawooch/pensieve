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
    <link rel="stylesheet" href="${path}/css/common/login3.css">

    
  </head>
<div class="container right-panel-active">
  <!-- Sign Up -->
  <div class="container__form container--signup">
    <form action="#" class="form" id="form1">
      <h2 class="form__title">Sign Up</h2>
      <input type="text" placeholder="User" class="input" />
      <input type="email" placeholder="Email" class="input" />
      <input type="password" placeholder="Password" class="input" />
      <button class="btn">Sign Up</button>
    </form>
  </div>

  <!-- Sign In -->
  <div class="container__form container--signin">
    <form action="#" class="form" id="form2">
      <h2 class="form__title">Sign In</h2>
      <input type="email" placeholder="Email" class="input" />
      <input type="password" placeholder="Password" class="input" />
      <a href="#" class="link">Forgot your password?</a>
      <button class="btn">Sign In</button>
    </form>
  </div>

  <!-- Overlay -->
  <div class="container__overlay">
    <div class="overlay">
      <div class="overlay__panel overlay--left">
        <button class="btn" id="signIn">Sign In</button>
      </div>
      <div class="overlay__panel overlay--right">
        <button class="btn" id="signUp">Sign Up</button>
      </div>
    </div>
  </div>
</div>

   <script>
   const signInBtn = document.getElementById("signIn");
   const signUpBtn = document.getElementById("signUp");
   const fistForm = document.getElementById("form1");
   const secondForm = document.getElementById("form2");
   const container = document.querySelector(".container");

   signInBtn.addEventListener("click", () => {
     container.classList.remove("right-panel-active");
   });

   signUpBtn.addEventListener("click", () => {
     container.classList.add("right-panel-active");
   });

   fistForm.addEventListener("submit", (e) => e.preventDefault());
   secondForm.addEventListener("submit", (e) => e.preventDefault());
   </script>
</body>
</html>
