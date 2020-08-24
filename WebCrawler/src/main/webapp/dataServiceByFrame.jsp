<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataServiceByFrame.css?e"></link>
<script type="text/javascript" src="styles/js/dataServiceByFrame.js?2"></script>
<title>MODOO</title>
</head>
<body>
<c:if test="${ctitle ne null && ctitle ne '' }">
<script>
	let ctitle = '${ctitle}';
	window.open("download/csv/" + ctitle, 'MODOO CSV DOWNLOAD');
</script>
</c:if>
<jsp:include page="components/header.html"/>
<div class="csvloading">
	<h5 style="margin:0;">제목을 적어주세요!</h5>
	<hr style="width: 100%;"/>
	<input type="text" id="ctitle" placeholder="제목" />
	<button type="button" onclick="submitDownload()">만들기</button>
</div>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem active">읽는 중!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=update'">수정</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=delete'">삭제</div>
	</div>
	<form name="saveFrame" method="post" action="frameMake.do">
		<input type="text" name="title" value="" id="title"/>
		<table>
			<tbody>
				<c:forEach items="${fhiList }" var="fhi">
					<tr>
						<td class="fieldHeader">
							${fhi.field }
						</td>
						<c:forEach items="${fhi.dataList }" var="dvo">
							<td>
								${dvo.data }
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem">${frame.title }</div>
		<hr/>
		<div class="sideItem" onclick="onDownload(${frame.fseq})">CSV 다운로드</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>