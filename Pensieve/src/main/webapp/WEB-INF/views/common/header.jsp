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
<%--     <link rel="icon" type="image/ico" href="${path}/img/favicon.ico"> --%>
    <link rel="stylesheet" href="${path}/css/bootstrap.css">
    <link rel="stylesheet" href="${path}/css/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${path}/css/themes/prism-okaidia.css">
    <link rel="stylesheet" href="${path}/css/custom.min.css">
    <!-- Global Site Tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-KGDJBEFF3W"></script>

    

    <script src="${path}/js/bootstrap/bootstrap.bundle.js"></script>
    <script src="${path}/js/bootstrap/prism.js" data-manual></script>
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
                <a class="dropdown-item" href="${path}/text/">text</a>
                <a class="dropdown-item" href="${path}/calendar/">calendar</a>
                <a class="dropdown-item" href="${path}/timeline/">timeline</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${path}/wc">우찬</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${path}/yw">영우</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${path}/jy">주연</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${path}/yh">연희</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${path}/themePage" id="themes">Theme</a>
            </li>
          </ul>
          <ul class="navbar-nav ms-md-auto">
            <li class="nav-item">
              <a target="_blank" rel="noopener" class="nav-link" href="#"><i class="bi bi-github"></i><span class="d-lg-none ms-2">GitHub</span></a>
            </li>
            <li class="nav-item">
              <a target="_blank" rel="noopener" class="nav-link" href="#"><i class="bi bi-twitter"></i><span class="d-lg-none ms-2">Twitter</span></a>
            </li>
            <li class="nav-item py-2 py-lg-1 col-12 col-lg-auto">
              <div class="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
              <hr class="d-lg-none my-2 text-white-50">
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link d-flex align-items-center" href="${path}/themePage" id="version-menu">
                <span>v5.3</span>
              </a>
            </li>
            <li class="nav-item py-2 py-lg-1 col-12 col-lg-auto">
              <div class="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
              <hr class="d-lg-none my-2 text-white-50">
            </li>
            <li class="nav-item dropdown" data-bs-theme="light">
              <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="theme-menu" aria-expanded="false" data-bs-toggle="dropdown" data-bs-display="static" aria-label="Toggle theme">
                <i class="bi bi-circle-half"></i>
                <span class="d-lg-none ms-2">Toggle theme</span>
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light" aria-pressed="false">
                    <i class="bi bi-sun-fill"></i><span class="ms-2">Light</span>
                  </button>
                </li>
                <li>
                  <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="dark" aria-pressed="true">
                    <i class="bi bi-moon-stars-fill"></i><span class="ms-2">Dark</span>
                  </button>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>