<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataService.css?z"></link>
<script type="text/javascript" src="styles/js/dataService.js?ferf"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active">���� ��!</div>
		<div class="sideItem active">�д� ��!</div>
		<div class="sideItem">����</div>
		<div class="sideItem" onclick="location.href='dataService.do?iseq=${info.iseq}&mode=delete'">����</div>
	</div>
	<form>
		
		<table id="dataTable">
			<thead>
				<tr>
					<td class="searchBar">
						<div>
							<input type="text" placeholder="���� �ܾ ������."/>
							<span class="textBottomEffect"></span>
						</div>
						<button>�˻�</button>
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
							<div class="dataTools">
								<div>C</div>
								<div>U</div>
								<div>D</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem">���⿡ ��������</div>
		<div class="sideItem">REPLACE</div>
		<div class="sideItem">DELETE</div>
		<div class="sideItem" onclick="location.href='infoService.do'">CANCLE</div>
	</div>
</section>
</body>
</html>