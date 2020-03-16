<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	<h1>Crawler Add Page</h1>
	<h2>By ${user.name }</h2>
	<hr/>
	<form method="post" action="crawlerConfirm.do">
	<table id=fieldForm border="1">
	<tr>
		<td><input type="text" name="title" placeholder="Input Crawler Title" /></td>
		<td><input type="button" id="fieldAdd" value="Field Add"/></td>
	</tr>
	<tr>
		<td><input type="text" name="link" placeholder="Input Crawling Target URL"/></td>
		<td><input type="submit" value="Crawling Go" id="submit"/><br/></td>
	</tr>
	<tr>
		<td colspan="2"><input type="text" name="content" placeholder="Input Crawling Contents"/></td>
	</tr>
	<tr>
		<td><input type="text" name="field" id="field"/></td>
		<td><input type="text" name="cssQuery" id="cssQuery"/></td>
	</tr>
	</table>
	</form>		
</center>
</body>
</html>