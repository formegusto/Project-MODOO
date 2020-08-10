<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoService.css?e"></link>
<script type="text/javascript" src="styles/js/infoService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active" onClick="location.href='infoService.do'">수집 중!</div>
		<div class="sideItem" onClick="location.href='tmService.do'">텍스트마이닝</div>
		<div class="sideItem" onClick="location.href='visualService.do'">시각화</div>
	</div>
	<div class="contents">
		<ul class="contentNav">
			<li class="navItem" onclick="location.href='infoService.do'">
				리스트
			</li>
			<li class="navItem active">
				프레임 보는 중!
			</li>
		</ul>
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword('${info.iseq}','read')">검색</button>
		</div>
		<div class="cardGroup">
			<c:forEach items="${frameList }" var="frame">
					<div class="contentCard" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">
					<h1>${frame.title }</h1>
					<hr/>
						<p>
							띵킹띵킹
						</p>
						<p>
							띵킹띵킹
						</p>
						<p>
							띵킹띵킹
						</p>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="location.href='infoServiceByFrame.do'">프레임 만들기</div>
	</div>
</section>
</body>
</html>