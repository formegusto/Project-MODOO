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
<link href="resources/css/fileUpload.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).on("click","#csving",function(){
		$('#loading').show();
		$('#loading-image').show();
	});
	function readURL(input) {
		  if (input.files && input.files[0]) {

		    var reader = new FileReader();

		    reader.onload = function(e) {
		      $('.drag-text').hide();
		      $('.drag-text_2').show();
		      $('.image-title').html(input.files[0].name);
		    };

		    reader.readAsDataURL(input.files[0]);

		  } else {
		    removeUpload();
		  }
	}
	
	function removeUpload() {
		  $('.file-upload-input').replaceWith($('.file-upload-input').clone());
		  $('.image-upload-wrap').show();
	}
	
	$('.image-upload-wrap').bind('dragover', function () {
		$('.image-upload-wrap').addClass('image-dropping');
	});
	$('.image-upload-wrap').bind('dragleave', function () {
		$('.image-upload-wrap').removeClass('image-dropping');
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

<!-- csvAddDesign -->
<form method="post" enctype="multipart/form-data">
<div class="container" style="margin-top: 15px;">
<div class="row">
<script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<div class="file-upload">
	  <div class="image-upload-wrap">
	    <input class="file-upload-input" name="csv" type='file' onchange="readURL(this);"/>
	    <div class="drag-text">
	      <h3>Drag and drop or Click here.</h3>
	    </div>
	    <div class="drag-text_2">
	      <h3><span class="image-title"></span></h3>
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
	  	<button type="button" id="csving" class="btn btn-secondary" onclick="javascript: form.action='csvConfirm.do'; form.submit()">CSV Confirm</button>
	  	</div>
  	</div>
</div>
</div>
</form>

</body>
</html>