<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
</script>
<title>TMInfo</title>
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

<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link" href="getInfoList.do">Info</a>
  <a class="nav-item nav-link" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
  <a class="nav-item nav-link active" href="tmObjectConfirm.do">TM</a>
  <a class="nav-item nav-link" href="getInfoList.do">Visual</a>
</nav>

<!-- getInfoDesign -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">TMTools</a>
			<c:if test="${info.itype eq 'css:linklist' }">
			<a class="nav-item nav-link" id="nav-link-tab" data-toggle="tab" href="#nav-link" role="tab" aria-controls="nav-link" aria-selected="false">LinkList</a>
			</c:if>
			<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">DataList</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
		<!-- Info정보 -->
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
		     <table class="table">
			 <thead class="thead-dark">
			    <tr>
			      <th scope="col" colspan="2">TMTools.</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			    	<td>
			    	<button type="button" class="btn btn-outline-dark" onclick="javascript: form.action='wordCount.do'; form.submit()">WordCount</button>
			    	</td>
			    	<td>
			    	글자 빈도수 프레임을 생성 해줍니다.
			    	</td>
			    </tr>
			  </tbody>
			</table>
		</div>
		
		<!-- URL 정보 (LinkList 전용) -->
		<c:if test="${info.itype eq 'css:linklist' }">
			<div class="tab-pane fade" id="nav-link" role="tabpanel" aria-labelledby="nav-link-tab">
			     <table class="table">
				 <thead class="thead-dark">
				    <tr>
				      <th scope="col" colspan="2">LinkList</th>
				    </tr>
				  </thead>
				    <tbody id="textFrame">
		  			<c:forEach items="${linkList }" var="link">
					<tr>
						<td>
						${link.data }
						</td>
					</tr>
					</c:forEach>
	  				</tbody>
				</table>
			</div>
		</c:if>
		
		<!-- Data정보 -->
        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<table class="table">
	  			<thead class="thead-dark">
				    <tr>
				      <th scope="col" colspan="2">
				      ${info.field }
				      <input type="hidden" name="seq" value="${info.seq }">
				      </th> 
				    </tr>
	  			</thead>
	  			<tbody id="textFrame">
		  			<c:forEach items="${dataList }" var="dat">
					<tr>
						<td>
						${dat.data }
						</td>
					</tr>
					</c:forEach>
	  			</tbody>
			</table>
        </div>
	</div>
</div>
</div>
</div>
</form>
</body>
</html>