<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="resources/css/loading.css">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="resources/css/fileDownload.css?1" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function download(){
		$('.downTag').get(0).click();
	}
</script>
</head>
<body>
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

<!-- csvAddDesign -->
<form method="post" enctype="multipart/form-data">
<div class="container" style="margin-top: 15px;">
<div class="row">
<script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<div class="file-upload">
	  <div class="image-upload-wrap" onclick="download()">
	    <div class="drag-text">
	      <h3>${ctitle }</h3>
	      <a class="downTag" href="download/${ctitle }" download></a>
	      <input type="hidden" name="bseq" value="${bseq }"/>
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
	  	<button type="button" class="btn btn-secondary" onclick="javascript: form.action='getBoard.do'; form.submit()">Cancel</button>
	  	</div>
  	</div>
</div>
</div>
</form>

</body>
</html>