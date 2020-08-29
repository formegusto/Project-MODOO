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
<section> 
	<div class="sideContent">
		<div class="sideItem active">���� ��!</div>
		<div class="sideItem active">�д� ��!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=write'">����</div>
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
			<button type="button" onclick="onKeyword('${frame.fseq}','read')">�˻�</button>
		</div>
		<table>
			<c:forEach items="${fhiList }" var="fhi">
			<tbody>
					<tr>
						<td class="fieldHeader">
							${fhi.field }
						</td>
						<c:forEach items="${fhi.dataList }" var="dvo">
							<td class="readData">
								${dvo.data }
							</td>
						</c:forEach>
					</tr>
				
			</tbody>
			</c:forEach>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem">${frame.title }</div>
		<hr/>
		<div class="sideItem" onclick="onDownload(${frame.fseq})">CSV �ٿ�ε�</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>