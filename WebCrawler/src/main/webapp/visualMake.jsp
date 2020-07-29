<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/visualMake.css?5"></link>
<script type="text/javascript" src="styles/js/visualMake.js?5"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	<table>
		<thead>
			<tr class="theadTitle">
				<td colspan="3">당신의 최애 색! 선택하시죠</td>
			</tr>
			<tr class="theadContent">
				<td colspan="3">
					<div onclick="submitCancle()">아 잠만요 다시할게요</div>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="pink" onmouseenter="onColor('pink')" onmouseleave="offColor('pink')" onclick="onSubmit('pink')">
					<div class="color pink">이거다!</div>
				</td>
			</tr>
			<tr>
				<td class="skyblue" onmouseenter="onColor('skyblue')" onmouseleave="offColor('skyblue')" onclick="onSubmit('skyblue')">
					<div class="color skyblue">이거다!</div>
				</td>
			</tr>
			<tr>
				<td class="green" onmouseenter="onColor('green')" onmouseleave="offColor('green')" onclick="onSubmit('green')">
					<div class="color green">이거다!</div>
				</td>
			</tr>
			<tr>
			<td class="purple" onmouseenter="onColor('purple')" onmouseleave="offColor('purple')" onclick="onSubmit('purple')">
					<div class="color purple">이거다!</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<c:forEach items="${infoList }" var="info">
				<div class="contentCard ${info.iseq }">
					<h1>${info.title }</h1>
					<hr/>
					<c:forEach items="${info.dataList }" var="data">
						<p>
							${data }
						</p>
					</c:forEach>
					<div class="checkBlind ${info.iseq }">
						<p class="ptag ${info.iseq}">이 데이터 리스트는 뭐죠?</p>
						<div class="buttonGroup ${info.iseq }">
							<button onclick="onVisual(${info.iseq},'number')">숫자다</button>
							<button onclick="onVisual(${info.iseq},'string')">문자다</button>
						</div>
						<div class="buttonGroup cancle ${info.iseq }">
							<button onclick="offVisual(${info.iseq})">취소</button>
						</div>
					</div>
				</div>
		</c:forEach>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="location.href='visualService.jsp'">CANCLE</div>
	</div>
	<form action="visualMake.do" method="post" name="visualForm">
		<input type="hidden" id="numIseq" name="numIseq"/>
		<input type="hidden" id="strIseq" name="strIseq"/>
		<input type="hidden" id="color" name="color"/>
		<input type="hidden" id="vtype" name="vtype" value="${vtype }"/>
	</form>
</section>
</body>
</html>