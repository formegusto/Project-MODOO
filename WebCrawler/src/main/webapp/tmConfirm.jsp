<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/css/tmConfirm.css?g"></link>
<script type="text/javascript" src="styles/js/tmConfirm.js?g"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<c:if test="${ttype eq 'sentiment'}">
		<script>
		window.addEventListener("load", function() {
			let sentiCanvas = document.getElementsByClassName('sentiment')[0];
			
			let positive = ${positive};
			let negative = ${negative};
			let neutral = ${neutral};
			
			sentiDraw(sentiCanvas, positive , negative, neutral);
		})
		</script>		
</c:if>
<meta content="text/html; charset=EUC-KR">
<title>MODOO</title>
</head>
<body>
<c:if test="${ttype eq 'wordcloud' }">
<div class="loading">
	<div class="loadingHeader">
		워드클라우드 결과물을<br/>
		프레임으로도 저장할까요?
	</div>
	<hr/>
	<div class="loadingContent">
		<div class="loadingItem" onclick="wordcloudSave('yes')">예</div>
		<div class="loadingItem" onclick="wordcloudSave('no')">아니오</div>
	</div>
</div>
</c:if>
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
			<c:if test="${ttype eq 'sna'}">
				<img src="confirmRview/${viewSeq }.png"/>
			</c:if>
			<c:if test="${ttype eq 'sentiment'}">
				<canvas class="sentiment"></canvas>
			</c:if>
			<form class="tmform" name="tmform" method="post" action="tmAdd.do" >
				<c:if test="${ttype eq 'sentiment'}">
					<input type="hidden" name="positive" value="${positive}"/>
					<input type="hidden" name="negative" value="${negative}"/>
					<input type="hidden" name="neutral" value="${neutral}"/>
				</c:if>
				<input type="hidden" name="iseq" value="${viewSeq }"/>
				<input type="hidden" name="ttype" value="${ttype }"/>
				<input type="text" name="title" placeholder="당신의 ${ttypeString }에 이름을 지어주세요!"/>
				<c:if test="${ttype eq 'wordcloud' }">
					<input type="hidden" name="wordList" value="${wordList }" />
					<input type="hidden" name="cntList" value="${cntList }" />
					<input type="hidden" name="saveFrame" value="" class="saveFrame"/>
				</c:if>
			</form>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="save('${ttype}')">SAVE</div>
		<div class="sideItem" onclick="location.href='tmService.do'">CANCLE</div>
	</div>
</section>

</body>
</html>