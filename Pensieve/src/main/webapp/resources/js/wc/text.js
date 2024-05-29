$(document).ready(()=>
{
    $('#saveText').on('click',()=>
    {

        let contextPath = $('#contextPath').val();
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
          url         : contextPath +'/wc/memoryInsert',
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
    function loadMemories()
    {
        let currPage = $('input[name=currPage]').val();
        let contextPath = $('#contextPath').val();
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
          url         : contextPath + '/wc/text/memorySelect',
        //   url         : '/memorySelect',
        // url         : '/wc/text/memorySelect',
          type        : 'POST',
          data        : data,
          beforesend: function () {
    //          $('#loadingCircle').show();
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

    window.addEventListener('scroll', function(ele)
    {
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
    

    //스크롤을 상단으로 이동시키는 함수
    $('#btnScrollTop').on('click',()=>
    {
        window.scrollTo({
            top:0,
            behavior:'smooth'
    });
    });
    
});

function editMemory(memoryId)
{
    //memoryId)' + contextPath +'/wc/memoryModify/?memoryId='+ oneMemory.memoryId +'
    console.log('memoryId : ' + memoryId);
    $('#myModal').modal('show');   
}

// document ready nd
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

    let contextPath = $('#contextPath').val();
    let cardParentStr = '';//카드의 최상단 요소를 만들어주는 문자열을 만든다.
    cardParentStr += '<div class="col-lg-4 mb-3" memory-data="'+ oneMemory.memoryId +'">';
    cardParentStr += '    <div class="card' + (oneMemory.todoYn != "N"?' border-secondary':'') + '">';
    cardParentStr += '    </div>';
    cardParentStr += '</div>';
    
    let cardHeaderStr = ''; //카드 헤더 요소를 만들어주는 문자열을 만든다.
    cardHeaderStr += '      <div class="card-header mb-1 ">'; 
    cardHeaderStr += '          <div class="d-flex w-100 justify-content-between">';
    cardHeaderStr += '              <h5 class="mb-1">'+ (oneMemory.title == undefined?'':oneMemory.title) +'</h5>';
    if(oneMemory.todoYn != "N")
    {
        cardHeaderStr += '<div class="d-flex justify-flex-end">';
        cardHeaderStr += '<div><input class="form-check-input" type="checkbox" value="Y" id="todo-check"></div>';
    }
    cardHeaderStr += '              <a id="btnGroupDrop'+ oneMemory.memoryId +'" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
    cardHeaderStr += '                  <i class="bi bi-three-dots-vertical"></i>';
    cardHeaderStr += '              </a>';
    cardHeaderStr += '              <div class="dropdown-menu" aria-labelledby="btnGroupDrop'+ oneMemory.memoryId +'">';
    cardHeaderStr += '                <a class="dropdown-item" href="javascript:editMemory(' + oneMemory.memoryId +');">수정</a>';
    cardHeaderStr += '                <a class="dropdown-item" href="' + contextPath +'/wc/memoryDelete/?memoryId='+ oneMemory.memoryId +'">삭제</a>';
    cardHeaderStr += '              </div>';
    if(oneMemory.todoYn != "N")
    {
        cardHeaderStr += '</div>';
    }
    cardHeaderStr += '          </div>';
    cardHeaderStr += '      </div>';
    cardHeaderStr += '      <input type="hidden" name="contentOrig" value="'+ oneMemory.contentOrig +'">';
    
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

    let targetDate  = oneMemory.modifyDate==null? oneMemory.createDate:oneMemory.modifyDate;
    let date        = new Date(targetDate);
    let offset      = date.getTimezoneOffset() * 60000;
    let dateOffset  = new Date(date.getTime() - offset);
    let convertDate = dateOffset.toISOString().replace("T", " ").replace(/\..*/, '');
    convertDate = convertDate.split(':')[0] + ':' + convertDate.split(':')[1];
    
    let cardFooterStr = ''; //카드 푸터 요소를 만들어주는 문자열을 만든다.
    cardFooterStr += '      <div class="card-footer text-muted">';
    cardFooterStr += convertDate;//yyyy-mm-dd hh:mi
    cardFooterStr += '      </div>';

    let cardParent = $(cardParentStr);
    cardParent.find('div.card').append(cardHeaderStr);
    cardParent.find('div.card').append(cardBodyStr);
    cardParent.find('div.card').append(cardFooterStr);
    
    let cardEle = cardParent;

    return cardEle;
}