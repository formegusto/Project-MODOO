<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoService.css?e"></link>
<script type="text/javascript" src="styles/js/infoService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active" onClick="location.href='infoService.do'">���� ��!</div>
		<div class="sideItem" onClick="location.href='tmService.do'">�ؽ�Ʈ���̴�</div>
		<div class="sideItem" onClick="location.href='visualService.do'">�ð�ȭ</div>
	</div>
	<div class="contents">
		<ul class="contentNav">
			<li class="navItem" onclick="location.href='infoService.do'">
				����Ʈ
			</li>
			<li class="navItem active">
				������ ���� ��!
			</li>
		</ul>
		<c:forEach items="${frameList }" var="frame">
					<div class="contentCard" onclick="location.href='dataService.do?fseq=${frame.fseq}&mode=read'">
					<h1>${frame.title }</h1>
					<hr/>
					<c:forEach items="${frame.dataList }" var="data">
						<p>
							${data }
						</p>
					</c:forEach>
				</div>
		</c:forEach>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="location.href='infoServiceByFrame.do'">������ �����</div>
	</div>
</section>
</body>
</html>