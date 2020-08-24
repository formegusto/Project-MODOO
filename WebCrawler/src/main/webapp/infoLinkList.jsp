<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoLinkList.css?c"></link>
<script type="text/javascript" src="styles/js/infoLinkList.js?z"></script>
<title>MODOO</title>
</head>
<body>
<div class="infoType">
	<div class="infoTypeGrp">
		<div class="infoTypeTitle">
		INFO의 유형을 골라주세요!
		</div>
	</div>
	<hr/>
	<div class="infoTypeGrp">
		<div class="type" onclick="selInfoType('text')">TEXT</div>
		<div class="type" onclick="selInfoType('link')">LINK</div>
		<div class="type" onclick="selInfoType('linklist')">LINKLIST</div>
		<div class="type" onclick="selInfoType('cancle')">CANCLE</div>
	</div>
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<ul class="contentNav">
			<li class="navItem">
				진행하시기 전에 사용하실 Link 데이터를 선택해주세요!
			</li>
		</ul>
		<div class="cardGroup">
			<c:forEach items="${infoList }" var="info">
				<div class="contentCard" onclick="location.href='infoInsert.do?type=linklist&iseq=${info.iseq }'">
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
		<div class="sideItem" onclick="location.href='infoService.do'">Cancle</div>
	</div>
</section>
</body>
</html>