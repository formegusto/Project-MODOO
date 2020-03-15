<%@page import="java.util.List"%>
<%@page import="com.crawler.biz.data.DataVO"%>
<%@page import="com.crawler.biz.data.impl.DataDAO"%>
<%@page import="com.crawler.biz.info.impl.InfoDAO"%>
<%@page import="com.crawler.biz.info.InfoVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Crawler SELECT Page</title>
</head>
<body>
<center>
	<h1>Crawler SELECT Page</h1>
	<hr/>
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
		<c:forEach items="${dataList }" var="dat">
		<tr>
			<td align="left">${info.field }</td>
			<td>${dat.data }</td>
		</tr>
		</c:forEach>
	</table>
	<hr>
	<a href="getInfoList.do">글목록</a>	
</center>
</body>
</html>