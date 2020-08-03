<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/frameConfirm.css?e"></link>
<script type="text/javascript" src="styles/js/frameConfirm.js?x"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<input id="titleInput" class="sideItem input" type="text" placeholder="저의 이름을 지어주세요..!"/>
	</div>
	<form name="saveFrame" method="post" action="frameMake.do">
		<input type="text" name="title" value="" id="title"/>
		<table>
			<tbody>
				<c:forEach items="${infoList }" var="info">
					<tr>
						<td class="fieldHeader">
						<input type="hidden" name="iseqList" value="${info.iseq }" />
							${info.field }
						</td>
						<c:forEach items="${info.dataList }" var="data">
							<td>
								${data }
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onClick('save')">SAVE</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>