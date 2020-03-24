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
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).on("click","#crawling",function(){
		$('#loading').show();
		$('#loading-image').show();
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
  <a class="navbar-brand" href="#">WCrawl</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Welcome! ${user.name }<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="logout.do">logout</a>
      </li>
    </ul>
  </div>
</nav>

<!-- crawlerAddDesign -->
<div class="container" style="margin-top: 15px;">
<div class="row">
<div style="margin:auto;">
<form method="post" action="crawlerConfirm.do">
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
    <input type="text" name="link" class="form-control" id="inputLink" placeholder="Input Crawling Target URL">
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputField">Field</label>
      <input type="text" name="field" class="form-control" id="inputField" placeholder="Input Data Field">
    </div>
    <div class="form-group col-md-6">
      <label for="inputCssQuery">CssQuery</label>
      <input type="text" name="cssQuery" class="form-control" id="inputCssQuery" placeholder="Input CssQuery">
    </div>
  </div>
  <button type="submit" id="crawling" class="btn btn-primary" style="float: right;">Crawler Confirm</button>
</form>
</div>
</div>
</div>
</body>
</html>