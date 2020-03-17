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
	<h3>${user.name }�� ȯ���մϴ�.</h3>
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td>
					<button type="button" onclick="location.href='getInfoList.do'">Crawler</button>
					<button type="button" onclick="location.href='getBoardList.do'">Share</button>
				</td>
			</tr>
	</table>
	<!--  �˻� ���� -->
	<form method="post" name="form">
		<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td align="right">
					<select name="searchCondition">
						<option value="TITLE">����
						<option value="CONTENT">����
					</select>
					<input name="searchKeyword" type="text"/>
					<input type="submit" value="�˻�" onclick="javascript: form.action='getInfoList.do'"/>
				</td>
			</tr>
		</table>
	
	<!-- �˻� ���� -->
	
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
		<tr>
			<th bgcolor="orange"></th>
			<th bgcolor="orange">��ȣ</th>
			<th bgcolor="orange">����</th>
			<th bgcolor="orange" width="150">�����</th>
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