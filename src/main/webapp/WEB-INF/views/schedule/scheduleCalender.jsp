<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/header.jsp" />
	<title>캘린더 일정관리</title>
	<!-- fullcalendar CDN -->  
	<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />  
	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>  
	<!-- fullcalendar 언어 CDN -->  
	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
	<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
	<style>
		.container {
			max-width: 1900px;
		}
		
		.fc-header-toolbar {
		    padding-top: 1em;    
		    padding-left: 1em;    
		    padding-right: 1em;
		}
		
		/* 일요일 날짜 빨간색 */
		.fc-day-sun a {
		  color: red;
		  text-decoration: none;
		}
		
		/* 토요일 날짜 파란색 */
		.fc-day-sat a {
		  color: blue;
		  text-decoration: none;
		}
		
		.color-group {
			display: flex;
		}
		
		.color-group > p {
			margin: 0 10px 0 0;
		}
	</style>
</head>
<body>
	<div class="container pb-8">
		<div class="row">
			<div class="col-sm-2">
				<aside>
					<h3>캘린더</h3>
					<button class="btn btn-info" data-toggle="modal" data-target="#scheduleModal">일정등록</button>
					<div>
						<a href="#">&middot; 공유 일정 등록</a>
					</div>
				</aside>
			</div>
			<div class="col-sm-10">
				<div id="calendar-container">
					<div id="calendar"></div>
				</div>
			</div>
		</div>
	</div>
	
	<%-- modal 시작 --%>
	<div class="modal" id="scheduleModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">일정등록</div>
				<div class="modal-body">
					<%-- <form name="scheduleForm" action="scheduleForm" method="post"> --%>
						<input type="hidden" id="scheduleId" name="scheduleId" value="${param.id}">
						
						<div class="form-group">
							<label for="schedule_name">일정명</label>
							<input type="text"
								   class="form-control" placeholder="일정명을 입력하세요"
								   name="schedule_name" id="schedule_name">
						</div>
						<div class="form-group">
							<label for="startAt">시작날짜/시간</label>
							<input type="datetime-local" name="startAt" id="startAt" class="form-control">
						</div>
						<div class="form-group">
				            <label for="endAt">종료날짜/시간</label>
				            <input type="datetime-local" name="endAt" id="endAt" class="form-control">
				        </div>
				        <div class="form-group custom-control custom-checkbox">
				             <input type="checkbox" name="allDay" id="allDay" class="custom-control-input" value="">
				             <label for="allDay" class="custom-control-label">하루종일</label>
				        </div>
          				<div class="form-group">
          					<div class="color-group">
          						<p>색상 :</p>
					            <select name="bgColor" id="bgColor">
									<option value="red" style="color:red;">빨강</option>
						            <option value="orange" style="color:orange;">주황</option>
						            <option value="yellow" style="color:yellow;">노랑</option>
						            <option value="green" style="color:green;">초록</option>
						            <option value="blue" style="color:blue;">파랑</option>
						            <option value="indigo" style="color:indigo;">남</option>
						            <option value="purple" style="color:purple;">보라</option>
								</select>
          					</div>
				        </div>
				        <div>
				        	<p>내용</p>
				        	<textarea rows="10" name="description" id="description"></textarea>
				        </div>
          				
						<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
						<button type="submit" class="btn btn-info" id="btnRegister">등록</button>
					<%-- </form> --%>
				</div>
			</div>
		</div>
	</div>
	<%-- modal end --%>
</body>
</html>