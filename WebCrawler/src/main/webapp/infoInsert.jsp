<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoInsert.css?s"></link>
<script type="text/javascript" src="styles/js/infoInsert.js?e"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	����� �ȳ��͵� ����������.<br/>
	�λ��� �׷��̴ϴ� :)
</div>
<jsp:include page="components/header.html"/>
<section> 
	<ul class="formNav">
		<li>
			${type }
		</li>
	</ul>
	<div class="formBox">
		<form class="infoForm" name="info" action="confirmData.do" method="post">
			<div class="inputGroup">
				<input type="text" name="title" placeholder="title!"/>
			</div>
			<div class="inputGroup">
				<textarea name="content" placeholder="content!"></textarea>
			</div>
			<div class="inputGroup">
				<c:if test="${(link ne null and link ne '' )}">
					<input type="text" name="link" placeholder="link!" value="${link }" readonly="readonly"/>
				</c:if>
				<c:if test="${link eq null or link eq '' }">
					<input type="text" name="link" placeholder="link!"/>
				</c:if>
			</div>
			<div class="inputGroup">
				<input type="text" name="field" placeholder="field!"/>
				<c:if test="${itype eq 'empty' }">
					<input type="text" name="cssQuery" placeholder="cssQuery!" value="empty" readonly="readonly"/>
				</c:if>
				<c:if test="${itype ne 'empty' }">
					<input type="text" name="cssQuery" placeholder="cssQuery!" title="ũ���� ������ ��带 ����Ͻø� ���� ã���� �� �ֽ��ϴ�!"/>
				</c:if>
			</div>
			<input type="hidden" name="itype" value=${itype } id="itype"/>
		</form>
	</div>
	<ul class="formNavBottom">
		<li onclick="onClick('cancle')">Cancle</li>
		<li onclick="onClick('confirm')">Confirm</li>
	</ul>
</section>
</body>
</html>