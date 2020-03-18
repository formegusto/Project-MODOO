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
	<h3>${user.name }님 환영합니다.</h3>
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