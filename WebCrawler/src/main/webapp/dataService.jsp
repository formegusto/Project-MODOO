<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataService.css?s"></link>
<script type="text/javascript" src="styles/js/dataService.js?ee"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem active">읽는 중!</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=update'">수정</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=delete'">삭제</div>
	</div>
	<form onsubmit="return false">
		<table id="dataTable">
			<thead>
				<tr>
					<td class="searchBar">
						<div>
							<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
							<span class="textBottomEffect"></span>
						</div>
						<button type="button" onclick="onKeyword('${info.iseq}','read')">검색</button>
					</td>
				</tr>
				<tr>
					<td class="dataItem">
						${info.field }
					</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dataList }" var="datavo">
					<tr>
						<td onclick="allDataContent(this)" class="dataItem">
							${datavo.data }
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItemInfo">
			${info.title }
			<div class="sideItemToolTip">
				<div class="ToolTipItem">
					${info.link }
				</div>
				<hr/>
				<div class="ToolTipItem">
					${info.cssQuery }
				</div>
			</div>
		</div>
		<hr/>
		<div class="sideItem" onclick="location.href='recrawl.do?iseq=${info.iseq}'">Recrawl</div>
		<div class="sideItem">CANCLE</div>
	</div>
</section>
</body>
</html>