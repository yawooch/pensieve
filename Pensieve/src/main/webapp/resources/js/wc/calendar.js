$(document).ready(() => {

  calendarOption['events'] = events;
  var calendar = new FullCalendar.Calendar(document.getElementById("calendar"),calendarOption);
  
  calendar.render();

  $.ajax({
    url : '/pensieve/wc/calendar/getCalendarEvent/',
    type : 'POST',
    dataType    : 'json',
    contentType : 'application/json;charset=utf-8',
    success : (data)=>
    {
      console.log(data);
    }

  });

});
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
var calendarOption = {
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
  select       : selectDateFunc,
  eventClick: function(arg) {
    if (confirm('Are you sure you want to delete this event?')) {
      arg.event.remove()
    }
  }
};

function selectDateFunc(arg) {
  var title = prompt("Event Title:");
  console.log(arg);
  if (title) {
    calendar.addEvent({
      title: title,
      start: arg.start,
      end: arg.end,
      allDay: arg.allDay,
    });
    alert(
      arg.start.toISOString().split("T")[0] + " ~ " +
      arg.end.toISOString().split("T")[0]   + ", " +
      title + "이 등록되었습니다."
    );
  }
  calendar.unselect();
}