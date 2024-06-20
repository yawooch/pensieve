<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
 
<link href="${path}/css/bootstrap-treeview.css"  rel="stylesheet"/>
<script src="${path}/js/bootstrap/bootstrap-treeview.js"></script>
<script>

var defaultData = [
    {
      text: 'Parent 1',
      href: '#parent1',
      tags: ['4'],
      nodes: [
        {
          text: 'Child 1',
          href: '#child1',
          tags: ['2'],
          nodes: [
            {
              text: 'Grandchild 1',
              href: '#grandchild1',
              tags: ['0']
            },
            {
              text: 'Grandchild 2',
              href: '#grandchild2',
              tags: ['0']
            }
          ]
        },
        {
          text: 'Child 2',
          href: '#child2',
          tags: ['0']
        }
      ]
    },
    {
      text: 'Parent 2',
      href: '#parent2',
      tags: ['0']
    },
    {
      text: 'Parent 3',
      href: '#parent3',
       tags: ['0']
    },
    {
      text: 'Parent 4',
      href: '#parent4',
      tags: ['0']
    },
    {
      text: 'Parent 5',
      href: '#parent5'  ,
      tags: ['0']
    }
  ];
$(document).ready(()=>
{
// 	$('#treeview2').treeview({
//         levels: 1,
//         data: defaultData,
//         onNodeSelected: function(event, node) {
//           $('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
//         },
//         onNodeUnselected: function (event, node) {
//           $('#selectable-output').prepend('<p>' + node.text + ' was unselected</p>');
//         }
//       });
	var initSelectableTree = function() {
        return $('#treeview2').treeview({
          levels: 1,
          data: defaultData,
          onNodeSelected: function(event, node) {
            $('#selectable-output').prepend('<p>' + node.text + ' was selected</p>');
          },
          onNodeUnselected: function (event, node) {
            $('#selectable-output').prepend('<p>' + node.text + ' was unselected</p>');
          }
        });
      };

      var $selectableTree = initSelectableTree();
});

</script>
    
<div class="modal fade" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">new Memo</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
          <span aria-hidden="true"></span>
        </button>
      </div>
      <div class="modal-body">
        <form>

        <div class="mb-3">
          <input type="text" class="form-control" id="content" name="content" rows="3" placeholder="제목"/>
        </div>
        <div class="mb-3">
          <textarea class="form-control" id="content" name="content" rows="3" placeholder="내용을 입력하세요"></textarea>
        </div>
        
        </form>
      </div>
      <div class="modal-footer" style="justify-content: space-between;">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" value="Y" id="todo-check">
          <label class="form-check-label" for="todo-check">
            Todo Memo
          </label>
        </div>
        <div>
	        <button type="button" class="btn btn-primary">저장</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container">
      <div class="row">
            <div class="d-flex gap-2 justify-content-center pb-5">
                <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size:3em;"  data-bs-toggle="modal" data-bs-target="#myModal" ><i class="bi bi-plus-lg"></i></button>
<%--                 <a href="${path}/wc/createCard" style="font-size:2em;"><i class="bi bi-plus-circle-fill"></i></a> --%>
                <button class="bi btn btn-primary rounded-circle p-3 lh-1" type="button" style="font-size:3em; background-color:#888; border-color:#888;"><i class="bi bi-mic"></i></button>
            </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
            <div class="card mb-3">
              <div class="card-header mb-1">
	              <div class="d-flex w-100 justify-content-between">
	                  <h5 class="mb-1">Menu setting</h5>
	              </div>
              </div>
              <div class="card-body">
              
              <div class="col-lg-4">
                <div id="treeview2" class=""></div>
              </div>
              
	        <div class="col-lg-4">
	          <h2>Events</h2>
	          <div id="selectable-output"></div>
	        </div>
              
              
              </div>
              <div style="text-align:center;"><img src="${path}/img/Pensieve.webp" width="100%"/></div>
              <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              </div>
              <ul class="list-group list-group-flush">
                <li class="list-group-item">Cras justo odio</li>
                <li class="list-group-item">Dapibus ac facilisis in</li>
                <li class="list-group-item">Vestibulum at eros</li>
              </ul>
              <div class="card-body">
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                2 days ago
              </div>
          </div>
        </div>
          
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
	              <div class="d-flex w-100 justify-content-between">
	                  <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
	              </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title1</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                3 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
	              <div class="d-flex w-100 justify-content-between">
	                  <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
	              </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title2</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                4 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title3</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                5 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card">
              <div class="card-header mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1"></h5>
                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bi bi-three-dots-vertical"></i>
                      </a>
                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title4</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
              <div style="text-align:center;"><img src="${path}/img/Pensieve.webp" width="100%"/></div>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted">
                6 days ago
              </div>
            </div>
        </div>
        <div class="col-lg-4 mb-3">
            <div class="card border-secondary">
              <div class="card-header border-secondary mb-1">
                  <div class="d-flex w-100 justify-content-between">
                      <h5 class="mb-1">Todo Memo</h5>
                      
                      <div class="d-flex justify-flex-end">
<!--                       <div> -->
	                      <div>
		                      <input class="form-check-input" type="checkbox" value="Y" id="todo-check">
	                      </div>
	                      <a id="btnGroupDrop1" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                          <i class="bi bi-three-dots-vertical"></i>
	                      </a>
	                      <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
	                        <a class="dropdown-item" href="#">수정</a>
	                        <a class="dropdown-item" href="#">삭제</a>
	                      </div>
                      </div>
                  </div>
              </div>
              <div class="card-body">
                <h4 class="card-title">Card title5</h4>
                <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                <a href="#" class="card-link">Card link</a>
                <a href="#" class="card-link">Another link</a>
              </div>
              <div class="card-footer text-muted align-right">
                7 days ago
              </div>
            </div>
        </div>
      </div>
      <div class="row m-3">
      <hr>
            <div class="d-flex gap-2 justify-content-center pb-5">
                <div class="spinner-border text-warning" style="width:3em;height:3em;border-width:0.6em;"></div>
            </div>
      </div>
      <div class="row m-3">
      <hr>
            <div class="d-flex gap-2 justify-content-center pb-5" style="font-size:3em;">
                <i class="bi bi-x-circle-fill"></i>
            </div>
      </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    