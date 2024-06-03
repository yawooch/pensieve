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
	  margin: 40px auto;
	}

</style>
<script src="${path}/js/jy/calendar.js"></script>

<div class="container">
  <div class="row">
    <div class="col-lg-12">
        <div class="card mb-3">
          <div class="card-body">
            <div id="calendar"></div>
            
            <!-- modal 추가 -->
            <div class="modal fade" id="calendarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="calendarModalLabel">일정을 입력하세요.</h5>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="taskId" class="col-form-label">일정 내용</label>
                            <input type="text" class="form-control" id="calendar_content" name="calendar_content">
                            <label for="taskId" class="col-form-label">시작 날짜</label>
                            <input type="date" class="form-control" id="calendar_start_date" name="calendar_start_date">
                            <label for="taskId" class="col-form-label">종료 날짜</label>
                            <input type="date" class="form-control" id="calendar_end_date" name="calendar_end_date">
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
        </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    