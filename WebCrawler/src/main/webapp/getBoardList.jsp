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
	<h1>Board List</h1>
	<h3>${user.name }�� ȯ���մϴ�.</h3>
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td>
					<button type="button" onclick="location.href='getInfoList.do'">Crawler</button>
					<button type="button" onclick="location.href='getBoardList.do'">Share</button>
				</td>
				<td>
					<button type="button" onclick="location.href='logout.do'">logout</button>
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
			<th bgcolor="orange">��ȣ</th>
			<th bgcolor="orange">����</th>
			<th bgcolor="orange">�ۼ���</th>
			<th bgcolor="orange" width="150">�����</th>
		</tr>
		<c:forEach items="${boardList }" var="board">
		<tr>
			<td>${board.bseq }</td>
			<td align="left">
			<a href="getBoard.do?seq=${board.bseq }">${board.title }</a>
			</td>
			<td>${board.id }</td>
			<td>${board.regDate }</td>
		</tr>
		</c:forEach>
	</table>
	</form>
</center>
</body>
</html>