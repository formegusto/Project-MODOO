<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoInsert.css?e"></link>
<script type="text/javascript" src="styles/js/infoInsert.js?e"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	결과가 안나와도 울지마세요.<br/>
	인생은 그런겁니다 :)
</div>
<jsp:include page="components/header.html"/>
<section> 
	<ul class="formNav">
		<li onclick="changeType(this,'text')">
			<div class="typeText">
				Text
			</div>
			<div class="checkText">
				check!
			</div>
		</li>
		<li onclick="changeType(this,'link')">
			<div class="typeText">
				Link
			</div>
			<div class="checkText">
				check!
			</div>
		</li>
		<li>LinkList</li>
	</ul>
	<div class="formBox">
		<form class="infoForm" name="info" action="confirmData.do" method="post">
			<div class="inputGroup">
				<input type="text" name="title" placeholder="title!"/>
			</div>
			<div class="inputGroup">
				<textarea name="content" placeholder="content!"></textarea>
			</div>
			<div class="inputGroup">
				<input type="text" name="link" placeholder="link!"/>
			</div>
			<div class="inputGroup">
				<input type="text" name="field" placeholder="field!"/>
				<input type="text" name="cssQuery" placeholder="cssQuery!"/>
			</div>
			<input type="hidden" name="itype" value="" id="itype"/>
		</form>
	</div>
	<ul class="formNavBottom">
		<li onclick="onClick('cancle')">Cancle</li>
		<li onclick="onClick('confirm')">Confirm</li>
	</ul>
</section>
</body>
</html>