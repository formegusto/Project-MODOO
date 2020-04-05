<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>Chat Room List</title>
</head>
<body>
<!-- Navbar : Login, 알람 정보 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
   <a class="navbar-brand" href="#">MODOO</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Welcome! ${user.name }<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="logout.do">logout</a>
      </li>
    </ul>
    <div id="roomOpen"></div>
  </div>
</nav>

<!-- Navs : 보드 메뉴 -->
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link" href="getInfoList.do">Info</a>
  <a class="nav-item nav-link" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link active" href="getRoomList.do">ChatRoom</a>
</nav>
	
<!-- getRoomList Design -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">번호</th>
	      <th scope="col">제목</th>
	      <th scope="col">작성자</th>
	      <th scope="col">등록일</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${roomList }" var="room">
		<tr>
			<th scope="row">${room.rnum }</th>
			<td align="left">
			<a href="getRoom.do?rnum=${room.rnum }">${room.rtitle }</a>
			</td>
			<td>${room.id }</td>
			<td>${room.regDate }</td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups"
		style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary">1</button>
	    <button type="button" class="btn btn-secondary">2</button>
	    <button type="button" class="btn btn-secondary">3</button>
	    <button type="button" class="btn btn-secondary">4</button>
	  	</div>
	  	<div class="btn-group mr-2" role="group" aria-label="Second group">
	    <button type="button" class="btn btn-secondary">5</button>
	    <button type="button" class="btn btn-secondary">6</button>
	    <button type="button" class="btn btn-secondary">7</button>
	  	</div>
	  	<div class="btn-group" role="group" aria-label="Third group">
	    <button type="button" class="btn btn-secondary">8</button>
  	</div>
</div>
</div>
</div>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    var textarea = document.getElementById("messageWindow");
    var webSocket = new WebSocket('ws://192.168.24.7:8080/WCrawl/alarm.do');
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
    	$("#roomOpen").html("<span class='navbar-text'>" + "<button type='button' class='btn btn-dark' onclick=location.href='getRoomList.do'>" + event.data + "</button></span>");
    }
    function onOpen(event) {
    	$("#roomOpen").html("<p style='color:white; '><strong>알람 기능 정상 작동중</strong></p>");
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