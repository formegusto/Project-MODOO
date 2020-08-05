<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataServiceByFrame.css?z"></link>
<script type="text/javascript" src="styles/js/updateDataServiceByFrame.js?x"></script>
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
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">읽기</div>
		<div class="sideItem active">수정 중!</div>
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
							<td class="dataItem">
								<textarea class="updateData ${dvo.dseq }" 
									onfocus="changeStart(this)" 
									onblur="changeEnd(this,${dvo.dseq})">${dvo.data }</textarea>
								<div class="updateConfirm ${dvo.dseq }">수정된 데이터</div>
							</td>
						</c:forEach>
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