<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/visualService.css?e"></link>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script type="text/javascript" src="styles/js/visualService.js"></script>
<script>
window.addEventListener("load", function() {
	let visualCanvas = document.getElementsByClassName('visual');
	let typeList = ['pie','doughnut','polarArea','bar', 'line'];
	
	for(let i=0; i<visualCanvas.length; i++){
		visualDraw(visualCanvas[i],typeList[i]);	
	}
})
</script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">����</div>
		<div class="sideItem" onClick="location.href='tmService.do'">�ؽ�Ʈ���̴�</div>
		<div class="sideItem active">�ð�ȭ ��!</div>
	</div>
	<div class="contents active" id="tmList">
			<div class="contentCard">
			<h1>�����1</h1>
			<hr/>
				<canvas class="visual"></canvas>
			</div>
			<div class="contentCard">
			<h1>�����2</h1>
			<hr/>
				<canvas class="visual"></canvas>
			</div>
			<div class="contentCard">
			<h1>�����2</h1>
			<hr/>
				<canvas class="visual"></canvas>
			</div>
			<div class="contentCard">
			<h1>�����2</h1>
			<hr/>
				<canvas class="visual"></canvas>
			</div>
			<div class="contentCard">
			<h1>�����2</h1>
			<hr/>
				<canvas class="visual"></canvas>
			</div>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=line'">
			<h1>Line</h1>
			<hr/>
				<div>
					<img src="styles/png/line.PNG" />
					<p>
						����.. ����.. (���α׸��� �Ҹ� �Դϴ�.)
					</p>
				</div>
			</div>
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=bar'">
			<h1>Bar</h1>
			<hr/>
				<div>
					<img src="styles/png/bar.PNG"/>
					<p>
						�����ڴ� ���ڹٸ� �����մϴ�!
					</p>
				</div>
			</div>
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=polararea'">
			<h1>PolarArea</h1>
			<hr/>
				<div>
					<img src="styles/png/polararea.PNG"/>
					<p>
						����Ƹ���? �����Ƹ���? ����?
					</p>
				</div>
			</div >
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=pie'">
			<h1>Pie</h1>
			<hr/>
				<div>
					<img src="styles/png/pie.PNG"/>
					<p>
						���� ���� �԰� �ͳ׿�.
					</p>
				</div>
			</div>
			<div class="contentCard visual" onClick="location.href='infoServiceByVisual.do?vtype=doughnut'">
			<h1>Doughnut</h1>
			<hr/>
				<div>
					<img src="styles/png/doughnut.PNG"/>
					<p>
						���� ������. ���ְ� ��������.
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