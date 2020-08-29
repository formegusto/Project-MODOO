<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoConfirm.css?e"></link>
<script type="text/javascript" src="styles/js/infoConfirm.js?zz"></script>
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
						<c:if test="${processType eq 're' }">
							<input type="hidden" name="iseq" value="${info.iseq }"/>
						</c:if>
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
	<c:if test="${processType eq 'new' }">
		<div class="sideContent">
			<div class="sideItem" onclick="onClick('save')">SAVE</div>
			<div class="sideItem">CANCLE</div>
		</div>
	</c:if>
	<c:if test="${processType eq 're' }">
		<div class="sideContent">
			<div class="sideItem" onclick="onClick('replace')">Replace</div>
			<div class="sideItem" onclick="onClick('append')">Append</div>
			<div class="sideItem" onclick="location.href='infoService.do'">CANCLE</div>
		</div>
	</c:if>
</section>
</body>
</html>