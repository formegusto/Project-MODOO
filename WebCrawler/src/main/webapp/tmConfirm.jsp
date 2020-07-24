<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/css/tmConfirm.css?2"></link>
<script type="text/javascript" src="styles/js/tmConfirm.js?1"></script>
<meta content="text/html; charset=UTF-8">
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="contentsNav">
			잘 구워진 워드클라우드 결과물!
		</div>
		<div class="contentsItem">
			<jsp:include page="/confirmRview/${viewSeq }.html"/>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem">SAVE</div>
		<div class="sideItem" onclick="location.href='tmService.jsp'">CANCLE</div>
	</div>
</section>

</body>
</html>