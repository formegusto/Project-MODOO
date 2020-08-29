<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataServiceByFrame.css"></link>
<script type="text/javascript" src="styles/js/updateDataServiceByFrame.js"></script>
<title>MODOO</title>
</head>
<body>
<div class="loading">
	<table class="loadingTable">
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
<div class="csvloading">
	<h5 style="margin:0;">제목을 적어주세요!</h5>
	<hr style="width: 100%;"/>
	<input type="text" id="ctitle" placeholder="제목" />
	<button type="button" onclick="submitDownload()">만들기</button>
	<button type="button" onclick="onDownloadCancle()">취소</button>
</div>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">읽기</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=write'">쓰기</div>
		<div class="sideItem active">수정 중!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=delete'">삭제</div>
	</div>
	<form name="saveFrame" method="post" action="frameMake.do">
		<input type="text" name="title" value="" id="title"/>
		<div class="searchBar">
			<div>
				<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
				<span class="textBottomEffect"></span>
			</div>
			<button type="button" onclick="onKeyword('${frame.fseq}','update')">검색</button>
		</div>
		<table>
			<c:forEach items="${fhiList }" var="fhi">
			<tbody>
					<tr>
						<td class="fieldHeader">
							${fhi.field }
						</td>
						<c:forEach items="${fhi.dataList }" var="dvo">
							<td class="dataItem">
								<textarea class="updateData ${dvo.dseq }" 
									onfocus="changeStart(this)" 
									onblur="changeEnd(this,${dvo.dseq})">${dvo.data }</textarea>
								<div class="updateConfirm ${dvo.dseq }">수정된 데이터</div>
							</td>
						</c:forEach>
					</tr>
			</tbody>
			</c:forEach>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onCheck()">수정</div>
		<hr/>
		<div class="sideItem" onclick="onDownload(${frame.fseq})">CSV 다운로드</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
	<form name="updateForm" method="post" action="updateDataByFrame.do" style="display:none">
		<input type="hidden" name="fseq" value="${frame.fseq}" />
		<div class="inputDseqList">
		</div>
		<div class="inputDataList">
		</div>
	</form>
</section>
</body>
</html>