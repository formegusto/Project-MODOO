<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Board Confirm Page</title>
</head>
<body>
<center>
	<h1>Board Confirm Page</h1>
	<hr/>
	<form method="post" name="form">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange" width="70">제목</td>
			<td align="left"><input name="title" type="text"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">작성자</td>
			<td align="left"><input type="text" name="id" size="10" value="${user.id }"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">내용</td>
			<td align="left">
				<textarea name="content" cols="40" rows="10"></textarea>
			</td>
		</tr>
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
		</table>
	<hr/>
	</c:forEach>
	<input type="submit" value="취소" onclick="javascript: form.action='getInfoList.do'"/>
	<input type="submit" value="등록" onclick="javascript: form.action='boardAdd_proc.do'"/>
	</form>
</center>
</body>
</html>