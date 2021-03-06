<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmService.css?g"></link>
<script type="text/javascript" src="styles/js/tmService.js?1"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">수집</div>
		<div class="sideItem active" onClick="location.href='tmService.do'">텍스트마이닝 중!</div>
		<div class="sideItem" onClick="location.href='visualService.do'">시각화</div>
		<div class="sideItem" onClick="location.href='signout.do'">떠나기</div>
	</div>
	<div class="contents active" id="tmList">
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword()">검색</button>
		</div>
		<c:forEach items="${tmList }" var="tm">
			<div class="contentCard">
			<h1>${tm.title }</h1>
			<hr/>
				<div class="contentItem">
					<c:if test="${tm.ttype eq 'wordcloud' }">
						<c:import url="/userRview/${tm.tseq }.html" charEncoding="EUC-KR" />
					</c:if>
					<c:if test="${tm.ttype eq 'sna'}">
						<img src="userRview/${tm.tseq }.png"/>
					</c:if>
					<c:if test="${tm.ttype eq 'sentiment'}">
						<canvas class="sentiment ${tm.tseq }"></canvas>
						<script>
								new Chart(document.getElementsByClassName('sentiment ' + ${tm.tseq})[0], {
							    // The type of chart we want to create
							    type: 'pie',
	
							    // The data for our dataset
							    data: {
							        labels: ['긍정','부정','중립'],
							        datasets: [{
							            label: '감성분석 결과',
							            backgroundColor: ['rgb(000,051,255)','rgb(255,051,051)','rgb(255,102,051)'],
							            borderColor: 'rgb(0,0,0)',
							            data: [${tm.tvi.positive},${tm.tvi.negative},${tm.tvi.neutral}]
							        }]
							    	},
								});
								
								console.log(ctx.style.width);
								console.log(ctx.style.height);
						</script>
					</c:if>
				</div>
				<button class="delBtn" onclick="location.href='deleteTm.do?tseq=${tm.tseq }'">X</button>
			</div>
		</c:forEach>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do?ttype=wordcloud'">
			<h1>WordCloud</h1>
			<hr/>
					<p>
					단어의 빈도수를 계산하여 WordCloud로 표현 해 줍니다.
					</p>
			</div>
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do?ttype=sna'">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					각 단어들 간의 연관성을 연결성을 통해 표현 해 줍니다.
					</p>
			</div>
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do?ttype=sentiment'">
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