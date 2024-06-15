var memoryDictionary = new Map();
var selectProps;
var thisCalendar;
$(document).ready(() => 
{
  //modal Save 버튼 클릭시
  $('#saveText').on('click',saveModalMemory);
  //모달이 닫힐때마다 form 안의 내용을 초기화 한다
  $("#myModal").on('hide.bs.modal', closeSetting);
  
  //모달창 TodoYn 체크 이벤트
  $('#repeatYn').on('change',repeatYnToggle);
  // calendarOption['events'] = events;
  var calendar = '';

  $.ajax(
  {
    url :  $('#contextPath').val() +'/wc/calendar/getCalendarEvent',
    type : 'POST',
    // dataType    : 'json',
    // contentType : 'application/json;charset=utf-8',
    success : (data)=>
    {
      let datas = new Array();
      for(let memory of data.memories)
      {
        memoryDictionary.set(memory.memoryId, memory);
        datas.push(returnEvent(memory));
      }

      calendar = new FullCalendar.Calendar(document.getElementById("calendar"),{
        headerToolbar: 
        {
          left  : "prev,next today",
          center: "title",
          right : "dayGridYear,dayGridMonth,timeGridWeek,timeGridDay,listMonth",
        },
        initialView  : 'dayGridYear',
        locale       : "ko",
        themeSystem  : "bootstrap5",
        navLinks     : true, // can click day/week names to navigate views
        // businessHours: true, // display business hours
        businessHours: 
        {
          // days of week. an array of zero-based day of week integers (0=Sunday)
          daysOfWeek : [1, 2, 3, 4, 5],
          startTime  : "09:00",
          endTime    : "18:00",
        },
        editable     : true,
        dayMaxEvents : true, // allow "more" link when too many events
        selectable   : true,
        select       : (arg)=>
        {
          $('#myModal').modal('show');
          setTimeout(()=>{$('#content').focus();}, 600);
          selectProps = arg;
          thisCalendar = calendar;
        },
        eventClick : eventClickFunction,
        eventDrop  : eventDropResizeFunction,
        eventResize: eventDropResizeFunction,
        dayCellContent:(info)=>
        {
          let number = document.createElement('a');
          number.classList.add('fc-daygrid-day-number');
          number.innerText = info.dayNumberText.replace('일','').replace('월',' /');
          return {html : number.outerHTML}
        }
      });   

      calendar.setOption('events', datas.concat(data.events));
      calendar.render();
    }
  });
});

function eventClickFunction(arg) 
{
    let disableEdit = arg.event.startEditable===undefined?false:(arg.event.startEditable===false?true:false);

    if (confirm('해당 이벤트를 삭제하시겠습니까?'))
    {
      if (disableEdit) {
        alert("삭제할수 없는 이벤트입니다.");
        return false;
      }
      arg.event.remove();
      
      $.ajax({
          url : `${ $('#contextPath').val() }/wc/calendar/calendarDelete/?memoryId=${arg.event.id}`,
          type : 'POST',
          contentType : 'json'
      });
    }
}

function eventDropResizeFunction(arg) {
    console.log(arg.event.id);
    let memory = memoryDictionary.get(parseInt(arg.event.id));
    
    memory['todoYn']  = memory['category']==='secondary'?'Y':null;//controller에 보낼때 이렇게 보내데?
    memory['strDate'] = moment(arg.event.start).format('yyyy-MM-DD hh:mm:ss');
    memory['endDate'] = moment(arg.event.end).format('yyyy-MM-DD hh:mm:ss');

    let viewType = arg.view.type;

    //"dayGridYear,dayGridMonth,timeGridWeek,timeGridDay,listMonth",
    // view에 따라 보여지는 날짜 형식이 다르므로 처리방식이다르다
    if(viewType === 'dayGridYear' || viewType === 'dayGridMonth')
    {
      memory['strDate']   = moment(arg.event.startStr).format('yyyy-MM-DD');
      memory['endDate']   = moment(arg.event.endStr).subtract(1, 'days').format('yyyy-MM-DD');
    }
    if(viewType === 'timeGridWeek' || viewType === 'timeGridDay' || viewType === 'listMonth')
    {
      memory['strDate']   = moment(arg.event.startStr).format('yyyy-MM-DD hh:mm:ss');
      memory['endDate']   = moment(arg.event.endStr).format('yyyy-MM-DD hh:mm:ss');
    }

    let formData = new FormData(form);

    formData.append("memoryId", memory.memoryId);
    formData.append("content", memory.contentOrig);
    formData.append("contentOrig", memory.contentOrig);
    formData.append("title", memory.title);
    formData.append("category", memory.category);
    formData.append("todoYn", memory.todoYn);
    formData.append("strDate", memory.strDate);
    formData.append("endDate", memory.endDate);
    formData.append("succDate", memory.todoYn !== 'Y'?'':(memory.todo.succDate==null?'':memory.todo.succDate));
      
    //memorySaveAjax생각보다 많이 쓰이네
    memorySaveAjax(formData,
    (data)=>
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
        let event = returnEvent(data.event); 
      }
    },
    ()=>{ $('#myModal').modal('hide');});
}

function saveModalMemory()
{
  let category       = $('#category>option:selected').val();
  let memoryId       = '';
  let title          = $('#category>option:selected').text();
  let content        = $('#content').val();
  let todoYn         = category==='secondary'?'Y':null;//controller에 보낼때 이렇게 보내데?
  let strDate        = selectProps.start;
  let endDate        = selectProps.end;
  let repeatPeriod   = $('#repeatYn:checked').val() ==='Y'?$('#repeatPeriod>option:selected').val():null;

  thisCalendar.unselect();
  let viewType = selectProps.view.type;

  //"dayGridYear,dayGridMonth,timeGridWeek,timeGridDay,listMonth",
  // view에 따라 보여지는 날짜 형식이 다르므로 처리방식이다르다
  if(viewType === 'dayGridYear' || viewType === 'dayGridMonth')
  {
    strDate   = moment(selectProps.startStr).format('yyyy-MM-DD');
    endDate   = moment(selectProps.endStr).subtract(1, 'days').format('yyyy-MM-DD');
  }
  if(viewType === 'timeGridWeek' || viewType === 'timeGridDay' || viewType === 'listMonth')
  {
      strDate   = moment(selectProps.startStr).format('yyyy-MM-DD hh:mm:ss');
      endDate   = moment(selectProps.endStr).format('yyyy-MM-DD hh:mm:ss');
  }
  
  let formData = new FormData();

  formData.append("memoryId"    , memoryId);
  formData.append("content"     , content);
  formData.append("contentOrig" , content);
  formData.append("title"       , title);
  formData.append("category"    , category);  
  formData.append("todoYn"      , todoYn);
  formData.append("strDate"     , strDate);
  formData.append("endDate"     , endDate);
  formData.append("repeatPeriod", repeatPeriod);


  //memorySaveAjax생각보다 많이 쓰이네
  memorySaveAjax(formData, function(data, textStatus, jqXHR)
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
      let event = returnEvent(data.event);
  
      thisCalendar.addEvent(event);
      
      //사용한 전역변수는 클리어
      thisCalendar = {};
      selectProps  = {};
    }
  },
  ()=>{ $('#myModal').modal('hide');});
}
//넘긴데이터 대로 WC_MEMORY에 저장한다.
function memorySaveAjax(data, successFunction, completeFunction)
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
        success:successFunction,
        error:function(data)
        {
            alert('저장에 실패하였습니다.');
        },
        complete:completeFunction
    });
}
//임시(참고) events
var events = [
  {
    title: "Business Lunch",
    start: "2024-06-03T13:00:00",
    constraint: "businessHours",
  },
  {
    title: "Meeting",
    start: "2024-06-13T11:00:00",
    constraint: "availableForMeeting", // defined below
    color: "#257e4a",
  },
  {
    title: "Conference",
    start: "2024-06-18",
    end: "2024-06-20",
  },
  {
    title: "Party",
    start: "2024-06-29T20:00:00",
  },

  // areas where "Meeting" must be dropped
  {
    groupId: "availableForMeeting",
    start: "2024-06-11T10:00:00",
    end: "2024-06-11T16:00:00",
    display: "background",
  },
  {
    groupId: "availableForMeeting",
    start: "2024-06-13T10:00:00",
    end: "2024-06-13T16:00:00",
    display: "background",
  },
  {
    start: "2024-06-13T10:00:00",
    end: "2024-06-13T12:00:00",
    display: "list-item",
    title: "WTF?",
  },
  {
    groupId: 999,
    title: 'Repeating Event',
    start: '2024-06-12T16:00:00'
  },
  {
    groupId: 999,
    title: 'Repeating Event',
    start: '2024-07-12T16:00:00'
  },
  {
    groupId: 999,
    title: 'Repeating Event',
  },
  {
    groupId: 999,
    title: 'Repeating Event',
  },
  {
    title: 'repeating Week Event',
    // daysOfWeek: [ 1, 2, 3, 4 ],
    start: '2024-06-05T00:00:00',
    duration: '0000-01-00T00:00:00'
  },
  {
    start: "2024-06-10T09:00:00",
    end: "2024-06-10T12:00:00",
    color: "#ff9f89",
    title: "PooPoo",
  },
  // red areas where no events can be dropped
  {
    start: "2024-06-24",
    end: "2024-06-28",
    overlap: false,
    display: "background",
    color: "#ff9f89",
  },
  {
    start: "2024-06-06",
    end: "2024-06-08",
    overlap: false,
    display: "background",
    color: "#ff9f89",
  },
  {
    title: 'Click for Google',
    url: 'http://google.com/',
    start: '2024-06-28'
  },
  {
    start: '2024-06-27',
    end: '2024-06-28',
    title: 'Click for Google2',
    url: 'http://google.com/',
    // display: "background",
    // color: "#ff9f89",
  }
];

function returnEvent(oneEvent) {
  let event = {};
  event['id'] = oneEvent.memoryId;
  event['title'] = oneEvent.content;
  event['allDay'] = false;
  
  if (moment(oneEvent.strDate).diff(moment(oneEvent.endDate)) % 86400000 === 0) {
    event['allDay'] = true;
    event['end'] = moment(oneEvent.endDate).add(1, 'days').format('yyyy-MM-DD hh:mm:ss');
  }
  else
  {
    event['end'] = moment(oneEvent.endDate).format('yyyy-MM-DD hh:mm:ss');
  }
  event['start'] = moment(oneEvent.strDate).format('yyyy-MM-DD hh:mm:ss');
  
  // console.log(event);
  return event;
}
//모달이 닫힐때 발생하는 이벤트
function closeSetting()
{
    $('#formModal')[0].reset();
    $('#collapseOne').collapse('hide');
    $('#repeatYn').prop('checked',false);
    $('#repeatYn').trigger('change');
}
//모달창 RepeatYn 체크 이벤트
function repeatYnToggle(event)
{
    let target = $(event.target);

    if(target.prop('checked'))
    {
        $('#repeatPeriod').prop('disabled',false);
        $('#btnClearTo, #btnClearFrom').prop('disabled',false);
      }
      else
      {
        $('#repeatPeriod').prop('disabled',true);
        $('#repeatPeriod>option').eq(0).prop('selected',true);
      }
}