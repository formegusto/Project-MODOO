<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/css/visualConfirm.css?6"></link>
<script type="text/javascript" src="styles/js/visualConfirm.js?4"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
window.addEventListener("load", function() {
	let visualCanvas = document.getElementsByClassName('visual')[0];
	
	let labels = ${strList};
	let datas = ${numList};
	let color = ${color};
	let vtype = ${vtype};
	
	console.log(labels);
	console.log(datas);
	console.log(color);
	
	visualDraw(visualCanvas, vtype ,labels, datas, color);
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
			<form name="tmform" method="post" action="visualService.do" >
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