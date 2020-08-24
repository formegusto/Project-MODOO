<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataService.css?eff"></link>
<script type="text/javascript" src="styles/js/updateDataService.js?e"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	<table>
		<thead>
			<tr class="theadTitle">
				<td colspan="3">바뀌는 데이터를 확인해주세요!</td>
			</tr>
			<tr class="theadContent">
				<td colspan="3">
					<div onClick="onUpSubmit()">완벽하다!</div>
					<div onclick="submitCancle()">다시볼게요.</div>
				</td>
			</tr>
		</thead>
		<tbody class="updateTable">
		</tbody>
	</table>
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=read'">읽기</div>
		<div class="sideItem active">수정 중!</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=delete'">삭제</div>
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
						<button type="button" onclick="onKeyword('${info.iseq}','update')">검색</button>
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
						<td class="dataItem">
							<textarea class="updateData ${datavo.dseq }" 
								onfocus="changeStart(this)" 
								onblur="changeEnd(this,${datavo.dseq})">${datavo.data }</textarea>
							<div class="updateConfirm ${datavo.dseq }">수정된 데이터</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onCheck()">수정</div>
		<hr/>
		<div class="sideItem">인풋</div>
		<div class="sideItem" onclick="location.href='infoService.do'">CANCLE</div>
	</div>
	<form name="updateForm" method="post" action="updateData.do" style="display:none">
		<input type="hidden" name="iseq" value="${info.iseq }" />
		<div class="inputDseqList">
		</div>
		<div class="inputDataList">
		</div>
	</form>
</section>
</body>
</html>