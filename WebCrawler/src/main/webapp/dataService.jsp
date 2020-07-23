<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataService.css?d"></link>
<script type="text/javascript" src="styles/js/dataService.js?ferf"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<form>
		<table id="dataTable">
			<thead>
				<tr>
					<td>
						${info.field }
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dataList }" var="datavo">
					<tr>
						<td onclick="allDataContent(this)">
							${datavo.data }
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem">CUT</div>
		<div class="sideItem">REPLACE</div>
		<div class="sideItem">DELETE</div>
	</div>
</section>
</body>
</html>