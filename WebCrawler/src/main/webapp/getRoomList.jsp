<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Chat Room List</title>
</head>
<body>
<center>
	<h1>Chat Room List</h1>
	<h3>${user.name }님 환영합니다.</h3>
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td width="30%">
					<button type="button" onclick="location.href='getInfoList.do'">Crawler</button>
					<button type="button" onclick="location.href='getBoardList.do'">Share</button>
					<button type="button" onclick="location.href='getRoomList.do'">ChatRoom</button>
				</td>
				<td width="50%">
					<div id="roomOpen"></div>
				</td>
				<td>
					<button type="button" onclick="location.href='logout.do'">logout</button>
				</td>
			</tr>
	</table>
	<!--  검색 시작 -->
	<form method="post" name="form">
		<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td align="right">
					<select name="searchCondition">
						<option value="TITLE">제목
						<option value="CONTENT">내용
					</select>
					<input name="searchKeyword" type="text"/>
					<input type="submit" value="검색" onclick="javascript: form.action='getInfoList.do'"/>
				</td>
			</tr>
		</table>
	
	<!-- 검색 종료 -->
	
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
		<tr>
			<th bgcolor="orange">번호</th>
			<th bgcolor="orange">제목</th>
			<th bgcolor="orange">작성자</th>
			<th bgcolor="orange" width="150">등록일</th>
		</tr>
		<c:forEach items="${roomList }" var="room">
		<tr>
			<td>${room.rnum }</td>
			<td align="left">
			<a href="getRoom.do?rnum=${room.rnum }">${room.rtitle }</a>
			</td>
			<td>${room.id }</td>
			<td>${room.regDate }</td>
		</tr>
		</c:forEach>
	</table>
	</form>
</center>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    var textarea = document.getElementById("messageWindow");
    var webSocket = new WebSocket('ws://192.168.24.36:8080/WCrawl/alarm.do');
    var inputMessage = document.getElementById("inputMessage");
    webSocket.onerror = function(event) {
        onError(event)
    };
    webSocket.onopen = function(event) {
        onOpen(event)
    };
    webSocket.onmessage = function(event) {
        onMessage(event)
    };
    function onMessage(event) {
    	$("#roomOpen").html(event.data + "&nbsp;<button onclick='javascript:ok() '>X</button>");
    }
    function onOpen(event) {
    	$("#roomOpen").html("알람 기능 정상 작동중");
    }
    function ok(){
    	var form = document.form;
    	form.action="getRoomList.do";
        form.submit();
    }
    function onError(event) {
        alert(event.data);
    }
</script>
</body>
</html>