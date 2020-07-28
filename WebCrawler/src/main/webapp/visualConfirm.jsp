<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/css/visualConfirm.css?3"></link>
<script type="text/javascript" src="styles/js/visualConfirm.js?2"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
window.addEventListener("load", function() {
	let visualCanvas = document.getElementsByClassName('visual');
	
	let labels = ${strList};
	let datas = ${numList};
	
	console.log(labels);
	console.log(datas);
	
	visualDraw(visualCanvas, labels, datas);
	/*
	for(let i=0; i<visualCanvas.length; i++){
		visualDraw(visualCanvas[i],typeList[i]);	
	}
	*/
})
</script>
<meta content="text/html; charset=EUC-KR">
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="contentsNav">
			쭈욱 펴진 라인 결과물!
		</div>
		<div class="contentsItem">
			<canvas class="visual"></canvas>
			<form name="tmform" method="post" action="tmAdd.do" >
				<input type="text" name="title" placeholder="당신의 라인에 이름을 지어주세요!"/>
			</form>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="document.tmform.submit()">SAVE</div>
		<div class="sideItem" onclick="location.href='tmService.do'">CANCLE</div>
	</div>
</section>

</body>
</html>