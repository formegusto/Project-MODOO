<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataService.css"></link>
<script type="text/javascript" src="styles/js/dataService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=read'">읽기</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=write'">쓰기</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=update'">수정</div>
		<div class="sideItem active">삭제 중!</div>
	</div>
	<form>
		<table id="dataTable">
			<thead>
				<tr>
					<td class="searchBar">
						<div>
							<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
							<span class="textBottomEffect"></span>
						</div>
						<button type="button" onclick="onKeyword('${info.iseq}','delete')">검색</button>
					</td>
				</tr>
				<tr>
					<td class="dataItem">
						${info.field }
					</td>
				</tr>
			</thead>
			<tbody class="dataTbody">
				<c:forEach items="${dataList }" var="datavo">
					<tr>
						<td class="dataItem">
							${datavo.data }
							<div class="dataTools" onclick="pushDelData(this,${datavo.dseq})">
								지나간 데이터는 돌아오지 않습니다.
							</div>
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
		<div class="sideItem" onclick="onDelSubmit()">삭제</div>
		<div class="sideItem" onclick="location.href='infoService.do'">CANCLE</div>
	</div>
	<form name="delForm" method="post" action="deleteData.do" style="display:none">
		<input type="hidden" name="iseq" value="${info.iseq }" />
		<input id="delDseqInput" type="hidden" name="dseqList" value=""/>
	</form>
</section>
</body>
</html>