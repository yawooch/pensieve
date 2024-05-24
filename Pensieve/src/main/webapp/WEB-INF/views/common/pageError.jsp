<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
html{
min-height:100%;
}
body{
    padding:68px 0px 0px 0px;
    background-image: linear-gradient(to bottom, #091013, #789c9c);
}
</style>
<div class="container">
      <div class="row">
        <div class="col-lg-12 text-center" style="">
            <img  style="width:80%;margin: 0 auto;" src="${path}/img/errorPage_temp.jpg"/>
<%--             <div  style="max-width:1088px;max-height:656px;background-image:url('${path}/img/errorPage_temp.jpg');"></div> --%>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="text-light mt-5">Page Error</h1>
            <h3 class="text-light">Back to <a href="${path}/">MainPage</a></h3>
        </div>
      </div>
</div>
</body>
</html>
    