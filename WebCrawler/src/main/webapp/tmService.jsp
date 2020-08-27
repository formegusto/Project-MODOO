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
		<div class="sideItem" onClick="location.href='infoService.do'">����</div>
		<div class="sideItem active" onClick="location.href='tmService.do'">�ؽ�Ʈ���̴� ��!</div>
		<div class="sideItem" onClick="location.href='visualService.do'">�ð�ȭ</div>
		<div class="sideItem" onClick="location.href='signout.do'">������</div>
	</div>
	<div class="contents active" id="tmList">
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="����� ���ϴ� �˻���� ���󰡰ھ�." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword()">�˻�</button>
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
							        labels: ['����','����','�߸�'],
							        datasets: [{
							            label: '�����м� ���',
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
					�ܾ��� �󵵼��� ����Ͽ� WordCloud�� ǥ�� �� �ݴϴ�.
					</p>
			</div>
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do?ttype=sna'">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					�� �ܾ�� ���� �������� ���Ἲ�� ���� ǥ�� �� �ݴϴ�.
					</p>
			</div>
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do?ttype=sentiment'">
			<h1>SentimentAnalysis</h1>
			<hr/>
					<p>
					�ܾ� �� ����, �߸�, ����, 3������ ������ �м��Ͽ� ǥ�� �� �ݴϴ�.
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