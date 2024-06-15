var varMemories = new Map();//불러온 기억들이 저장되는 Map()
var totalMemoryCount = 0;

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
    loadMemories();//스크롤 Observe 해결될때까진 일단 두번 실행시키쟈..
    /***********************  Memory 관련 이벤트 끝   **********************/

    /***********************  Scroll 관련 이벤트 시작 **********************/
    //스크롤을 내리면 scrollTop 버튼이 생성된다.
    $(document).on('scroll', showBtnScroll);

    //스크롤을 끝까지 내렸는데 아직 데이터가 다 나오지 않으면 기억을 불러온다.
    onscrollend = ()=>
    {
        let currPage = $('input[name=currPage]').val();

        if(totalMemoryCount >= (currPage*6))
        {
            loadMemories();
        }
    };
    
    //스크롤을 상단으로 이동시키는 함수
    $('#btnScrollTop').on('click',btnScrollTop);
    /***********************  Scroll 관련 이벤트 끝   **********************/

    /***********************  검색 관련 이벤트 시작 **********************/
    document.getElementById('searchInput').addEventListener('keyup', (event)=>
    {
        if(event.key !== 'Enter')
        {
            return ;
        }
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

        loadMemories();
    });

    /***********************  검색 관련 이벤트 끝   **********************/
});

function saveModalMemory()
{
    let memoryId  = $('input[name=memoryId]').val();
    let title     = $('#title').val();
    let content   = $('#content').val();
    let todoYn    = $('#todoYnCheck:checked').val();
    let category  = $('#category>option:selected').val();
    let strDate   = $('#fromDateHidden').val();
    let endDate   = $('#toDateHidden').val();
    
    //Date 입력시 validation 검사
    //Todo에서 strDate만 있는건 없다는 가정하에 validation진행
    if((endDate === '' && strDate !== '') || (new Date(endDate).getTime() < new Date(strDate).getTime()))
    {
        let tempDate = endDate;
        endDate = strDate;
        strDate = tempDate;
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

    let form = $('#formModal')[0];
    let formData = new FormData(form);

    //memorySaveAjax생각보다 많이 쓰이네
    memorySaveAjax(formData, ()=>
    {
        $('#myModal').modal('hide');
    });
}
//넘긴데이터 대로 WC_MEMORY에 저장한다.
function memorySaveAjax(data, completeFunction)
{
    let contextPath = $('#contextPath').val();

    $.ajax({
        url         : contextPath +'/wc/memorySave',
        type        : 'POST',
        contentType : false,
        processData : false,
        // dataType    : 'json',
        // data        : JSON.stringify(data),
        // contentType : 'application/json;charset=utf-8',
        data        : data,
        success:function(data, textStatus, jqXHR)
        {
            console.log('textStatus : ' + textStatus);
            if(jqXHR.getResponseHeader("REQUIRE_LOGIN") === 'true')
            {
                if(confirm('로그인이 필요한 기능입니다.\n로그인페이지로 가시겠습니까?'))
                {
                    location.href = path + '/login';
                }
            }
            else
            {
                varMemories.set(data.memory.memoryId, data.memory);
    
                addCardMemory('prepend', data.memory);
            }
        },
        error:function(data)
        {
            alert('저장에 실패하였습니다.');
        },
        complete:completeFunction
    });
}
//memory 를 가져온다.
function loadMemories()
{
    let currPage    = $('input[name=currPage]').val();
    let searchWord  = $('input[name=searchWord]').val();
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
        totalMemoryCount = data.totalMemories;
          
        memories.forEach((memory)=>
        {
            varMemories.set(memory.memoryId, memory);
        });

        if (totalMemoryCount === 0) {
                $('#endOfMemories').show();
                //검색된 아이템이 없을 경우 관찰중인 요소를 숨긴다.
                $('#sentinel').hide();
        }
        else 
        {
            if (totalMemoryCount <= currPage*6){
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
//카드형식으로 된 요소를 추가한다.
function cardMemoryMaker(oneMemory)
{
    let cardParent = $('<div></div>');
    cardParent.addClass('col-lg-4 mb-3');
    cardParent.attr('memory-data', oneMemory.memoryId);
    cardParent.append('<div class="card"></div>');
    
    let succDateStr = oneMemory.todoYn == "Y"?(oneMemory.todo===null?null:(oneMemory.todo.succDate===null?null:oneMemory.todo.succDate)):null;

    if(oneMemory.todoYn == "Y"&& succDateStr === null)
    {
        cardParent.find('div.card').css('border-color', '#f3969a');
    }
    else if(oneMemory.todoYn == "Y" &&  succDateStr !== null)
    {
        let chileStamp = $('<div></div>');
        chileStamp.addClass('missionStamp-complete');
        chileStamp.text('complete');
        chileStamp.prepend('<div>' + parseDate((oneMemory.todo.succDate),'long') + '</div>');
        cardParent.prepend(chileStamp.prop('outerHTML'));
        cardParent.find('div.card').css('border-color', '#56cc9d');
    }

    if(oneMemory.categoryColor !== 'default')
    {
        cardParent.find('div.card').css('border-color', oneMemory.categoryColor);
    }
    
    //카드 헤더 요소를 만들어주는 문자열을 만든다.
    let cardHeaderEle = $('<div class="card-header mb-1 "></div>');
    cardHeaderEle.css('border-color','inherit');
    cardHeaderEle.append('<div class="d-flex w-100 justify-content-between"></div>');
    cardHeaderEle.find('div.justify-content-between').append('<h5 class="mb-1">'+ (oneMemory.title == undefined?'':oneMemory.title) +'</h5>');
    
    let headerContent = $('<div></div>');
    let stringGrpId   = 'btnGroupDrop' + oneMemory.memoryId;

    headerContent.append('<a></a>');
    headerContent.find('a').append('<i class="bi bi-three-dots-vertical"></i>');
    headerContent.find('a').attr('id', stringGrpId);
    headerContent.find('a').attr('type', 'button');
    headerContent.find('a').attr('data-bs-toggle', 'dropdown');
    headerContent.find('a').attr('aria-haspopup', 'true');
    headerContent.find('a').attr('aria-expanded', 'false');
    headerContent.find('a').addClass('text-secondary');
    
    headerContent.append('<div class="dropdown-menu"></div>');
    headerContent.find('div.dropdown-menu').attr('aria-labelledby', stringGrpId);
    headerContent.find('div.dropdown-menu').append('<a href="javascript:editMemory('   + oneMemory.memoryId +');">수정</a>');
    headerContent.find('div.dropdown-menu').append('<a href="javascript:copyMemory('   + oneMemory.memoryId +');">복제</a>');
    headerContent.find('div.dropdown-menu').append('<a href="javascript:deleteMemory(' + oneMemory.memoryId +');">삭제</a>');
    headerContent.find('div.dropdown-menu>a').addClass('dropdown-item');

    let cardContentStr = '';
    
    if(oneMemory.todoYn != "N")
    {
        let todoEle = $('<div></div>');
        todoEle.addClass('d-flex justify-flex-end');
        todoEle.append('<div><input type="checkbox" '+ (succDateStr!=null?'checked':'') +'/></div>');
        todoEle.find('div>input').addClass('form-check-input');
        todoEle.find('div>input').attr('name', 'todoCheck');
        todoEle.find('div>input').val(oneMemory.memoryId);
        todoEle.append(headerContent.prop('innerHTML'));

        cardContentStr = todoEle.prop('outerHTML');
    }
    else
    {
        cardContentStr = headerContent.prop('innerHTML');
    }
    cardHeaderEle.find('div.justify-content-between').append(cardContentStr);


    let cardBodyFileList = '';
    //파일 정보가 있을때 처리
    if(oneMemory.memoryFiles.length != 0)
    {
        cardBodyFileList += '<p>';
        for (let file of oneMemory.memoryFiles) {
            cardBodyFileList += '<img src="'+ $('#contextPath').val() +'/img/upload/wc/memo/'+ file.fileReName +'" alt="'+ file.fileOrigName +'">';
        }
        cardBodyFileList += '</p>';
    }
    cardBodyFileList += oneMemory.content;
        
    //파일 정보가 있을때 처리
    if(oneMemory.memoryFiles.length != 0)
    {
        //카드 목록 화면에서는 삭제하지 않는다
        cardBodyFileList += createFileList(oneMemory.memoryFiles, false);//false일 때는 삭제버튼을 보여주지 않는다.
    }


    //카드 바디 요소를 만들어주는 문자열을 만든다.
    let  cardBodyEle  = $('<div class="card-body">' + cardBodyFileList + '</div>');

    //ul은 div.card-body 와 형제요소여야한다
    if(cardBodyEle.find('ul').length !== 0)
    {
        //함께 쌓여있는 요소는 없으므로 기본적인 클래스만 준다.
        cardBodyEle.find('ul').addClass('list-group list-group-flush');
        cardBodyEle.find('li').addClass('list-group-item list-group-item-action');
    }
    //img는 div에 둘러쌓여야하며, div.card-body와 형제요소여야한다.
    if(cardBodyEle.find('img').length !== 0)
    {
        //img 를 p 밖으로 꺼내주고 스타일 부여
        cardBodyEle.find('img').unwrap();
        cardBodyEle.find('img').css('width', '100%')
    }

    //ul이 아니면 div에 쌓이게 한다.
    let tempBodyStr = '<div>';

    cardBodyEle.children().each((idx, ele)=>
    {
        if($(ele).prop('tagName')==='IMG')
        {
            tempBodyStr += '</div>';
            tempBodyStr += '<div>' + $(ele).prop('outerHTML') + '</div>';
            tempBodyStr += '<div>';
        }
        else if($(ele).prop('tagName')==='UL')
        {
            tempBodyStr += '</div>';
            tempBodyStr += $(ele).prop('outerHTML');
            tempBodyStr += '<div>';
        }
        else
        {
            tempBodyStr += $(ele).prop('outerHTML');
        }
    });
    tempBodyStr += '</div>';
    tempBodyStr = tempBodyStr.replaceAll('<div></div>', '');
    cardBodyEle = $('<div>' + tempBodyStr + '</div>');

    cardBodyEle.find('div').addClass('card-body');
    cardBodyEle.find('div').has('img').removeClass('card-body').css('text-align','center');

    //카드 푸터 요소를 만들어주는 문자열을 만든다.
    let editDate      = oneMemory.modifyDate==null? oneMemory.createDate:oneMemory.modifyDate;
    let convertDate   = parseDate(editDate, 'summary');
    let cardFooterEle = $('<div></div>'); 
    cardFooterEle.addClass('card-footer text-muted');
    cardFooterEle.prepend('<div></div>');
    cardFooterEle.append('<div class="text-end italicDate"></div>');
    cardFooterEle.find('div.text-end.italicDate').text(convertDate + (oneMemory.modifyDate==null? ' Created':' Modified'));

    let strDateStr = oneMemory.todoYn == "Y"?(oneMemory.todo===null?null:(oneMemory.todo.strDate===null?null:oneMemory.todo.strDate)):null;
    let endDateStr = oneMemory.todoYn == "Y"?(oneMemory.todo===null?null:(oneMemory.todo.endDate===null?null:oneMemory.todo.endDate)):null;
    if(oneMemory.todoYn =='Y')
    {
        cardFooterEle.find('div').eq(0).append(strDateStr!==null?'<span>' + parseDate(strDateStr, 'short') + '</span>':'');
        cardFooterEle.find('div').eq(0).append(endDateStr!==null?' ~ <span>' + parseDate(endDateStr, 'short') + '</span>':'');
    }

    cardParent.find('div.card').append(cardHeaderEle.prop('outerHTML'));
    cardParent.find('div.card').append(cardBodyEle.prop('innerHTML'));
    cardParent.find('div.card').append(cardFooterEle.prop('outerHTML'));
    cardParent.find('input[name=todoCheck]').on('change', checkTodoFunc);

    return cardParent;
}
//FileList div에 filelist를 채운다.
function createFileList(memoryFiles, deleteYn) {

    let fileList = '<ul class="fileList">';
    for (let file of memoryFiles) {
        fileList += '<li class="fileItem justify-content-between d-flex">';
        fileList += '<a class="filename" href="javascript:fileDown(' + file.fileRelaMemoryId + ', ' + file.memoryFileId + ');" style="width:'+ (deleteYn?'85%':'100%') +';">';
        fileList += file.fileOrigName + '</a>';
        if(deleteYn)
        {
            fileList += '<a class="text-link text-danger" href="javascript:void(0);" onClick="fileDelete(' + file.memoryFileId + ')" memoryFileId="' + file.memoryFileId + '">삭제</a>';
        }
        fileList += '</li>';
    }
    fileList += '</ul>';
    return fileList;
}
//스크롤을 인지하여 다음 페이지의 메모리카드를 불러온다
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
    $('#fileList').html('');
    $('#fileList').hide();
    $('input[name=memoryId]').val('');
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
//기억을 수정할수 있는 모달을 띄우며 세팅
function editMemory(memoryId)
{
    let memory = varMemories.get(memoryId);
    
    $('input[name=memoryId]').val(memory.memoryId);
    $('input[name=title]').val(memory.title);
    $('textarea[name=content]').val(memory.contentOrig);
    $('select[name=category] option[value=' + memory.category +']').prop('selected',true);
    

    if(memory.memoryFiles.length != 0)
    {
        $('#fileList').show();
        let appendStr = '<div class="col-12"><label>File List</label>';
        appendStr += createFileList(memory.memoryFiles, true);
        appendStr += '</div>';
        $('#fileList').append(appendStr);
        $('#collapseOne').collapse('show');
    }

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
//기억을 삭제하는 이벤트
function deleteMemory(memoryId)
{
    let path         = $('#contextPath').val();

    $.ajax({
        url : `${ path }/wc/text/memoryDelete?memoryId=${memoryId}`,
        type : 'POST',
        contentType : 'json',
        success : (data, textStatus, jqXHR)=>{
            if(jqXHR.getResponseHeader("REQUIRE_LOGIN") === 'true')
            {
                if(confirm('로그인이 필요한 기능입니다.\n로그인페이지로 가시겠습니까?'))
                {
                    location.href = path + '/login';
                }
            }
            else
            {
                location.reload();
            }
        }
    });
}
//기억을 복제하는 이벤트
function copyMemory(memoryId)
{
    let memory = varMemories.get(memoryId);
    let formData = new FormData();

    // var inputFile = $("input[name='imageFile']");
    // var files = inputFile[0].files;
    // for(var i =0;i<files.length;i++){
    //     formData.append("imageFile", files[i]);
    // }

    formData.append("memoryId", '');
    formData.append("content", memory.contentOrig);
    formData.append("contentOrig", memory.contentOrig);
    formData.append("title", memory.title);
    formData.append("createDate", memory.createDate);
    formData.append("modifyDate", null);
    formData.append("category", memory.category);
    formData.append("memberId", memory.memberId);
    formData.append("todoYn", memory.todoYn==='N'?null:memory.todoYn);
    formData.append("strDate", memory.todoYn !== 'Y'?'':(memory.todo.strDate ==null?'':memory.todo.strDate ));
    formData.append("endDate", memory.todoYn !== 'Y'?'':(memory.todo.endDate ==null?'':memory.todo.endDate ));
    formData.append("succDate", memory.todoYn !== 'Y'?'':(memory.todo.succDate==null?'':memory.todo.succDate));
    formData.append("imageFile", null);


    // memory.memoryId   = 0;
    // memory.content    = memory.contentOrig;
    // memory.todoYn     = memory.todoYn==='N'?null:memory.todoYn;
    // memory.modifyDate = null;
    // memory = {strDate  : (memory.todoYn !== 'Y'?'':(memory.todo.strDate ==null?'':memory.todo.strDate )), ... memory};
    // memory = {succDate : (memory.todoYn !== 'Y'?'':(memory.todo.succDate==null?'':memory.todo.succDate)), ... memory};
    // memory = {endDate  : (memory.todoYn !== 'Y'?'':(memory.todo.endDate ==null?'':memory.todo.endDate )), ... memory};
    
    memorySaveAjax(formData, null);
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
        let diffDate     = (new Date().getTime() - date.getTime());
        let timesAgo     = parseInt(diffDate/1000);
        let resultString = '';
        
        if(parseInt(diffDate/1000) !== 0 || parseInt(diffDate) !== 0)
        {
            resultString = ' Now,';
        }
        timesAgo = parseInt(diffDate/1000/60);
        if(timesAgo !== 0)
        {
            resultString = timesAgo + ' Minutes ago,';
        }
        timesAgo = parseInt(timesAgo/60);
        if(timesAgo !== 0)
        {
            resultString =  timesAgo + ' Hours ago,';
        }
        timesAgo = parseInt(timesAgo/24);
        if(timesAgo !== 0)
        {
            resultString =  timesAgo + ' Days ago,';
        }
        //(diffDate/1000/60/60/24/30) 라는 수로 나눠서 확인하면 자연 로그 라는 수가 생성되어
        // 오히려 방금 작성했는데 3Years Ago 가 나온다
        if( parseInt(timesAgo/30) !== 0 && (new Date().getMonth() - date.getMonth()) !== 0)
        {
            resultString =  (new Date().getMonth() - date.getMonth()) + ' Months ago,';
        }
        //(diffDate/1000/60/60/24/365) 라는 수로 나눠서 확인하면 자연 로그 라는 수가 생성되어
        // 오히려 방금 작성했는데 3Years Ago 가 나온다
        if(parseInt(timesAgo/365) !== 0 && (new Date().getFullYear() - date.getFullYear()) !== 0)
        {
            resultString =  (new Date().getFullYear() - date.getFullYear()) + ' Years ago,';
        }
        return resultString;
    }

    if(textLen === 'short')
    {
        convertDate = convertDate.split(' ')[0];
    }

    return convertDate;
}
//todo를 체크할때 발생하는 이벤트
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
        let memory = varMemories.get(data.memoryId);
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
//파일을 다운받는 함수
function fileDown(memoryId, memoryFileId){
    let path         = $('#contextPath').val();
    location.assign(`${ path }/wc/file/fileDown?memoryId=${memoryId}&memoryFileId=${memoryFileId}`);
}
//파일을 삭제하는 함수 delete 폴더로 옮기고 DB에서는 보여주지 않는다.
function fileDelete(memoryFileId){
    let path         = $('#contextPath').val();

    $.ajax({
        url : `${ path }/wc/file/fileDelete?memoryFileId=${memoryFileId}`,
        type : 'POST',
        contentType : 'json',
        success : (data)=>
        {
            if(data.result == 0)
            {
                $('#fileList').find('a[memoryFileId='+ memoryFileId +']').parents('li').text('삭제에 실패하였습니다.');
            }
            else
            {
                $('#fileList').find('a[memoryFileId='+ memoryFileId +']').parents('li').remove();
            }
        }
    });
}