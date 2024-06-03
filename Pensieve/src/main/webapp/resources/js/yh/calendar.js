document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'addEventButton'
      },
      customButtons: {
        addEventButton: { // 추가한 버튼 설정
            text : "일기 추가",  // 버튼 내용
            click : function(){ // 버튼 클릭 시 이벤트 추가
                $("#calendarModal").modal("show"); // modal 나타내기
                
                $("#addCalendar").on("click",function(){  // modal의 추가 버튼 클릭 시
                    var content = $("#calendar_content").val();
                    var start_date = $("#calendar_start_date").val();
                    
                    //내용 입력 여부 확인
                    if(content == null || content == ""){
                        alert("내용을 입력하세요.");
                    }else if(start_date == "" || end_date ==""){
                        alert("날짜를 입력하세요.");
                    }else if(new Date(end_date)- new Date(start_date) < 0){ // date 타입으로 변경 후 확인
                        alert("종료일이 시작일보다 먼저입니다.");
                    }else{ // 정상적인 입력 시
                        var obj = {
                            "title" : content,
                            "start" : start_date,
                            "end" : end_date
                        }//전송할 객체 생성

                        console.log(obj); //서버로 해당 객체를 전달해서 DB 연동 가능
                       }
                   });

             }
         }
      },
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:');
        if (title) { 
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('Are you sure you want to delete this event?')) {
          arg.event.remove()
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
        {
          title: 'All Day Event',
          start: '2023-01-01'
        },
        {
          title: 'Long Event',
          start: '2023-01-07',
          end: '2023-01-10'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2023-01-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2023-01-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2023-01-11',
          end: '2023-01-13'
        },
        {
          title: 'Meeting',
          start: '2023-01-12T10:30:00',
          end: '2023-01-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2023-01-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2023-01-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2023-01-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2023-01-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2023-01-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2023-01-28'
        }
      ]
    });

    calendar.render();
    
    $("#calendarModalClose").on("click",function(){
        $("#calendarModal").modal("hide")
    });
    
  });