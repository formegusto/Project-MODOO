<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/css/chat.css" rel="stylesheet"></link>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://use.fontawesome.com/63a7f56cba.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>${room.rtitle }</title>
<!-- 로그인한 상태일 경우와 비로그인 상태일 경우의 chat_id설정 -->
<c:if test="${(user.id ne '') and !(empty user.id)}">
	<input type="hidden" value='${user.id }' id='chat_id' />
</c:if>
<c:if test="${(user.id eq '') or (empty user.id)}">
	<input type="hidden" value='<%=session.getId().substring(0, 6)%>' id='chat_id' />
</c:if>
</head>
<body>
<!-- Navbar : Login, 알람 정보 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">WCrawl</a>
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

<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <tbody>
	    <tr>
	      <th scope="row">제목</th>
	      <td>${board.title }</td>
	    </tr>
	    <tr>
	      <th scope="row">작성자</th>
	      <td>${board.id }</td>
	    </tr>
	    <tr>
	      <th scope="row">내용</th>
	      <td>${board.content }</td>
	    </tr>
	    </tbody>
	</table>
</div>
</div>

<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='getRoomList.do'; form.submit()">RoomList</button>
	  	</div>
  	</div>
</div>
</div>

</form>

<!-- 채팅창 -->
<div class="container" style="margin-top: 15px;">
<div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>접속자리스트</h4>
            </div>
          </div>
          <div id="inbox_chat" class="inbox_chat"></div>
        </div>
        <div id="mesgs" class="mesgs">
          <div id="msg_history" class="msg_history">
          <c:forEach items="${chatList }" var="chat">
	            	<c:if test="${chat.id eq user.id }">
		            	<div class="outgoing_msg">
			              <div class="sent_msg">
			                <p>${chat.data }</p>
			                <span class="time_date">나</span> 
			              </div>
			            </div>
	            	</c:if>
	            	<c:if test="${chat.id ne user.id }">
	            		<div class="incoming_msg">
			              <div class="received_msg">
			                <div class="received_withd_msg">
			                  <p>${chat.data }</p>
			                  <span class="time_date">${chat.id }</span>
			                </div>
			              </div>
			            </div>
	            	</c:if>
          </c:forEach>
          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input type="text" id="inputMessage" class="write_msg" placeholder="Type a message" onkeyup="enterkey()"/>
              <button class="msg_send_btn" type="button" onclick="send()"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
            </div>
          </div>
        </div>
      </div>
</div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    var textarea = document.getElementById("messageWindow");
    var rnum = ${room.rnum};
    var webSocket = new WebSocket('ws://192.168.24.36:8080/WCrawl/chat.do?' + rnum + '&' + $("#chat_id").val());
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
        var message = event.data.split("|");
        var sender = message[0];
        var content = message[1];
        if (content == "") {
            
        } else {
        	if(sender == "userList"){
        	  var userList = content.split(",");
              userList.forEach(function(user,index,userList){
            	  if(index == 0){
            		  $("#inbox_chat").html("<div class='chat_list'><div class='chat_people'><div class='chat_ib'>" 
                				+ "<h5>"+ user + "</h5></div></div></div>");
            	  } else {
            		  $("#inbox_chat").html($("#inbox_chat").html()
            				  + "<div class='chat_list'><div class='chat_people'><div class='chat_ib'>" 
                				+ "<h5>"+ user + "</h5></div></div></div>");
            	  }
              });
        	} else {
	            $("#msg_history").html($("#msg_history").html() 
	            		+ "<div class='incoming_msg'><div class='received_msg'><div class='received_withd_msg'>" 
	            		+ "<p>" + content + "</p>"
	            		+ "<span class='time_date'>" + sender + "</span>"
	            		+ "</div></div></div>");
        	}
        	var elem = document.getElementById('msg_history');
            elem.scrollTop = elem.scrollHeight;
       }
    }
    function onOpen(event) {
    	var elem = document.getElementById('msg_history');
        elem.scrollTop = elem.scrollHeight;
    	$("#msg_history").html($("#msg_history").html() 
                + "<div class='outgoing_msg'><div class='sent_msg'>" 
                + "<p>채팅방 연결에 성공했습니다.</p>"
                + "<span class='time_date'>나</span>" 
                + "</div></div>");
    }
    function onError(event) {
        alert(event.data);
    }
    function send() {
        if (inputMessage.value == "") {
        } else {
          	$("#msg_history").html($("#msg_history").html() 
                + "<div class='outgoing_msg'><div class='sent_msg'>" 
                + "<p>" + inputMessage.value + "</p>"
                + "<span class='time_date'>나</span>" 
                + "</div></div>");
        }
        var elem = document.getElementById('msg_history');
        elem.scrollTop = elem.scrollHeight;
        webSocket.send($("#chat_id").val() + "|" + inputMessage.value);
        inputMessage.value = "";
    }
    //     엔터키를 통해 send함
    function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }
</script>
</body>
</html>