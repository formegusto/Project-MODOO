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
<c:if test="${ctitle ne null && ctitle ne '' }">
<script>
	let ctitle = '${ctitle}';
	window.open("download/csv/" + ctitle, 'MODOO CSV DOWNLOAD');
</script>
</c:if>
<jsp:include page="components/header.html"/>
<div class="csvloading">
	<h5 style="margin:0;">������ �����ּ���!</h5>
	<hr style="width: 100%;"/>
	<input type="text" id="ctitle" placeholder="����" />
	<button type="button" onclick="submitDownload()">�����</button>
	<button type="button" onclick="onDownloadCancle()">���</button>
</div>
<form name="appendForm" method="post" action="appendInfoByFrame.do" style="display:none">
	<input type="hidden" name="fseq" value="${frame.fseq }"/>
	<div class="inputDataList">
	</div>
</form>
<section> 
	<div class="sideContent">
		<div class="sideItem active">���� ��!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">�б�</div>
		<div class="sideItem active">���� ��!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=update'">����</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=delete'">����</div>
	</div>
	<form name="saveFrame" method="post" action="frameMake.do">
		<input type="text" name="title" value="" id="title"/>
		<div class="searchBar">
			<div>
				<input id="keyword" type="text" placeholder="����� ���ϴ� �˻���� ���󰡰ھ�." />
				<span class="textBottomEffect"></span>
			</div>
			<button type="button" onclick="onKeyword('${frame.fseq}','write')">�˻�</button>
		</div>
		<table>
			<c:forEach items="${fhiList }" var="fhi">
			<tbody>
					<tr class="dataTr ${fhi.iseq }">
						<td class="fieldHeader">
							${fhi.field }
						</td>
						<c:forEach items="${fhi.dataList }" var="dvo">
							<td>
								${dvo.data }
							</td>
						</c:forEach>
					</tr>
					<tr>
						<td class="toolTd">
							<button type="button" class="appendBtn" onclick="appendClick(${fhi.iseq })">������ �߰��ϱ�</button>
						</td>
					</tr>
			</tbody>
			</c:forEach>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onAppend()">����</div>
		<hr/>
		<div class="sideItem" onclick="onDownload(${frame.fseq})">CSV �ٿ�ε�</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>