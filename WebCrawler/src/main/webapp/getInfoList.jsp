<%@page import="java.util.List"%>
<%@page import="com.crawler.biz.info.impl.InfoDAO"%>
<%@page import="com.crawler.biz.info.InfoVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Crawler List</title>
</head>
<body>
<center>
	<h1>Crawler List</h1>
	<h3>테스트님 환영합니다.</h3>
	
	<!--  검색 시작 -->
	<form action="getInfoList.jsp" method="post">
		<table border="1" cellspadding="0" cellspacing="0"  width="700">
			<tr>
				<td align="right">
					<select name="searchCondition">
						<option value="TITLE">제목
						<option value="CONTENT">내용
					</select>
					<input name="searchKeyword" type="text"/>
					<input type="submit" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
	<!-- 검색 종료 -->
	
	<table border="1" cellspadding="0" cellspacing="0"  width="700">
		<tr>
			<th bgcolor="orange" width="100">번호</th>
			<th bgcolor="orange" width="200">제목</th>
			<th bgcolor="orange" width="150">등록일</th>
		</tr>
		<c:forEach items="${infoList }" var="info">
		<tr>
			<td>${info.seq }</td>
			<td align="left"><a href="getInfo.do?seq=${info.seq }">
			${info.title }
			</a></td>
			<td>${info.regDate }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<a href="crawlerAdd.jsp">New Cralwer</a>
</center>
</body>
</html>