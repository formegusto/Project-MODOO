<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/visualMake.css?2"></link>
<script type="text/javascript" src="styles/js/visualMake.js?3"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	시각화를 생성 중 입니다.<br/>
	기다려주세여.
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<c:forEach items="${infoList }" var="info">
				<div class="contentCard ${info.iseq }">
					<h1>${info.title }</h1>
					<hr/>
					<c:forEach items="${info.dataList }" var="data">
						<p>
							${data }
						</p>
					</c:forEach>
					<div class="checkBlind ${info.iseq }">
						<p class="ptag ${info.iseq}">이 데이터 리스트는 뭐죠?</p>
						<div class="buttonGroup ${info.iseq }">
							<button onclick="onVisual(${info.iseq},'number')">숫자다</button>
							<button onclick="onVisual(${info.iseq},'string')">문자다</button>
						</div>
						<div class="buttonGroup cancle ${info.iseq }">
							<button onclick="offVisual(${info.iseq})">취소</button>
						</div>
					</div>
				</div>
		</c:forEach>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="location.href='visualService.jsp'">CANCLE</div>
	</div>
	<form action="visualMake.do" method="post" name="visualForm">
		<input type="hidden" id="numIseq" name="numIseq"/>
		<input type="hidden" id="strIseq" name="strIseq"/>
	</form>
</section>
</body>
</html>