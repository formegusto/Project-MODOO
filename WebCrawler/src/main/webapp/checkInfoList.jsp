<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Board Check List</title>
</head>
<body>
<center>
	<h1>Board Check List</h1>
	<h3>${user.name }님 환영합니다.</h3>
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td>
					<button type="button" onclick="location.href='getInfoList.do'">Crawler</button>
					<button type="button" onclick="location.href='getBoardList.do'">Share</button>
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
			<th bgcolor="orange"></th>
			<th bgcolor="orange">번호</th>
			<th bgcolor="orange">제목</th>
			<th bgcolor="orange" width="150">등록일</th>
		</tr>
		<c:forEach items="${infoList }" var="info">
		<tr>
			<td><input type="checkbox" name="seqList" value="${info.seq }">
			<td>${info.seq }</td>
			<td align="left"><a href="getInfo.do?seq=${info.seq }">
			${info.title }
			</a></td>
			<td>${info.regDate }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<input type="submit" value="New Crawler" onclick="javascript: form.action='crawlerAdd.jsp'"/>
	<input type="submit" value="New Board" onclick="javascript: form.action='boardConfirm.do'"/>
	</form>
</center>
</body>
</html>