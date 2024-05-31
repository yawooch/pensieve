var varMemories = new Map();

const vanillaOptions = {
    type: 'default',
    input: true,
    actions: {
        clickDay(event, self)
        {
            let targetId = $(self.HTMLInputElement).attr('id');
            $('#'+ targetId +',#'+ targetId +'Hidden').val(self.selectedDates);

            self.hide();
        },
    },
    settings: {
        visibility: {
            positionToInput: 'center',
        },
        lang: 'ko',
    },
  };

$(document).ready(()=>
{
    /***********************  모달 관련 이벤트 시작 **********************/
    //modal Save 버튼 클릭시
    $('#saveText').on('click',saveModalMemory);
    //모달이 닫힐때마다 form 안의 내용을 초기화 한다
    $("#myModal").on('hide.bs.modal', closeSetting);
    
    new VanillaCalendar('#fromDate',vanillaOptions).init();
    new VanillaCalendar('#toDate',vanillaOptions).init();
        
    //모달창 Date Clear
    $('button[id^=btnClear]').on('click', (event)=>
    {
        $(event.target).prevAll().val('');
    });
    //모달창 TodoYn 체크 이벤트
    $('#todoYnCheck').on('change',todoYnToggle);
    /***********************  모달 관련 이벤트 끝   **********************/

    /***********************  Memory 관련 이벤트 시작 **********************/
    var intersectionObserver = new IntersectionObserver(setOpserveAction);
    // 주시 시작
    intersectionObserver.observe(document.querySelector('#sentinel'));
    loadMemories();
    /***********************  Memory 관련 이벤트 끝   **********************/

    
    /***********************  Scroll 관련 이벤트 시작 **********************/
    //스크롤을 내리면 scrollTop 버튼이 생성된다.
    $(document).on('scroll', showBtnScroll);
    
    //스크롤을 상단으로 이동시키는 함수
    $('#btnScrollTop').on('click',btnScrollTop);
    /***********************  Scroll 관련 이벤트 끝   **********************/

    /***********************  검색 관련 이벤트 시작 **********************/
    function searchEvent(event)
    {
        console.log(event);
        // let reg = /[가-힣ㄱ-ㅎa-zA-Z0-9~!@#$%^&*()_+|\\,.\/;'":]\[\]\{\}/gi;
        // let data = event.data===null?'':event.data;
        // let word = data.replace(reg,'');
        // if (word === '') { return; }

        if(event.key !== 'Enter')
        {
            return ;
        }
        
        // $('#cardList').remove();
        let masonryEle = $('#cardList').masonry({
            percentPosition: true,
            columnWidth    : '.col-lg-4',
            itemSelector   : '.col-lg-4'
        });

        //카드 수정시, 기존 수정전 카드는 제거
        $.each($('#cardList').find('.col-lg-4'),(idx, ele)=>
        {
            masonryEle.masonry('remove', $(ele));
        });
        $('input[name=currPage]').val('');


        // createDelay(loadMemories, 1000);
        loadMemories();
    }
    const createDelay = (callback, ms)=>
    {
        let instance;
        console.log(instance);
        return ()=>
        {
            clearTimeout(instance);
            instance = setTimeout(callback, 1000);
        }
    }
    // document.getElementById('searchInput').addEventListener('keydown', searchEvent);
    // document.getElementById('searchInput').addEventListener('keypress', searchEvent);
    document.getElementById('searchInput').addEventListener('keyup', searchEvent);
    // document.getElementById('searchInput').addEventListener('input', searchEvent);
    // $("#searchInput").on("keypress", searchEvent);
    // $("#searchInput").on("keyup", searchEvent);
    // $("#searchInput").on("keydown", searchEvent);

    /***********************  검색 관련 이벤트 끝   **********************/
});
function saveModalMemory()
{
    let contextPath = $('#contextPath').val();
    let memoryId  = $('input[name=memoryId]').val();
    let title     = $('#title').val();
    let content   = $('#content').val();
    let todoYn    = $('#todoYnCheck:checked').val();
    let category  = $('#category>option:selected').val();
    let strDate   = $('#fromDateHidden').val();
    let endDate   = $('#toDateHidden').val();
    
    //Date 입력시 validation 검사
    //Todo에서 strDate만 있는건 없다는 가정하에 validation진행
    if((endDate === '' && strDate !== '') || (new Date(endDate).getTime() >= new Date(strDate).getTime()))
    {
        let tempDate = strDate;
        strDate = endDate;
        endDate = tempDate;
    }

    if(content.trim() === '')
    {
        alert('저장할 내용이 없습니다.');
        $('#myModal').modal('hide');
        return ;
    }

    let datas = 
    {
        memoryId,
        title,
        content,
        todoYn,
        category,
        strDate,
        endDate
    };
    
    if(content == '' && title == '')
    {
        alert('빈값이 입력되었습니다. 저장되지 않았습니다.');
        $('#myModal').modal('hide');
    }
    $.ajax({
        url         : contextPath +'/wc/text/memorySave',
        type        : 'POST',
        dataType    : 'json',
        contentType : 'application/json;charset=utf-8',
        data        : JSON.stringify(datas),
        success:function(data)
        {
            varMemories.set(data.memory.memoryId, data.memory);
            addCardMemory('prepend', data.memory);
            $('#myModal').modal('hide');
        },
        error:function(data)
        {
            alert('저장에 실패하였습니다.');
            $('#myModal').modal('hide');
        }
    });
}
//memory 를 가져온다.
function loadMemories()
{
    let currPage = $('input[name=currPage]').val();
    let searchWord = $('input[name=searchWord]').val();
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
        currPage     : currPage,
        searchWord   : searchWord
    };
    
    $.ajax({
      url         : contextPath + '/wc/text/memorySelect',
      type        : 'POST',
      data        : data,
      beforesend: function () {
          $('#sentinel').show();
          $('#loadingCircle').show();
          $('#endOfMemories').hide();
      },
      success:function(data)
      {
        let memories      = data.memories;
        let totalMemories = data.totalMemories;
          
        memories.forEach((memory)=>
        {
            varMemories.set(memory.memoryId, memory);
        });

        if (totalMemories === 0) {
                $('#endOfMemories').show();
                //검색된 아이템이 없을 경우 관찰중인 요소를 숨긴다.
                $('#sentinel').hide();
        }
        else 
        {
            if (totalMemories <= currPage*3){
                //검색된 아이템이 최대카드개수 이하일 경우 관찰중인 요소를 숨긴다.
                $('#sentinel').hide();
                $('#loadingCircle').hide();
                $('#endOfMemories').show();
            }
            else {
                    $('#sentinel').show();
                    $('#loadingCircle').show();
                    $('#endOfMemories').hide();
            }
        }
        memories.forEach((memory)=>
        {
            addCardMemory('append', memory);
        });
        },
        error:function(data){
            $('#loadingCircle').hide();
        }
    });
};

// document ready nd
function addCardMemory(addEleStr, oneMemory)
{
    let cardEle    = cardMemoryMaker(oneMemory);
    let masonryEle = $('#cardList').masonry({
        percentPosition: true,
        columnWidth    : '.col-lg-4',
        itemSelector   : '.col-lg-4'
    });

    //카드 수정시, 기존 수정전 카드는 제거
    $.each($('#cardList').find('.col-lg-4'),(idx, ele)=>
    {
        if(cardEle.attr('memory-data') === $(ele).attr('memory-data'))
        {
            masonryEle.masonry('remove', $(ele));
        }
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
    
    let cardColor = '';
    
    if(oneMemory.todoYn == "Y"&& oneMemory.todo.succDate === null)
    {
        cardColor = ' border-secondary';
    }
    else if(oneMemory.todoYn == "Y" &&  oneMemory.todo.succDate !== null)
    {
        cardColor = ' border-success';
    }
    else
    {
        cardColor = ' border-' + oneMemory.category;
    }
    
    cardParentStr += '<div class="col-lg-4 mb-3" memory-data="'+ oneMemory.memoryId +'">';
    cardParentStr += '    <div class="card' + cardColor + '">';
    cardParentStr += '    </div>';
    cardParentStr += '</div>';
    
    let cardHeaderStr = ''; //카드 헤더 요소를 만들어주는 문자열을 만든다.
    cardHeaderStr += '      <div class="card-header mb-1 ">'; 
    cardHeaderStr += '          <div class="d-flex w-100 justify-content-between">';
    cardHeaderStr += '              <h5 class="mb-1">'+ (oneMemory.title == undefined?'':oneMemory.title) +'</h5>';
    if(oneMemory.todoYn != "N")
    {
        cardHeaderStr += '<div class="d-flex justify-flex-end">';
        cardHeaderStr += '<div><input class="form-check-input" type="checkbox" value="' + oneMemory.memoryId + '" name="todoCheck" '+ (oneMemory.todo.succDate!=null?'checked':'') +'></div>';
    }
    cardHeaderStr += '              <a id="btnGroupDrop'+ oneMemory.memoryId +'" type="button" class="text-secondary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
    cardHeaderStr += '                  <i class="bi bi-three-dots-vertical"></i>';
    cardHeaderStr += '              </a>';
    cardHeaderStr += '              <div class="dropdown-menu" aria-labelledby="btnGroupDrop'+ oneMemory.memoryId +'">';
    cardHeaderStr += '                <a class="dropdown-item" href="javascript:editMemory(' + oneMemory.memoryId +');">수정</a>';
    cardHeaderStr += '                <a class="dropdown-item" href="' + contextPath +'/wc/text/memoryDelete/?memoryId='+ oneMemory.memoryId +'">삭제</a>';
    cardHeaderStr += '              </div>';
    if(oneMemory.todoYn != "N")
    {
        cardHeaderStr += '</div>';
    }
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

    let editDate  = oneMemory.modifyDate==null? oneMemory.createDate:oneMemory.modifyDate;
    let convertDate = parseDate(editDate, 'summary');
    
    let cardFooterStr = ''; //카드 푸터 요소를 만들어주는 문자열을 만든다.
    cardFooterStr += '      <div class="card-footer text-muted">';
    if(oneMemory.todoYn =='Y')
    {
        cardFooterStr += '      <div>';
        if(oneMemory.todo.strDate!==null)
        {
            cardFooterStr += '      <span>' + parseDate(oneMemory.todo.strDate, 'short') + '</span>';//yyyy-MM-dd HH:mm
        }
        if(oneMemory.todo.endDate!==null)
        {
            cardFooterStr += '      ~ <span>' + parseDate(oneMemory.todo.endDate, 'short') + '</span>';//yyyy-MM-dd HH:mm
        }
        cardFooterStr += '      </div>';
    }
    cardFooterStr += '      <div> '+ (oneMemory.modifyDate==null? 'created at: ':'modified at: ') + convertDate + '</div>';//yyyy-MM-dd HH:mm
    cardFooterStr += '      </div>';

    let cardParent = $(cardParentStr);
    cardParent.find('div.card').append(cardHeaderStr);
    cardParent.find('div.card').append(cardBodyStr);
    cardParent.find('div.card').append(cardFooterStr);
    
    let cardEle = cardParent;

    cardEle.find('input[name=todoCheck]').on('change', checkTodoFunc);

    return cardEle;
}


function setOpserveAction(entries, observer)
{
    entries.forEach((entry)=>
    {
        // intersectionRatio가 0이라는 것은 대상을 볼 수 없다는 것이므로
        // 아무것도 하지 않음
        if (entry.intersectionRatio <= 0) return;

        observer.observe(document.querySelector('#sentinel'));
        
        loadMemories();
    });
}

//모달이 닫힐때 발생하는 이벤트
function closeSetting()
{
    $('#formModal')[0].reset();
    $('#collapseOne').collapse('hide');
}
//스크롤을 내리면 scrollTop 버튼이 생성된다.
function showBtnScroll()
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
}
//스크롤을 상단으로 이동시키는 함수
function btnScrollTop()
{
    window.scrollTo({
        top:0,
        behavior:'smooth'
    });
}

//모달창 TodoYn 체크 이벤트
function todoYnToggle(event)
{
    let target = $(event.target);

    if(target.prop('checked'))
    {
        $('#fromDate, #fromDateHidden, #toDate, #toDateHidden').prop('disabled',false);
        $('#btnClearTo, #btnClearFrom').prop('disabled',false);
    }
    else
    {
        $('#fromDate, #fromDateHidden, #toDate, #toDateHidden').val('');
        $('#fromDate, #fromDateHidden, #toDate, #toDateHidden').prop('disabled',true);
        $('#btnClearTo, #btnClearFrom').prop('disabled',true);
    }
}

function editMemory(memoryId)
{
    let memory = varMemories.get(memoryId);
    
    $('input[name=memoryId]').val(memory.memoryId);
    $('input[name=title]').val(memory.title);
    $('textarea[name=content]').val(memory.contentOrig);
    $('select[name=category] option[value=' + memory.category +']').prop('selected',true);
    
    if(memory.todoYn == 'Y')
    {
        $('#todoYnCheck').prop('checked', true);
        $('#fromDate, #fromDateHidden, #toDate, #toDateHidden').prop('disabled',false);
        $('#btnClearTo, #btnClearFrom').prop('disabled',false);
        $('#fromDate, #fromDateHidden').val(parseDate(memory.todo.strDate, 'short'));
        $('#toDate, #toDateHidden').val(parseDate(memory.todo.endDate, 'short'));
        $('#collapseOne').collapse('show');
    }
    else
    {
        $('#todoYnCheck').prop('checked', false);
    }
    $('textarea[name=content]').val(memory.contentOrig);
    $('#myModal').modal('show');  
}

//date를 넣으면 yyyy-MM-dd HH:mm 형식으로 내보낸다.
function parseDate(targetDate, textLen)
{
    if(targetDate ===''|| targetDate ===null)
    {
        return '';
    }

    let date        = new Date(targetDate);
    let offset      = date.getTimezoneOffset() * 60000;
    let dateOffset  = new Date(date.getTime() - offset);
    let convertDate = dateOffset.toISOString().replace("T", " ").replace(/\..*/, '');
    convertDate = convertDate.split(':')[0] + ':' + convertDate.split(':')[1];
    
    if(textLen === 'summary')
    {
        // let diffDate   = (new Date().getTime() - dateOffset.getTime());
        // let minuteToTime  = 60000;
        // let hourToTime  = minuteToTime*60;
        // let dayToTime  = hourToTime*24;
        // let timesAgo = diffDate/hourToTime;

        // console.log(dateOffset.toISOString());
        // console.log(dateOffset.getTime());
        // console.log(diffDate);
        // if(timesAgo <= 0)
        // {
        //     timesAgo = Math.abs(diffDate/minuteToTime);
        //     return timesAgo + ' hours ago';
        // }
        // if(timesAgo >= 30)
        // {
        //     timesAgo = diffDate/dayToTime*24*30;
        //     return timesAgo + ' month ago';
        // }
        // return timesAgo + ' days ago';
        return convertDate;
    }

    if(textLen === 'short')
    {
        convertDate = convertDate.split(' ')[0];
    }

    return convertDate;
}

function checkTodoFunc(event)
{
    let contextPath = $('#contextPath').val();
    let target = $(event.target);
    let parentEle = target.parents('div.card');


    let datas = {
        memoryId      : target.val(),
        succDateBool  : target.prop('checked')
    };

    $.ajax({
    url         : contextPath +'/wc/text/checkTodo',
    type        : 'POST',
    dataType    : 'json',
    contentType : 'application/json;charset=utf-8',
    data        : JSON.stringify(datas),
    success:function(data)
    {
        console.log(data);
        let memory = varMemories.get(data.memoryId);
        console.log(memory.todo.succDate, data.succDate);
        memory.todo.succDate = data.succDate;
        varMemories.set(memory.memoryId, memory);
    }           
    });

    if(target.prop('checked'))
    {
        parentEle.removeClass('border-secondary');
        parentEle.addClass('border-success');
    }
    else
    {
        parentEle.removeClass('border-success');
        parentEle.addClass('border-secondary');
    }
}