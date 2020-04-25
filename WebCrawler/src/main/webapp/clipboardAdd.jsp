<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="resources/css/loading.css">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function pasteClip(){
		$('#loading').show();
		$('#loading-image').show();
		document.form.action = "clipboardConfirm.do";
		document.form.submit();
	}
</script>
<title>Board Confirm Page</title>
</head>
<body>
<!-- Loading -->
<div id="loading">
	<img id="loading-image" src="images/viewLoading.gif" alt="Loading..."/>
</div>
<!-- Navbar : Login, 알람 정보 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">MODOO</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Welcome! ${user.name }<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="logout.do">logout</a>
      </li>
    </ul>
    <div id="roomOpen"></div>
  </div>
</nav>

<!-- Board Input -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
<div style="margin:auto;">
  <div class="form-group">
    <label for="contentTextarea">Table 데이터를 이곳에 붙여넣기 해주세요.</label>
    <textarea class="form-control" name="table" id="table" rows="3" placeholder="Input Board Contents" onchange="pasteClip()"></textarea>
  </div>
</div>
</div>
</div>
</form>
</body>
</html>