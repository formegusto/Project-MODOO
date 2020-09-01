<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/boardMake.css?s"></link>
<script type="text/javascript" src="styles/js/boardMake.js?g"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	<h5 style="margin:0;">������ �����ּ���!</h5>
	<hr style="width: 100%;"/>
	<form name="board" method="post" action="boardMake.do">
		<input type="hidden" name="fseq" id="fseq"/>
		<input type="hidden" name="tseq" id="tseq"/>
		<input type="hidden" name="vseq" id="vseq"/>
		<input type="hidden" name="type" id="type"/>
		<div class="btnGrp">
			<button type="button" onclick="typeClick(this,'board')">�Խù��� �����</button>
			<button type="button" onclick="typeClick(this,'room')">ä�ù����� �����</button>
		</div>
		<input type="text" name="title" id="title" placeholder="����" />
		<textarea placeholder="����" name="content"></textarea>
		<button type="button" onclick="onMake()">�����</button>
	</form>
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">		
		<div class="subContent">
			<div class="contentHeader">
				������
			</div>
			<c:forEach items="${frameList}" var="frame">
				<div class="content" onmouseenter="titleEnter(this)" onmouseleave="titleLeave(this)" onclick="titleClick(this,${frame.fseq},'frame')">
					${frame.title }
				</div>
			</c:forEach>
		</div>
		<div class="subContent">
			<div class="contentHeader">
				�ؽ�Ʈ���̴�
			</div>
			<c:forEach items="${tmList}" var="tm">
				<div class="content" onmouseenter="titleEnter(this)" onmouseleave="titleLeave(this)" onclick="titleClick(this,${tm.tseq},'tm')">
					${tm.title }
				</div>
			</c:forEach>
		</div>
		<div class="subContent">
			<div class="contentHeader">
				�ð�ȭ
			</div>
			<c:forEach items="${visualList}" var="visual">
				<div class="content" onmouseenter="titleEnter(this)" onmouseleave="titleLeave(this)" onclick="titleClick(this,${visual.vseq},'visual')">
					${visual.title }
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="makeClick()">MAKE</div>
		<div class="sideItem" onclick="location.href='boardService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>