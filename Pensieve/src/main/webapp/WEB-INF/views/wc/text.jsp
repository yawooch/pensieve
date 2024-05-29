<%@ page language="java" contentType="text/html;              charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"            prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"             prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"       prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<c:set var="path" value="${ pageContext.request.contextPath }" />

<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/masonry/">
<script src="https://cdn.jsdelivr.net/npm/masonry-layout@4.2.2/dist/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>
<script src="${path}/js/wc/text.js"></script>

<input type="hidden" name="currPage"  value="" />
<input type="hidden" id="contextPath" value="${path}" />
<button type="button" id="btnScrollTop" class="btn btn-primary btn-lg fade" style="z-index: 10; bottom: 30px; right: 30px; position: fixed;">
    <i class="bi bi-arrow-bar-up"></i>
</button>
<div class="modal fade" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="formModal">
                <div class="modal-header">
                    <h5 class="modal-title">new Memo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"></span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="mb-3"><input type="text" class="form-control" id="title"   name="title"   placeholder="제목" maxlength="17" /></div>
                    <div class="mb-3"><textarea rows="7" class="form-control" id="content" name="content" placeholder="내용을 입력하세요"></textarea></div>
                </div>

                <div class="accordion modalcard" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                Options
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                        <div class="accordion-body collapsed">
                            <div>
                                <label for="formFile" class="form-label">파일 첨부</label>
                                <input class="form-control" type="file" id="formFile">
                            </div>
                            <div>
                                <label class="form-check-label mt-4">TODO 설정</label>
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" value="Y" id="todo-check" name="todoYn">
                                    <label class="form-check-label" for="todo-check">Todo Memo</label>
                                </div>
                                <div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text"><i class="bi bi-calendar"></i></span>
                                        <input  type="text"   class="form-control" aria-label="Amount (to the nearest dollar)" aria-describedby="button-addon2" readOnly>
                                        <button type="button" class="btn btn-primary"id="button-addon2">Clear</button>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <label for="category" class="form-label mb-3">카테고리 선택</label>
                                <select class="form-select" id="category" name="category">
                                    <option value="전체"  >전체  </option>
                                    <option value="일정"  >일정  </option>
                                    <option value="정보"  >정보  </option>
                                    <option value="기념일">기념일</option>
                                </select>
                            </div>
                            <div>
                                <label for="" class="form-label mb-3">카드 색상 선택</label>
                                <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                                    <input type="radio" class="btn-check"   name="btnradio" id="btnradio0" autocomplete="off" checked>
                                    <label class="btn btn-outline-success"   for="btnradio0">default</label>
                                    <input type="radio" class="btn-check"   name="btnradio" id="btnradio1" autocomplete="off" checked>
                                    <label class="btn btn-outline-primary"   for="btnradio1">primary</label>
                                    <input type="radio" class="btn-check"   name="btnradio" id="btnradio2" autocomplete="off" checked>
                                    <label class="btn btn-outline-secondary" for="btnradio2">secondary</label>
                                    <input type="radio" class="btn-check"   name="btnradio" id="btnradio3" autocomplete="off" checked>
                                    <label class="btn btn-outline-info"      for="btnradio3">info</label>
                                    <input type="radio" class="btn-check"   name="btnradio" id="btnradio4" autocomplete="off" checked>
                                    <label class="btn btn-outline-warning"   for="btnradio4">warning</label>
                                </div>
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
        <div class="d-flex gap-2 justify-content-center pb-5">
            <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size: 3em;" data-bs-toggle="modal" data-bs-target="#myModal">
                <i class="bi bi-plus-lg"></i>
            </button>
            <%--                 <a href="${path}/wc/createCard" style="font-size:2em;"><i class="bi bi-plus-circle-fill"></i></a> --%>
            <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size: 3em;" data-bs-toggle="collapse" data-bs-target="#searchBox">
                <i class="bi bi-search"></i>
            </button>
            <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size: 3em; background-color: #888; border-color: #888;">
                <i class="bi bi-mic"></i>
            </button>
        </div>
    </div>

    <div class="row collapse" id="searchBox">
        <div class="d-flex gap-2 justify-content-center pb-5">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Search Keywords" aria-label="Recipient's username" aria-describedby="button-addon2">
                <button class="btn btn-primary btn-lg" type="button" id="button-addon2">Search</button>
            </div>
        </div>
    </div>
    
    <div class="row" id="cardList" data-masonry='{"percentPosition": true, "columnWidth" : ".col-lg-4", "itemSelector" :".col-lg-4"}'>
    </div>
    
    <div class="row fade show m-3" id="loadingCircle" style="display: block;">
        <hr>
        <div class="d-flex justify-content-center">
            <div class="spinner-border text-warning" style="width: 3em; height: 3em; border-width: 0.6em;"></div>
        </div>
    </div>
    <div class="row m-3" id="endOfMemories" style="display: none;">
        <hr>
        <div class="d-flex gap-2 justify-content-center pb-5" style="font-size: 3em;">
            <i class="bi bi-x-circle-fill"></i>
        </div>
    </div>
</div>
<p id="sentinel"></p>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />