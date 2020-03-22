<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Board SELECT Page</title>
</head>
<body>
<center>
	<h1>Board SELECT Page</h1>
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
		<c:if test="${board.id eq user.id }">
		<tr>
			<td>
				<input type="hidden" name="id" value="${board.id }"/>
				<input type="hidden" name="bnum" value="${board.bseq }"/>
				<input type="text" name="rtitle"/>
			</td>
			<td>
				<input type="button" value="New Chat" onclick="javascript: send()"/>
			</td>
		</tr>
		</c:if>
	</table>
	<hr/>
	<c:forEach items="${infoList }" var="info">
		<input type="hidden" name="inumList" value="${info.seq }"/>
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left">
					${info.title }
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">주소</td>
				<td align="left">
					${info.link }
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">CssQuery</td>
				<td align="left">
					${info.cssQuery }
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left">
					${info.content }
				</td>
			</tr>
			<c:set var="key">${info.seq }</c:set>
			<c:set var="dataList">${dataMap[key] }</c:set>
			<c:forEach items="${dataList }" var="data">
				<tr>
					<td align="left">${info.field }</td>
					<td>${data }</td>
				</tr>
			</c:forEach>
		</table>
	<hr/>
	</c:forEach>
	<input type="submit" value="글목록" onclick="javascript: form.action='getBoardList.do'"/>
	<input type="hidden" value='${user.id }' id='alarm_id' />
	</form>
</center>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    var textarea = document.getElementById("messageWindow");
    var webSocket = new WebSocket('ws://192.168.24.36:8080/WCrawl/alarm.do');
    webSocket.onerror = function(event) {
        onError(event)
    };
    webSocket.onopen = function(event) {
        onOpen(event)
    };
    webSocket.onmessage = function(event) {
        onMessage(event)
    };
    function send() {
    	var form = document.form;
    	form.action="roomAdd_proc.do";
        webSocket.send($("#alarm_id").val() + " 님이 새로운 채팅방을 오픈했습니다.");
        form.submit();
    }
    function onError(event) {
        alert(event.data);
    }
</script>
</body>
</html>