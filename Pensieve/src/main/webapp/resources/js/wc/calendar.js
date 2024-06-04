var events = [
  {
    title: "Business Lunch",
    start: "2024-06-03T13:00:00",
    constraint: "businessHours",
  },
  {
    title: "Meeting",
    start: "2024-06-13T11:00:00",
    constraint: " ", // defined below
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
];

$(document).ready(() => {

  var calendar = new FullCalendar.Calendar(document.getElementById("calendar"),
  {
    headerToolbar: 
    {
      left  : "prev,next today",
      center: "title",
      right : "dayGridYear,dayGridMonth,timeGridWeek,timeGridDay,listMonth",
    },
    locale       : "ko",
    themeSystem  : "bootstrap5",
    navLinks     : true, // can click day/week names to navigate views
    businessHours: true, // display business hours
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
    events       : events
  });

  calendar.unselect();

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
  }
  calendar.render();
});
