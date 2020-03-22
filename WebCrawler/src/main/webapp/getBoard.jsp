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
				<input type="submit" value="New Chat" onclick="javascript: form.action='roomAdd_proc.do'"/>
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
	</form>
</center>
</body>
</html>