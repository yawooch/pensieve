<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
 
<link href="${path}/css/bootstrap-treeview.css"  rel="stylesheet"/>
<script src="${path}/js/bootstrap/bootstrap-treeview.js"></script>
<script src="${path}/js/admin/settings.js"></script>
<div class="container">
      <div class="row">
        <div class="col-lg-12">
            <div class="card mb-3">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1">Menu setting</h5>
                  </div>
              </div>
              <div class="card-body" style="display:flex;flex-direction:row;">
              
                  <div class="col-lg-6">
                      <div class="btn-group mt-2 mb-2" role="group" aria-label="Basic radio toggle button group">
                          <input type="radio" class="btn-check" name="traversing" id="addChild" autocomplete="off">
                          <label class="btn btn-outline-primary" for="addChild">자식 메뉴 추가</label>
                          <input type="radio" class="btn-check" name="traversing" id="addSiblings" autocomplete="off">
                          <label class="btn btn-outline-primary" for="addSiblings">형제 메뉴 추가</label>
                      </div>
                      <input type="button" class="btn btn-primary" id="btnCollapse" value="Collapse All">
                      <div id="treeview2" class=""></div>
                      <div class="btn-group" role="group" aria-label="Basic checkbox toggle button group" style="display:none;">
                        <input type="checkbox" class="btn-check" id="chngFirst" autocomplete="off">
                        <label class="btn btn-primary mt-2" for="chngFirst">Change First Data</label>
                      </div>
                  </div>
              
                  <div class="col-lg-6 p-3">
                      <form id="menuForm">
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuId">MenuId</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="Menu Id" id="menuId" readonly>
                          </span>
                      </div>
                      
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuParentId">MenuParentId</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="Menu Parent Id" id="menuParentId" readonly>
                          </span>
                      </div>
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuLv">Menu Level</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="menuLv" id="menuLv" readonly>
                          </span>
                      </div>
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuUrl">Menu Url</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="Menu Url" id="menuUrl">
                          </span>
                      </div>
                      <div class="row">
                          <label class="col-3 col-form-label col-form-label-sm mt-1" for="menuName">Menu Name</label>
                          <span class="col-9">
                              <input type="text" class="form-control form-control-sm" placeholder="menuName" id="menuName">
                          </span>
                      </div>
                      <input type="button" class="btn btn-primary mt-3"  id="addMenu" value="추가" style="display:none;">
                      <div class="btn-group mt-3" style="display:none;" id="btnGroup">
                      <input type="button" class="btn btn-primary"  id="modMenu" value="수정">
                      <input type="button" class="btn btn-danger"   id="delMenu" value="삭제">
                      </div>
                       </form>
                  </div>
              </div>
          </div>
        </div>
      </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    