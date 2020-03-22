<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Room IN Page</title>
<!-- 로그인한 상태일 경우와 비로그인 상태일 경우의 chat_id설정 -->
<c:if test="${(user.id ne '') and !(empty user.id)}">
	<input type="hidden" value='${user.id }' id='chat_id' />
</c:if>
<c:if test="${(user.id eq '') or (empty user.id)}">
	<input type="hidden" value='<%=session.getId().substring(0, 6)%>' id='chat_id' />
</c:if>

</head>
<body>
<center>
	<h1>${room.rtitle }</h1>
	<hr/>
	<form method="post" name="form">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange" width="70">제목</td>
			<td align="left">${board.title }</td>
		</tr>
		<tr>
			<td bgcolor="orange">작성자</td>
			<td align="left">${board.id }</td>
		</tr>
		<tr>
			<td bgcolor="orange">내용</td>
			<td align="left">${board.content }</td>
		</tr>
	</table>
	<hr/>
	<input type="submit" value="글목록" onclick="javascript: form.action='getBoardList.do'"/>
	</form>
	<hr/>
	<!--     채팅창 -->
    <div id="_chatbox">
        <fieldset>
            <div id="messageWindow"></div>
            <br/> <input id="inputMessage" type="text" onkeyup="enterkey()" />
            <input type="submit" value="send" onclick="send()" />
        </fieldset>
    </div>
</center>
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
            if (content.match("/")) {
                if (content.match(("/" + $("#chat_id").val()))) {
                    var temp = content.replace("/" + $("#chat_id").val(), "(귓속말) :").split(":");
                    if (temp[1].trim() == "") {
                    } else {
                        $("#messageWindow").html($("#messageWindow").html() + "<p class='whisper'>"
                            + sender + content.replace("/" + $("#chat_id").val(), "(귓속말) :") + "</p>");
                    }
                } else {
                }
            } else {
                if (content.match("!")) {
                    $("#messageWindow").html($("#messageWindow").html()
                        + "<p class='chat_content'><b class='impress'>" + sender + " : " + content + "</b></p>");
                } else {
                    $("#messageWindow").html($("#messageWindow").html()
                        + "<p class='chat_content'>" + sender + " : " + content + "</p>");
                }
            }
        }
    }
    function onOpen(event) {
        $("#messageWindow").html("<p class='chat_content'>[" + [rnum] + "] 채팅에 참여하였습니다.</p>");
    }
    function onError(event) {
        alert(event.data);
    }
    function send() {
        if (inputMessage.value == "") {
        } else {
            $("#messageWindow").html($("#messageWindow").html()
                + "<p class='chat_content'>나 : " + inputMessage.value + "</p>");
        }
        webSocket.send($("#chat_id").val() + "|" + inputMessage.value);
        inputMessage.value = "";
    }
    //     엔터키를 통해 send함
    function enterkey() {
        if (window.event.keyCode == 13) {
            send();
        }
    }
    //     채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
    window.setInterval(function() {
        var elem = document.getElementById('messageWindow');
        elem.scrollTop = elem.scrollHeight;
    }, 0);
</script>
</body>
</html>