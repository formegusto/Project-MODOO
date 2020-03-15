<%@page import="com.crawler.biz.info.InfoVO"%>
<%@page import="com.crawler.biz.common.WCrawl"%>
<%@page import="com.crawler.biz.data.DataVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Crawler Add Page</title>
<link rel="stylesheet" href="resources/css/loading.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(document).on("click","#submit",function(){
		$('#loading').show();
		$('#loading-image').show();
	});
</script>
</head>
<body>
<div id="loading">
	<img id="loading-image" src="images/viewLoading.gif" alt="Loading..."/>
</div>
<center>
	<h1>Crawler Confirm Page</h1>
	<hr/>
<form method="post" action="crawlerAdd_proc.do">
	<hr/>
	<h1>${info.title }<input type="hidden" name="title" value="${info.title }"/></h1>
	<h2>${info.link }<input type="hidden" name="link" value="${info.link }"/></h2><br/>
	<h3>${info.content }<input type="hidden" name="content" value="${info.content }"/></h3><input type="submit" value="Crawler Add"/>
	<input type="hidden" name="field" value="${info.field }"/>
	<input type="hidden" name="cssQuery" value="${info.cssQuery }"/>
	<hr/>
	<h1>DataList</h1>
	<c:forEach items="${dataList }" var="dat">
		${dat.data } <br/>
		<input type="hidden" name="data" value="${dat.data }"/>
	</c:forEach>
</form>	
</center>
</body>
</html>