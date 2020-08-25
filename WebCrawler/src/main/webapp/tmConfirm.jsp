<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/css/tmConfirm.css?s"></link>
<script type="text/javascript" src="styles/js/tmConfirm.js?1"></script>
<meta content="text/html; charset=EUC-KR">
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="contentsNav">
			${tc.comment }
		</div>
		<div class="contentsItem">
			<c:if test="${ttype eq 'wordcloud' }">
				<jsp:include page="/confirmRview/${viewSeq }.html"/>
			</c:if>
			<c:if test="${ttype eq 'sna' }">
				<img src="confirmRview/${viewSeq }.png"/>
			</c:if>
			<form class="tmform" name="tmform" method="post" action="tmAdd.do" >
				<input type="hidden" name="iseq" value="${viewSeq }"/>
				<input type="hidden" name="ttype" value="${ttype }"/>
				<input type="text" name="title" placeholder="당신의 ${ttypeString }에 이름을 지어주세요!"/>
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