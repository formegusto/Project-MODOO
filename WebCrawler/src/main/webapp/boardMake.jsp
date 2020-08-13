<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/boardMake.css?3"></link>
<script type="text/javascript" src="styles/js/boardMake.js"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">		
		<div class="subContent">
			<div class="contentHeader">
				프레임
			</div>
			<div class="content" onmouseenter="titleEnter(this)" onmouseleave="titleLeave(this)">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
		</div>
		<div class="subContent">
			<div class="contentHeader">
				텍스트마이닝
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
		</div>
		<div class="subContent">
			<div class="contentHeader">
				비주얼
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
			<div class="content">
				제목
			</div>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="">MAKE</div>
		<div class="sideItem" onclick="location.href='boardService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>