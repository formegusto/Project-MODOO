<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoService.css?d"></link>
<script type="text/javascript" src="styles/js/infoService.js?a"></script>
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
	<div class="sideContent">
		<div class="sideItem active" onClick="location.href='infoService.do'">수집 중!</div>
		<div class="sideItem" onClick="location.href='tmService.do'">텍스트마이닝</div>
		<div class="sideItem" onClick="location.href='visualService.do'">시각화</div>
	</div>
	<div class="contents">
		<ul class="contentNav">
			<li class="navItem active">
				리스트 보는 중!
			</li>
			<li class="navItem" onclick="location.href='frameService.do'">
				프레임
			</li>
		</ul>
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword('info')">검색</button>
		</div>
		<div class="cardGroup">
			<c:forEach items="${infoList }" var="info">
				<div class="contentCard">
					<h1 onclick="location.href='dataService.do?iseq=${info.iseq}&mode=read'">${info.title }</h1>
					<hr/>
					<c:forEach items="${info.dataList }" var="data">
						<p onclick="location.href='dataService.do?iseq=${info.iseq}&mode=read'">
							${data }
						</p>
					</c:forEach>
					<button class="delBtn" onclick="location.href='deleteInfo.do?iseq=${info.iseq }'">X</button>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="onInsertPage('crawling')">CRAWLING</div>
		<div class="sideItem" onclick="onInsertPage('csv')">CSV</div>
	</div>
</section>
</body>
</html>