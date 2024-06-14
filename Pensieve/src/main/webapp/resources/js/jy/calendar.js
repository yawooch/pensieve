document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
  
    var calendar = new FullCalendar.Calendar(calendarEl, {
      timeZone: 'UTC',
      themeSystem: 'bootstrap5',
      headerToolbar: {
        left: 'prev,next,addEventButton',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      weekNumbers: false,
      dayMaxEvents: true, // allow "more" link when too many events
      events:[ // 일정 데이터 추가 , DB의 event를 가져오려면 JSON 형식으로 변환해 events에 넣어주면된다.
        {
          title:'일정',
          start:'2024-05-26 00:00:00',
          end:'2024-05-27 24:00:00' 
        }
      ],
      customButtons: {
        addEventButton: { // 추가한 버튼 설정
          text : "일정 추가",  // 버튼 내용
          click : function(){ // 버튼 클릭 시 이벤트 추가
            $("#calendarModal").modal("show"); // modal 나타내기
            $("#calendarModalLabel").text("일정 추가"); // 모달 제목 설정
            // 이전에 입력된 내용 초기화
            $("#calendar_content").val('');
            $("#calendar_start_date").val('');
            $("#calendar_end_date").val('');
  
            $("#addCalendar").off("click").on("click", function(){  // modal의 추가 버튼 클릭 시
              var content = $("#calendar_content").val();
              var start_date = $("#calendar_start_date").val();
              var end_date = moment($("#calendar_end_date").val()).add(1, "days").format("YYYY-MM-DD");
              
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
                calendar.addEvent(obj); // 캘린더에 이벤트 추가
                $("#calendarModal").modal("hide"); // 모달 닫기
              }
            });
          }
        }
      },
      dateClick: function(info) { // 날짜 클릭 시
        $("#calendarModal").modal("show"); // modal 나타내기
        $("#calendarModalLabel").text("일정 추가"); // 모달 제목 설정
        // 클릭한 날짜를 모달의 시작 날짜로 설정
        $("#calendar_start_date").val(info.dateStr);
        $("#calendar_end_date").val(info.dateStr);
  
        // 이전에 입력된 내용 초기화
        $("#calendar_content").val('');
  
        $("#addCalendar").off("click").on("click", function(){  // modal의 추가 버튼 클릭 시
          var content = $("#calendar_content").val();
          var start_date = $("#calendar_start_date").val();
          var end_date = moment($("#calendar_end_date").val()).add(1, "days").format("YYYY-MM-DD");
          
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
            calendar.addEvent(obj); // 캘린더에 이벤트 추가
            console.log(calendar);
            $("#calendarModal").modal("hide"); // 모달 닫기
          }
        });
      },
      eventClick: function(info) { // 이벤트 클릭 시
        $("#calendarModal").modal("show"); // modal 나타내기
        $("#calendarModalLabel").text("일정 수정"); // 모달 제목 설정
    
        // 클릭한 이벤트의 정보를 모달에 설정
        $("#calendar_content").val(info.event.title);
        $("#calendar_start_date").val(info.event.start.toISOString().substring(0, 10));
        $("#calendar_end_date").val(moment(info.event.end).subtract(1, "days").format("YYYY-MM-DD"));
    
        // 추가 버튼 클릭 핸들러
        $("#addCalendar").off("click").on("click", function () { // 기존 이벤트 핸들러 제거 후 새로 추가
          var content = $("#calendar_content").val();
          var start_date = $("#calendar_start_date").val();
          var end_date = moment($("#calendar_end_date").val()).add(1, "days").format("YYYY-MM-DD");
    
          // 내용 입력 여부 확인
          if (content == null || content == "") {
            alert("내용을 입력하세요.");
          } else if (start_date == "" || end_date == "") {
            alert("날짜를 입력하세요.");
          } else if (new Date(end_date) - new Date(start_date) < 0) { // date 타입으로 변경 후 확인
            alert("종료일이 시작일보다 먼저입니다.");
          } else { // 정상적인 입력 시
            // 기존 이벤트 수정
            info.event.setProp('title', content);
            info.event.setStart(start_date);
            info.event.setEnd(end_date);
    
            console.log(info.event); // 서버로 해당 객체를 전달해서 DB 연동 가능
            $("#calendarModal").modal("hide"); // 모달 닫기
          }
        });
      },
      editable: true, // false로 변경 시 draggable 작동 x 
      displayEventTime: false // 시간 표시 x
    });
    
    calendar.render();
  
    $("#calendarModalClose").on("click", function(){
       $("#calendarModal").modal("hide")
    });
  });