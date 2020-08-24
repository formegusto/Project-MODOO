<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoConfirm.css?a"></link>
<script type="text/javascript" src="styles/js/infoConfirm.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<form name="saveInfo" method="post" action="insertInfo.do">
		<table>
			<thead>
				<tr>
					<td>
						${info.field }
						<input type="hidden" name="id" value="${info.id }"/>
						<input type="hidden" name="title" value="${info.title }"/>
						<input type="hidden" name="content" value="${info.content }"/>
						<input type="hidden" name="field" value="${info.field }"/>
						<input type="hidden" name="link" value="${info.link }"/>
						<input type="hidden" name="cssQuery" value="${info.cssQuery }"/>
						<input type="hidden" name="itype" value="${info.itype }"/>
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dataList }" var="datavo">
					<tr>
						<td>
							${datavo.data }
							<input type="hidden" name="data" value="${datavo.data }"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onClick('save')">SAVE</div>
		<div class="sideItem">CANCLE</div>
	</div>
</section>
</body>
</html>