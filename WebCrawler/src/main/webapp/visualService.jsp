<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/visualService.css?s"></link>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script type="text/javascript" src="styles/js/visualService.js?e"></script>
<script>
window.addEventListener("load", function() {
	let visualCanvas = document.getElementsByClassName('visualcanvas');
	
	let i=0;
	let title = [];
	let vtype = [];
	let color = [];
	let labels = [];
	let datas = [];
	<c:forEach items="${visualList }" var="visual">
		title.push('${visual.title}');
		vtype.push(${visual.vtype});
		color.push(${visual.color});
		labels.push(${visual.labels});
		datas.push(${visual.datas});
		
		console.log(title[i]);
		console.log(vtype[i]);
		console.log(color[i]);
		console.log(labels[i]);
		console.log(datas[i]);
		
		visualDraw(visualCanvas[i],title[i],vtype[i],labels[i],datas[i],color[i]);
		i++;
	</c:forEach>
})
</script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">수집</div>
		<div class="sideItem" onClick="location.href='tmService.do'">텍스트마이닝</div>
		<div class="sideItem active">시각화 중!</div>
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
		<c:forEach items="${visualList }" var="visual">
			<div class="contentCard">
			<h1>${visual.title }</h1>
			<hr/>
				<canvas class="visualcanvas"></canvas>
			<button class="delBtn" onclick="location.href='deleteVisual.do?vseq=${visual.vseq}'">X</button>
			</div>
		</c:forEach>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=line'">
			<h1>Line</h1>
			<hr/>
				<div>
					<img src="styles/png/line.PNG" />
					<p>
						쓰윽.. 쓰윽.. (라인그리는 소리 입니다.)
					</p>
				</div>
			</div>
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=bar'">
			<h1>Bar</h1>
			<hr/>
				<div>
					<img src="styles/png/bar.PNG"/>
					<p>
						제작자는 초코바를 좋아합니다!
					</p>
				</div>
			</div>
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=pie'">
			<h1>Pie</h1>
			<hr/>
				<div>
					<img src="styles/png/pie.PNG"/>
					<p>
						애플 파이 먹고 싶네요.
					</p>
				</div>
			</div>
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=doughnut'">
			<h1>Doughnut</h1>
			<hr/>
				<div>
					<img src="styles/png/doughnut.PNG"/>
					<p>
						도넛 맛있죠. 맛있게 만들어봐요.
					</p>
				</div>
			</div>
	</div>
	<div class="sideContent">
		<div class="sideItem active" onclick="onChange(this,'list')" id="firstActive">My List</div>
		<div class="sideItem" onclick="onChange(this,'makeList')">Make Visual</div>
	</div>
</section>
</body>
</html>