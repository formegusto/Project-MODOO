<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/css/tmConfirm.css?4"></link>
<script type="text/javascript" src="styles/js/tmConfirm.js?1"></script>
<meta content="text/html; charset=EUC-KR">
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="contentsNav">
			�� ������ ����Ŭ���� �����!
		</div>
		<div class="contentsItem">
			<jsp:include page="/confirmRview/${viewSeq }.html"/>
			<form name="tmform" method="post" action="tmAdd.do" >
				<input type="hidden" name="iseq" value="${viewSeq }"/>
				<input type="hidden" name="ttype" value="info:wordcloud"/>
				<input type="text" name="title" placeholder="����� ����Ŭ���忡 �̸��� �����ּ���!"/>
			</form>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="document.tmform.submit()">SAVE</div>
		<div class="sideItem" onclick="location.href='tmService.do'">CANCLE</div>
	</div>
</section>

</body>
</html>