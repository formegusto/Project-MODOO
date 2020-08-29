<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataServiceByFrame.css"></link>
<script type="text/javascript" src="styles/js/dataServiceByFrame.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<div class="csvloading">
	<h5 style="margin:0;">제목을 적어주세요!</h5>
	<hr style="width: 100%;"/>
	<input type="text" id="ctitle" placeholder="제목" />
	<button type="button" onclick="submitDownload()">만들기</button>
	<button type="button" onclick="onDownloadCancle()">취소</button>
</div>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">읽기</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=write'">쓰기</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=update'">수정</div>
		<div class="sideItem active">삭제 중!</div>
	</div>
	<form name="saveFrame" method="post" action="frameMake.do">
		<input type="text" name="title" value="" id="title"/>
		<div class="searchBar">
			<div>
				<input id="keyword" type="text" placeholder="당신이 원하는 검색어면 따라가겠어." />
				<span class="textBottomEffect"></span>
			</div>
			<button type="button" onclick="onKeyword('${frame.fseq}','delete')">검색</button>
		</div>
		<table>
			<c:forEach items="${fhiList }" var="fhi">
			<tbody>
					<tr>
						<td class="fieldHeader">
							${fhi.field }
						</td>
						<c:forEach items="${fhi.dataList }" var="dvo">
							<td>
								${dvo.data }
								<div class="dataTools" onclick="pushDelData(this,${dvo.dseq})">
									지나간 데이터는 돌아오지 않습니다.
								</div>
							</td>
						</c:forEach>
					</tr>
			</tbody>
			</c:forEach>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onDelSubmit()">삭제</div>
		<hr/>
		<div class="sideItem" onclick="onDownload(${frame.fseq})">CSV 다운로드</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
	<form name="delForm" method="post" action="deleteDataByFrame.do" style="display:none">
		<input type="hidden" name="fseq" value="${frame.fseq }" />
		<input id="delDseqInput" type="hidden" name="dseqList" value=""/>
	</form>
</section>
</body>
</html>