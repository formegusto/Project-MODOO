<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/frameMake.css?s"></link>
<script type="text/javascript" src="styles/js/frameMake.js?dd"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	�Ƹ��ٿ� �����<br/>
	������ �̸�����!<br/>
	�� �غ� �� �Դϴ�. ȣȫ<br/>
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="cardGroup">
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
						<p class="ptag ${info.iseq}" >���� �������ּ���!</p>
						<div class="buttonGroup ${info.iseq }">
							<button onclick="onFrame(${info.iseq})">������� ���ߴ�</button>
						</div>
						<div class="buttonGroup cancle ${info.iseq }">
							<button onclick="offFrame(${info.iseq})">������ �йи����� ����</button>
						</div>
					</div>
				</div>
		</c:forEach>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="onSubmit()">MAKE</div>
	</div>
	<form name="frameForm" method="post" action="frameConfirm.do" style="display:none">
		<input id="iseqList" type="hidden" name="iseqList" value=""/>
	</form>
</section>
</body>
</html>