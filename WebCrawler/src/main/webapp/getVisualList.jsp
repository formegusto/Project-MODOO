<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="resources/css/loading.css">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link href="resources/fontawesome/css/all.css" rel="stylesheet">
<script defer src="resources/fontawesome/js/all.js"></script>
<script type="text/javascript">
function visualSel(vtype){
	$("input[name=vtype]").val(vtype);
	document.form.action = "checkVisualList.do";
	document.form.submit();
}
</script>
<title>Visual MAIN Page</title>
</head>
<body>
<!-- Loading -->
<div id="loading">
	<img id="loading-image" src="images/viewLoading.gif" alt="Loading..."/>
</div>

<!-- Navbar : Login, 알람 정보 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">MODOO</a>
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

<!-- Navs : 보드 메뉴 -->
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link" href="getInfoList.do">Info</a>
  <a class="nav-item nav-link" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
  <a class="nav-item nav-link" href="tmObjectConfirm.do">TM</a>
  <a class="nav-item nav-link active" href="getVisualList.jsp">Visual</a>
</nav>

<!-- getInfoDesign -->
<form method="post" name="form">
<input type="hidden" name="vtype" value="" />
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-model-tab" data-toggle="tab" href="#nav-model" role="tab" aria-controls="nav-model" aria-selected="true">Model</a>
			<a class="nav-item nav-link" id="nav-making-tab" data-toggle="tab" href="#nav-making" role="tab" aria-controls="nav-making" aria-selected="false">Making</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
		<!-- Model정보 -->
		<div class="tab-pane fade show active" id="nav-model" role="tabpanel" aria-labelledby="nav-home-tab">
		     <div class="card-group">
			  <div class="card">
			    <img class="card-img-top" src="images/visualModelImg/barModel.JPG" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title">Bar</h5>
			    </div>
			    <div class="card-footer text-right">
			      <small class="text-muted">
					<a href="#" class="btn btn-primary" onclick="visualSel('chart:bar')">Select</a>
				  </small>
			    </div>
			  </div>
			  <div class="card">
			    <img class="card-img-top" src="images/visualModelImg/lineModel.JPG" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title">Line</h5>
			    </div>
			    <div class="card-footer text-right">
			      <small class="text-muted">
					<a href="#" class="btn btn-primary" onclick="visualSel('chart:line')">Select</a>
				  </small>
			    </div>
			  </div>
			 </div>
			 <div class="card-group">
			  <div class="card">
			    <img class="card-img-top" src="images/visualModelImg/doughnutModel.JPG" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title">Doughnut</h5>
			    </div>
			    <div class="card-footer text-right">
			      <small class="text-muted">
					<a href="#" class="btn btn-primary" onclick="visualSel('chart:doughnut')">Select</a>
				  </small>
			    </div>
			  </div>
			  <div class="card">
			    <img class="card-img-top" src="images/visualModelImg/polarAreaModel.JPG" alt="Card image cap">
			    <div class="card-body">
			      <h5 class="card-title">PolarArea</h5>
			    </div>
			    <div class="card-footer text-right">
			      <small class="text-muted">
					<a href="#" class="btn btn-primary" onclick="visualSel('chart:polarArea')">Select</a>
				  </small>
			    </div>
			  </div>
			</div>
		</div>
		
		<!-- Making정보 -->
        <div class="tab-pane fade" id="nav-making" role="tabpanel" aria-labelledby="nav-profile-tab">
			
        </div>
	</div>
</div>
</div>
</div>
</form>
</body>
</html>