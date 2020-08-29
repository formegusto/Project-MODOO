<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmMake.css"></link>
<script type="text/javascript" src="styles/js/tmMake.js?2"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	텍스트 마이닝 진행 중 입니다.<br/>
	기다려주세여
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="cardGroup">
		<c:forEach items="${infoList }" var="info">
				<div class="contentCard" onclick="onTm(${info.iseq},'${ttype }')">
					<h1>${info.title }</h1>
					<hr/>
					<c:forEach items="${info.dataList }" var="data">
						<p>
							${data }
						</p>
					</c:forEach>
					<div class="checkBlind">
						클릭하시면 바로 진행합니다!
					</div>
				</div>
		</c:forEach>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="location.href='tmService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>