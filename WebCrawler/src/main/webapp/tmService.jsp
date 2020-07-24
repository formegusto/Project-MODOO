<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmService.css?33"></link>
<script type="text/javascript" src="styles/js/tmService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">����</div>
		<div class="sideItem active" onClick="location.href='tmService.jsp'">�ؽ�Ʈ���̴� ��!</div>
		<div class="sideItem">�ð�ȭ</div>
	</div>
	<div class="contents active" id="tmList">
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					�̰� ��� �ȽἭ ������ �׷��°ǰ�? �ʹ��ϴ� �ФФФФ�;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					�̰� ��� �ȽἭ ������ �׷��°ǰ�? �ʹ��ϴ� �ФФФФ�;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					�̰� ��� �ȽἭ ������ �׷��°ǰ�? �ʹ��ϴ� �ФФФФ�;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					�̰� ��� �ȽἭ ������ �׷��°ǰ�? �ʹ��ϴ� �ФФФФ�;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					�̰� ��� �ȽἭ ������ �׷��°ǰ�? �ʹ��ϴ� �ФФФФ�;
					</p>
			</div>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard" onClick="location.href='infoServiceByTm.do'">
			<h1>WordCloud</h1>
			<hr/>
					<p>
					�ܾ��� �󵵼��� ����Ͽ� WordCloud�� ǥ�� �� �ݴϴ�.
					</p>
			</div>
			<div class="contentCard">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					�� �ܾ�� ���� �������� ���Ἲ�� ���� ǥ�� �� �ݴϴ�.
					</p>
			</div>
			<div class="contentCard">
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