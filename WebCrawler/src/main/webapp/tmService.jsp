<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmService.css?33"></link>
<script type="text/javascript" src="styles/js/tmService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">수집</div>
		<div class="sideItem active" onClick="location.href='tmService.jsp'">텍스트마이닝 중!</div>
		<div class="sideItem">시각화</div>
	</div>
	<div class="contents active" id="tmList">
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					이거 길게 안써서 나한테 그러는건가? 너무하다 ㅠㅠㅠㅠㅠ;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					이거 길게 안써서 나한테 그러는건가? 너무하다 ㅠㅠㅠㅠㅠ;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					이거 길게 안써서 나한테 그러는건가? 너무하다 ㅠㅠㅠㅠㅠ;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					이거 길게 안써서 나한테 그러는건가? 너무하다 ㅠㅠㅠㅠㅠ;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					이거 길게 안써서 나한테 그러는건가? 너무하다 ㅠㅠㅠㅠㅠ;
					</p>
			</div>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard" onClick="location.href='infoServiceByTm.do'">
			<h1>WordCloud</h1>
			<hr/>
					<p>
					단어의 빈도수를 계산하여 WordCloud로 표현 해 줍니다.
					</p>
			</div>
			<div class="contentCard">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					각 단어들 간의 연관성을 연결성을 통해 표현 해 줍니다.
					</p>
			</div>
			<div class="contentCard">
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