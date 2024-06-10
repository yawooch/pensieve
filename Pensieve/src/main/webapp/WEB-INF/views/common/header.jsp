<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Pensieve</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon을 지정하는 link -->
<%--     <link rel="icon" type="image/ico" href="${path}/img/favicon.ico"> --%>
    <link rel="stylesheet" href="${path}/css/bootstrap.css">
    <!-- 아이콘을 사용할 수 있게하는 부트스트랩 CSS -->
    <link rel="stylesheet" href="${path}/css/font/bootstrap-icons.css">
    
<%--     <link rel="stylesheet" href="${path}/css/themes/prism-okaidia.css"> --%>
    <link rel="stylesheet" href="${path}/css/custom.css">
<script src="${path}/js/jquery/jquery-3.7.1.js"></script>
    <!-- Global Site Tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-KGDJBEFF3W"></script>
    <script src='${path}/js/fullcalendar/index.global.js'></script>
    <script src="${path}/js/bootstrap/bootstrap.bundle.js"></script>
<%--     <script src="${path}/js/bootstrap/prism.js" data-manual></script> --%>
    <script src="${path}/js/main/moment.js"></script>
    <script src="${path}/js/bootstrap/custom.js"></script>
  </head>
  <body>
    <div class="navbar navbar-expand-lg fixed-top bg-primary" data-bs-theme="dark">
      <div class="container">
        <a href="${path}/" class="navbar-brand">Pensieve</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav">
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="${path}/" id="main">Main</a>
              <div class="dropdown-menu" aria-labelledby="main">
                <a class="dropdown-item" href="${path}/main/text">text</a>
                <a class="dropdown-item" href="${path}/main/calendar">calendar</a>
                <a class="dropdown-item" href="${path}/main/timeline">timeline</a>
              </div>
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="#" id="wc">우찬</a>
              <div class="dropdown-menu" aria-labelledby="wc">
                <a class="dropdown-item" href="${path}/wc/text">text</a>
                <a class="dropdown-item" href="${path}/wc/calendar">calendar</a>
                <a class="dropdown-item" href="${path}/wc/timeline">timeline</a>
              </div>
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="#" id="yw">영우</a>
              <div class="dropdown-menu" aria-labelledby="yw">
                <a class="dropdown-item" href="${path}/yw/text">text</a>
                <a class="dropdown-item" href="${path}/yw/calendar">calendar</a>
              </div>
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="${path}/jy" id="jy">주연</a>
              <div class="dropdown-menu" aria-labelledby="jy">
                <a class="dropdown-item" href="${path}/jy/memo">memo</a>
                <a class="dropdown-item" href="${path}/jy/calendar">calendar</a>
                <a class="dropdown-item" href="${path}/jy/todo">todo list</a>
              </div>
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="#" id="yh">연희</a>
              <div class="dropdown-menu" aria-labelledby="yh">
                <a class="dropdown-item" href="${path}/yh/">text</a>
                <a class="dropdown-item" href="${path}/yh/calendar">calendar</a>
                <a class="dropdown-item" href="${path}/yh/timeline">timeline</a>
              </div>
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="#" id="ja">정아</a>
              <div class="dropdown-menu" aria-labelledby="ja">
                <a class="dropdown-item" href="${path}/ja/text">text</a>
                <a class="dropdown-item" href="${path}/ja/calendar">calendar</a>
              </div>
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"  href="${path}/" id="template">Template</a>
              <div class="dropdown-menu" aria-labelledby="template">
                <a class="dropdown-item" href="${path}/template/themePage" id="themes">Theme</a>
                <a class="dropdown-item" href="${path}/template/text">text</a>
                <a class="dropdown-item" href="${path}/template/timeline"  >timeline</a>
                <a class="dropdown-item" href="${path}/template/calendar1" >calendar1 </a>
                <a class="dropdown-item" href="${path}/template/calendar2" >calendar2 </a>
                <a class="dropdown-item" href="${path}/template/calendar3" >calendar3 </a>
                <a class="dropdown-item" href="${path}/template/calendar4" >calendar4 </a>
                <a class="dropdown-item" href="${path}/template/calendar5" >calendar5 </a>
                <a class="dropdown-item" href="${path}/template/calendar6" >calendar6 </a>
                <a class="dropdown-item" href="${path}/template/calendar7" >calendar7 </a>
                <a class="dropdown-item" href="${path}/template/calendar8" >calendar8 </a>
                <a class="dropdown-item" href="${path}/template/calendar9" >calendar9 </a>
                <a class="dropdown-item" href="${path}/template/calendar10">calendar10</a>
                <a class="dropdown-item" href="${path}/template/calendar11">calendar11</a>
                <a class="dropdown-item" href="${path}/template/calendar12">calendar12</a>
                <a class="dropdown-item" href="${path}/template/calendar13">calendar13</a>
              </div>
            </li>
          </ul>
          
          <ul class="navbar-nav ms-md-auto">
          
            <!-- 로그인 전 -->
            <c:if test="${ empty loginMember }">
	           <li class="nav-item">
	             <a target="_blank" rel="noopener" class="nav-link" href="${path}/login">
	               <i class="bi bi-box-arrow-in-right"></i>
	             </a>
	           </li>
            </c:if>
            <!-- 로그인 후 -->
            <c:if test="${ not empty loginMember }">
	            <li class="nav-item dropdown" data-bs-theme="light">
	              <a class="nav-link d-flex align-items-center" href="${path}/mypage" id="version-menu">
	                <i class="bi bi-person-circle"></i>
	              </a>
	            </li>
            </c:if>
          </ul>
        </div>
      </div>
    </div>