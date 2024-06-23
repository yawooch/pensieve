<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<script src="${path}/js/main/settings.js"></script>
<div class="container">
      <div class="row">
        <div class="col-lg-12">
            <div class="card mb-3">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1">Settings</h5>
                  </div>
              </div>
              <div class="card-body">
                  <div class="col-lg-12">
	                  <h5 class="mb-1">Category Set</h5>
                  </div>
              
                  <div class="col-lg-6">
                  </div>
              
              </div>
          </div>
        </div>
      </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    