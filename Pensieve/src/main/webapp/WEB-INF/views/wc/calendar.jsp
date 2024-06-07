<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
.fc-bg-event>.fc-event-title{
    color: red;
}

</style>
<script src="${path}/js/wc/calendar.js"></script>

<input type="hidden" id="contextPath" value="${path}" />
<div class="modal fade" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formModal">
                <input type="hidden" name="memoryId" value=""/>
                <div class="modal-header">
                    <h5 class="modal-title">new Memo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="mb-3"><textarea rows="7" class="form-control" id="content" name="content" placeholder="내용을 입력하세요" maxlength="30"></textarea></div>
                </div>

                <div class="accordion modalcard" id="accordionOptions">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                Options
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionOptions">
                        <div class="accordion-body collapsed">
                            <div>
                                <label for="category" class="form-label mb-3">카테고리 선택</label>
                                <select class="form-select" id="category" name="category">
                                    <option value="primary"   class="bg-primary text-white" selected>일정  </option>
                                    <option value="warning"   class="bg-warning text-white">기념일</option>
                                    <option value="info"      class="bg-secondary text-white"   >Todo</option>
                                </select>
                            </div>
                            <div>
                                <label for="repeatPeriod" class="form-label mb-3 mt-3">반복</label>
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" value="Y" id="repeatYn" name="repeatYn">
                                    <label class="form-check-label" for="repeatYn">반복 사용 여부</label>
                                </div>
                                <select class="form-select" id="repeatPeriod" name="repeatPeriod" disabled>
                                    <option value="year"   selected>매년</option>
                                    <option value="month"          >매달</option>
                                    <option value="day"            >매일</option>
                                    <option value="pointing" disabled>x일</option>
                                </select>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary"   id="saveText">저장</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container">
      <div class="row">
        <div class="col-lg-12">
            <div id="calendar"></div>
        </div>
      </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    