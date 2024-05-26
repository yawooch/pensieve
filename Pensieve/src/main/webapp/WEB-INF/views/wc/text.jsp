<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/masonry/">
<script src="https://cdn.jsdelivr.net/npm/masonry-layout@4.2.2/dist/masonry.pkgd.min.js" integrity="sha384-GNFwBvfVxBkLMJpYMOABq3c+d3KnQxudP/mGPkzpZSTYykLBNsZEnG2D9G/X/+7D" crossorigin="anonymous" async></script>
 
<input type="hidden" name="currPage" value=""/>
<button type="button" id="btnScrollTop" class="btn btn-primary btn-lg fade" style="bottom: 30px; right: 120px; position: fixed;">
    <i class="bi bi-arrow-bar-up"></i>
</button>
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
        <form id="formModal">

        <div class="mb-3">
          <input type="text" class="form-control" id="title" name="title" placeholder="제목" maxlength="17"/>
        </div>
        <div class="mb-3">
          <textarea class="form-control" id="content" name="content" rows="3" placeholder="내용을 입력하세요"></textarea>
        </div>
        
        </form>
      </div>
      <div class="modal-footer" style="justify-content: space-between;">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" value="Y" id="todo-check" name="todoYn">
          <label class="form-check-label" for="todo-check">
            Todo Memo
          </label>
        </div>
        <div>
	        <button type="button" class="btn btn-primary" id="saveText">저장</button>
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
      <div class="row"  id="cardList" data-masonry='{"percentPosition": true, "columnWidth" : ".col-lg-4", "itemSelector" :".col-lg-4"}'>
        
      </div>
      <div class="row fade show m-3" id="loadingCircle" style="display:block;">
      <hr>
            <div class="d-flex gap-2 justify-content-center pb-5">
                <div class="spinner-border text-warning" style="width:3em;height:3em;border-width:0.6em;"></div>
            </div>
      </div>
      <div class="row m-3" id="endOfMemories" style="display:none;">
      <hr>
            <div class="d-flex gap-2 justify-content-center pb-5" style="font-size:3em;">
                <i class="bi bi-x-circle-fill"></i>
            </div>
      </div>
</div>

<p id="sentinel"></p>

<script>
function loadMemories()
{
	let currPage = $('input[name=currPage]').val();
	
	//첫 로딩시는 빈값으로 준다.
	if(currPage == '')
	{
		currPage = 0;
		$('input[name=currPage]').val(currPage);
	}
	++ currPage;
    $('input[name=currPage]').val(currPage);
    
	
    let data = {
    		currPage     : currPage
    };
    
    $.ajax({
      url         : '${path}/wc/text/memorySelect',
      type        : 'POST',
      data        : data,
      beforesend: function () {
//    	    $('#loadingCircle').show();
          $('#sentinel').show();
      },
      success:function(data)
      {
    	  let memories = data.memories;
    	  let totalMemories = data.totalMemories;
    	  
          if (totalMemories === 0) {
//                 $('#loadingCircle').hide();
                $('#endOfMemories').show();
        	    $('#sentinel').hide();
        	    //검색된 아이템이 없을 경우 관찰중인 요소를 숨긴다.
          }
          else 
          {
              if (totalMemories <= currPage*3){
               //검색된 아이템이 20개 이하일 경우 관찰중인 요소를 숨긴다.
               $('#sentinel').hide();
                $('#loadingCircle').hide();
                $('#endOfMemories').show();
           }
           else {
                $('#sentinel').show();
//                   $('#loadingCircle').hide();
           }
       }
    	  memories.forEach((memory, idx)=>
    	  {
    		  addCardMemory('append', memory);
    	  });
      },
      error:function(data){
    	  console.log('오류발생');
          $('#loadingCircle').hide();
      },
      complete: function () {
//           $('#loadingCircle').hide();
      }
    });
};

loadMemories();

$('#saveText').on('click',()=>
{
    let title     = $('#title').val();
    let content   = $('#content').val();
    let todoYn    = $('#todo-check:checked').val();
    
    let datas = {
            title     : title,
            content   : content,
            todoYn    : todoYn
          };
    
    if(content == '' && title == '')
    {
        alert('빈값이 입력되었습니다. 저장되지 않았습니다.');
        $('#myModal').modal('hide');
    }
    $.ajax({
      url         : '${path}/wc/memoryInsert',
      type        : 'POST',
      dataType    : 'json',
      contentType : 'application/json;charset=utf-8',
      data        : JSON.stringify(datas),
      success:function(data)
      {
        addCardMemory('prepend', data.memory);
        $('#formModal')[0].reset();
        $('#myModal').modal('hide');
      },
      error:function(data)
      {
          alert('저장에 실패하였습니다.');
          $('#formModal')[0].reset();
          $('#myModal').modal('hide');
      }
    });
});

    function addCardMemory(addEleStr, oneMemory)
    {
        let cardEle = cardMemoryMaker(oneMemory)    ;
        
        let masonryEle = $('#cardList').masonry({
             percentPosition: true,
             columnWidth    : '.col-lg-4',
             itemSelector   : '.col-lg-4'
        });
        if(addEleStr=='append')
        {
            masonryEle.append(cardEle);//요소에 추가를 하고
            masonryEle.masonry('appended', cardEle);//다시 레이아웃 맞추는 기능을 수행
        }
        if(addEleStr=='prepend')
        {
            masonryEle.prepend(cardEle);//요소에 추가를 하고
            masonryEle.masonry('prepended', cardEle);//다시 레이아웃 맞추는 기능을 수행
        }
    }
    function cardMemoryMaker(oneMemory)
    {
        let cardParentStr = '';//카드의 최상단 요소를 만들어주는 문자열을 만든다.
        cardParentStr += '<div class="col-lg-4 mb-3" memory-data="'+ oneMemory.memoryId +'">';
        cardParentStr += '    <div class="card' + (oneMemory.todoYn != "N"?' border-secondary':'') + '">';
        cardParentStr += '    </div>';
        cardParentStr += '</div>';
        
        let cardHeaderStr = ''; //카드 헤더 요소를 만들어주는 문자열을 만든다.
        cardHeaderStr += '      <div class="card-header '+ (oneMemory.todoYn == "Y"?'border-secondary ':'') +'mb-1 ">'; 
        cardHeaderStr += '          <div class="d-flex w-100 justify-content-between">';
        cardHeaderStr += '              <h5 class="mb-1">'+ (oneMemory.title == undefined?'':oneMemory.title) +'</h5>';
        cardHeaderStr += '              <a id="btnGroupDrop'+ oneMemory.memoryId +'" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
        cardHeaderStr += '                  <i class="bi bi-three-dots-vertical"></i>';
        cardHeaderStr += '              </a>';
        cardHeaderStr += '              <div class="dropdown-menu" aria-labelledby="btnGroupDrop'+ oneMemory.memoryId +'">';
        cardHeaderStr += '                <a class="dropdown-item" href="${path}/wc/memoryModify/?memoryId="'+ oneMemory.memoryId +'">수정</a>';
        cardHeaderStr += '                <a class="dropdown-item" href="${path}/wc/memoryDelete/?memoryId="'+ oneMemory.memoryId +'">삭제</a>';
        cardHeaderStr += '              </div>';
        cardHeaderStr += '          </div>';
        cardHeaderStr += '      </div>';
        
        let cardBodyStr = '';//카드 바디 요소를 만들어주는 문자열을 만든다.
        cardBodyStr += '      <div class="card-body">';
        cardBodyStr += '        '+ oneMemory.content;
        cardBodyStr += '      </div>';
        
        
        let ulEle = '';
        let newUlList = '';
        if($(cardBodyStr).find('ul').length !== 0)
        {
            ulEle = $($(cardBodyStr).find('ul').prop("outerHTML"));
            
            ulEle.addClass('list-group list-group-flush');
            ulEle.find('li').addClass('list-group-item');
            
            newUlList = ulEle.prop("outerHTML");

        }
        let cardBodyEle = $(cardBodyStr); 
        cardBodyEle.find('ul').remove();//remove()를하면 제외된 ele를 반환한다
        cardBodyStr = cardBodyEle.prop("outerHTML");
        
        cardBodyStr += newUlList;

        let date       = new Date(oneMemory.createDate);
        let offset     = date.getTimezoneOffset() * 60000;
        let dateOffset = new Date(date.getTime() - offset);
        
        let cardFooterStr = ''; //카드 푸터 요소를 만들어주는 문자열을 만든다.
        cardFooterStr += '      <div class="card-footer text-muted">';
        cardFooterStr += '        '+ dateOffset.toISOString().replace("T", " ").replace(/\..*/, '');
        cardFooterStr += '      </div>';

        let cardParent = $(cardParentStr);
        cardParent.find('div.card').append(cardHeaderStr);
        cardParent.find('div.card').append(cardBodyStr);
        cardParent.find('div.card').append(cardFooterStr);
        
        let cardEle = cardParent;

        return cardEle;
    }
//     let entries = $('#cardList div.col-lg-4');
    
    var intersectionObserver = new IntersectionObserver((entries, observer)=>
    {
        entries.forEach((entry)=>
        {
            // intersectionRatio가 0이라는 것은 대상을 볼 수 없다는 것이므로
            // 아무것도 하지 않음
            if (entry.intersectionRatio <= 0) return;

            observer.observe(document.querySelector('#sentinel'));
            
            loadMemories();
        });
    });
    // 주시 시작
    intersectionObserver.observe(document.querySelector('#sentinel'));

    window.addEventListener('scroll', function(ele){
        let scrollY = window.scrollY;
    
    
        if(scrollY >= 200)
        {
            $('#btnScrollTop').addClass('show');
        }
        else
        {
            $('#btnScrollTop').removeClass('show');
        }
    });
    $('#btnScrollTop').on('click',()=>
    {
        window.scrollTo({
            top:0,
            behavior:'smooth'
    });
    });
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>