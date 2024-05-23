<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>

    <script src="${path}/js/wc/calendar.js"></script>
<div class="container">
      <div class="row">
        <div class="col-lg-12">
            <div class="card mb-3">
              <div class="card-body">
                <div id="calendar"></div>
              </div>
            </div>
        </div>
      </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    