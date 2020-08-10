<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmService.css?e"></link>
<script type="text/javascript" src="styles/js/tmService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">����</div>
		<div class="sideItem active" onClick="location.href='tmService.do'">�ؽ�Ʈ���̴� ��!</div>
		<div class="sideItem" onClick="location.href='visualService.do'">�ð�ȭ</div>
	</div>
	<div class="contents active" id="tmList">
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="����� ���ϴ� �˻���� ���󰡰ھ�." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword('${info.iseq}','read')">�˻�</button>
		</div>
		<c:forEach items="${tmList }" var="tm">
			<div class="contentCard">
			<h1>${tm.title }</h1>
			<hr/>
				<div class="contentItem">
					<c:import url="/userRview/${tm.tseq }.html" charEncoding="EUC-KR" />
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard tm" onClick="location.href='infoServiceByTm.do'">
			<h1>WordCloud</h1>
			<hr/>
					<p>
					�ܾ��� �󵵼��� ����Ͽ� WordCloud�� ǥ�� �� �ݴϴ�.
					</p>
			</div>
			<div class="contentCard tm">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					�� �ܾ�� ���� �������� ���Ἲ�� ���� ǥ�� �� �ݴϴ�.
					</p>
			</div>
			<div class="contentCard tm">
			<h1>SentimentAnalysis</h1>
			<hr/>
					<p>
					�ܾ� �� ����, �߸�, ����, 3������ ������ �м��Ͽ� ǥ�� �� �ݴϴ�.
					</p>
			</div>
	</div>
	<div class="sideContent">
		<div class="sideItem active" onclick="onChange(this,'list')" id="firstActive">My List</div>
		<div class="sideItem" onclick="onChange(this,'makeList')">Make TM</div>
	</div>
</section>
</body>
</html>