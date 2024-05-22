<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      height: 'auto',
      // stickyHeaderDates: false, // for disabling

      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'listMonth,listYear'
      },

      // customize the button names,
      // otherwise they'd all just say "list"
      views: {
        listMonth: { buttonText: 'list month' },
        listYear: { buttonText: 'list year' }
      },

      initialView: 'listYear',
      initialDate: '2023-01-12',
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      events: [
        {
          title: 'repeating event 1',
          daysOfWeek: [ 1, 2, 3 ],
          duration: '00:30'
        },
        {
          title: 'repeating event 2',
          daysOfWeek: [ 1, 2, 3 ],
          duration: '00:30'
        },
        {
          title: 'repeating event 3',
          daysOfWeek: [ 1, 2, 3 ],
          duration: '00:30'
        }
      ]
    });

    calendar.render();
  });

</script>
<style>
  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>
<div class="container">
   <div class="row">
     <div class="col-lg-12">
         <div class="card mb-3">
           <div class="card-body">
             <div id="calendar"></div>
           </div>
         </div>
     </div>
   </div>
</div>
</body>
</html>
