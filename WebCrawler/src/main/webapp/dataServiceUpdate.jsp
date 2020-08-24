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
				<td colspan="3">�ٲ�� �����͸� Ȯ�����ּ���!</td>
			</tr>
			<tr class="theadContent">
				<td colspan="3">
					<div onClick="onUpSubmit()">�Ϻ��ϴ�!</div>
					<div onclick="submitCancle()">�ٽú��Կ�.</div>
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
		<div class="sideItem active">���� ��!</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=read'">�б�</div>
		<div class="sideItem active">���� ��!</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=delete'">����</div>
	</div>
	<form>
		<table id="dataTable">
			<thead>
				<tr>
					<td class="searchBar">
						<div>
							<input id="keyword" type="text" placeholder="����� ���ϴ� �˻���� ���󰡰ھ�." />
							<span class="textBottomEffect"></span>
						</div>
						<button type="button" onclick="onKeyword('${info.iseq}','update')">�˻�</button>
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
							<div class="updateConfirm ${datavo.dseq }">������ ������</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onCheck()">����</div>
		<hr/>
		<div class="sideItem">��ǲ</div>
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