<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmService.css?e"></link>
<script type="text/javascript" src="styles/js/tmService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">수집</div>
		<div class="sideItem active" onClick="location.href='tmService.do'">텍스트마이닝 중!</div>
		<div class="sideItem" onClick="location.href='visualService.do'">시각화</div>
	</div>
	<div class="contents active" id="tmList">
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword('${info.iseq}','read')">검색</button>
		</div>
		<c:forEach items="${tmList }" var="tm">
			<div class="contentCard">
			<h1>${tm.title }</h1>
			<hr/>
				<div class="contentItem">
					<c:import url="/userRview/${tm.tseq }.html" charEncoding="EUC-KR" />
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do'">
			<h1>WordCloud</h1>
			<hr/>
					<p>
					단어의 빈도수를 계산하여 WordCloud로 표현 해 줍니다.
					</p>
			</div>
			<div class="contentCard tm">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					각 단어들 간의 연관성을 연결성을 통해 표현 해 줍니다.
					</p>
			</div>
			<div class="contentCard tm">
			<h1>SentimentAnalysis</h1>
			<hr/>
					<p>
					단어 별 긍정, 중립, 부정, 3가지의 감성을 분석하여 표현 해 줍니다.
					</p>
			</div>
	</div>
	<div class="sideContent">
		<div class="sideItem active" onclick="onChange(this,'list')" id="firstActive">My List</div>
		<div class="sideItem" onclick="onChange(this,'makeList')">Make TM</div>
	</div>
</section>
</body>
</html>