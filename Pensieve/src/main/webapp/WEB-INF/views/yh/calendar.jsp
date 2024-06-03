<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <script src="${path}/js/yh/calendar.js"></script>
<style>
  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>
    <div id='calendar'></div>
    
       <!-- modal 추가 -->
            <div class="modal fade" id="calendarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">일기 추가하기</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="taskId" class="col-form-label">일기 제목</label>
                            <input type="text" class="form-control" id="calendar_title" name="calendar_title">
                            <label for="taskId" class="col-form-label">날짜</label>
                            <input type="date" class="form-control" id="calendar_start_date" name="calendar_start_date">
                            <label for="taskId" class="col-form-label">일기 내용</label>
                            <input type="text" class="form-control" id="calendar_content" name="calendar_content">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" id="addCalendar">추가</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                            id="calendarModalClose">취소</button>
                    </div>

                </div>
            </div>
          </div>
</body>
</html>
