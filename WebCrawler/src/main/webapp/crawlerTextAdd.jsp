<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Crawler Add Page</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="resources/css/loading.css">
<link href="resources/bootstrap/css/bootstrap.min.css?update" rel="stylesheet"></link>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).on("click","#crawling",function(){
		$('#loading').show();
		$('#loading-image').show();
	});
	$(function(){
		$('[data-toggle="tooltip"]').tooltip()
	});
</script>
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

<!-- crawlerAddDesign -->
<form method="post" action="crawlerConfirm.do">
<div class="container" style="margin-top: 15px;">
<div class="row">
<div style="margin:auto;">
  <div class="form-group">
	<label for="inputTitle">Title</label>
	<input type="text" name="title" class="form-control" id="inputTitle" placeholder="Input Crawler Title">
  </div>
  <div class="form-group">
    <label for="contentTextarea">Content</label>
    <textarea class="form-control" name="content" id="contentTextarea" rows="3" placeholder="Input Crawling Contents"></textarea>
  </div>
  <div class="form-group">
    <label for="inputLink">Link</label>
    <input type="text" name="link" class="form-control" id="inputLink" placeholder="Input Crawling Target URL"
    	data-toggle="tooltip" data-placement="top" data-html="true" title="수집데이터가 있는 페이지 링크를 넣어주세요.">
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputField">Field</label>
      <input type="text" name="field" class="form-control" id="inputField" placeholder="Input Data Field">
    </div>
    <div class="form-group col-md-6">
      <label for="inputCssQuery">CssQuery</label>
      <input type="text" name="cssQuery" class="form-control" id="inputCssQuery" placeholder="Input CssQuery"
      	data-toggle="tooltip" data-placement="top" data-html="true" title="<img src='images/cssQueryExam.JPG'/><br/>수집하고자 하는 데이터의 CSSQuery를 크롬 개발자 모드(Ctrl+Shift+C)를 통해 확인 후 넣어주세요.">
    </div>
  </div>
</div>
</div>
</div>

<!-- Button -->
<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	  	<button type="button" class="btn btn-secondary" onclick="javascript: form.action='getInfoList.do'; form.submit()">Cancel</button>
	  	<button type="button" id="urlCancel" class="btn btn-secondary">URL Confirm(CLEAR)</button>
	  	<button type="button" id="urlLoad" class="btn btn-secondary">URL Confirm(TEST)</button>
	  	<button type="button" id="crawling" class="btn btn-secondary" onclick="javascript: form.action='crawlerTextConfirm.do'; form.submit()">Crawler Confirm</button>
	  	</div>
  	</div>
</div>
</div>
</form>
<div id="targetUrl" style="margin-top: 15px;">
</div>
</body>
<script type="text/javascript">
$("#urlCancel").click(function(){
	$("#targetUrl").html("");
})
$("#urlLoad").click(function(){
	$("#targetUrl").load($("#inputLink").val());
})
$("#targetUrl").click(function(event){
	event.preventDefault();
	if(event.target.className!=""){
		$("#inputCssQuery").val(event.target.nodeName + "." + event.target.className);
	} else if (event.target.id!=""){
		$("#inputCssQuery").val(event.target.nodeName + "#" + event.target.id);
	} else {
		$("#inputCssQuery").val(event.target.nodeName);
	}
	$('html').scrollTop(0);
})
</script>
</html>



