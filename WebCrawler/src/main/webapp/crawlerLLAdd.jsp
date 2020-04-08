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
	$(document).on("click","#crawling",function(){
		$('#loading').show();
		$('#loading-image').show();
	});
</script>
<title>Crawler SELECT Page</title>
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

<!-- getInfoDesign -->
<form method="post" name="form" id="form">
<input type="hidden" value="" name="dseqChangeList"/>
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">InfoList</a>
			<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">DataList</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
		<!-- Info정보 -->
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
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
			    <input type="text" name="link" class="form-control" id="inputLink" value="${info.link }" readonly>
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
		</div>
		<!-- Data정보 -->
        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<table class="table">
	  			<thead class="thead-dark">
				    <tr>
				      <th scope="col" colspan="2">URL List</th>
				    </tr>
	  			</thead>
	  			<tbody>
		  			<c:forEach items="${urlList }" var="url">
					<tr>
						<td>
						${url.data }
						<input type="hidden" name="urlList" value="${url.data }">
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
<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='getInfoList.do'; form.submit()">Cancle</button>
	    <button type="button" id="crawling" class="btn btn-secondary" onclick="javascript: form.action='crawlerLLConfirm.do'; form.submit()">Register</button>
	  	</div>
  	</div>
</div>
</div>
</form>
</body>
</html>