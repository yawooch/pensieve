<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"              prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"               prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"         prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags"   prefix="security" %>

<c:set var="path" value="${ pageContext.request.contextPath }"/>


<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var srcCalendarEl = document.getElementById('source-calendar');
    var destCalendarEl = document.getElementById('destination-calendar');

    var srcCalendar = new FullCalendar.Calendar(srcCalendarEl, {
      editable: true,
      initialDate: '2023-01-12',
      events: [
        {
          title: 'event1',
          start: '2023-01-11T10:00:00',
          end: '2023-01-11T16:00:00'
        },
        {
          title: 'event2',
          start: '2023-01-13T10:00:00',
          end: '2023-01-13T16:00:00'
        }
      ],
      eventLeave: function(info) {
        console.log('event left!', info.event);
      }
    });

    var destCalendar = new FullCalendar.Calendar(destCalendarEl, {
      initialDate: '2023-01-12',
      editable: true,
      droppable: true, // will let it receive events!
      eventReceive: function(info) {
        console.log('event received!', info.event);
      }
    });

    srcCalendar.render();
    destCalendar.render();
  });

</script>
<style>
  #source-calendar,
  #destination-calendar {
    float: left;
    width: 600px;
    margin: 0 20px 20px 0;
  }

</style>
</head>
<body>

<div class="container">
   <div class="row">
     <div class="col-lg-12">
         <div class="card mb-3">
           <div class="card-body">
			  <div id='source-calendar'></div>
			  <div id='destination-calendar'></div>
           </div>
         </div>
     </div>
   </div>
</div>

</body>
</html>
