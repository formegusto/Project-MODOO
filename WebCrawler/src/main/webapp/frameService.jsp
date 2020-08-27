<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoService.css?s"></link>
<script type="text/javascript" src="styles/js/infoService.js?e"></script>
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
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="����� ���ϴ� �˻���� ���󰡰ھ�." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword('frame')">�˻�</button>
		</div>
		<div class="cardGroup">
			<c:forEach items="${frameList }" var="frame">
					<div class="contentCard">
					<h1 onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">${frame.title }</h1>
					<hr/>
					<c:forEach items="${frame.dataList }" var="data">
						<p onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">
							${data }
						</p>
					</c:forEach>
					<button class="delBtn" onclick="location.href='deleteFrame.do?fseq=${frame.fseq}'">X</button>
					</div>
			</c:forEach>
		</div>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="location.href='infoServiceByFrame.do'">������ �����</div>
	</div>
</section>
</body>
</html>