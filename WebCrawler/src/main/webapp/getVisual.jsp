<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Board Check List</title>
</head>
<body>
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

<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
<table class="table">
	<tbody>
		<tr>
			<td>
	    	<nav>
			<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-visual" role="tab" aria-controls="nav-test" aria-selected="false">Visual Info</a>
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-view" role="tab" aria-controls="nav-test" aria-selected="false">View</a>
			</div>
			</nav>
			</td>
		</tr>
		<tr>
		<td colspan="2">
			<div class="tab-content" id="nav-tabContent">
			<div class="tab-pane" id="nav-visual" role="tabpanel" aria-labelledby="nav-home-tab">
				<table class="table">
				  <tbody>
				    <tr>
				      <th scope="row">제목</th>
				      <td>${visual.title }</td>
				    </tr>
				    <tr>
				      <th scope="row">작성자</th>
				      <td>${visual.id }</td>
				    </tr>
				    <tr>
				      <th scope="row">내용</th>
				      <td>${visual.content }</td>
				    </tr>
				 </tbody>
				</table>
				
				<div class="container" style="margin-top: 15px;">
				<div class="row">
					<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
					  	<div class="btn-group mr-2" role="group" aria-label="First group">
					  	<button type="button" class="btn btn-secondary" onclick="location.href='getVisualList.do'">VisualList</button>
					  	<button type="button" class="btn btn-outline-secondary" onclick="javascript: form.action='boardConfirm.do'; form.submit()">Share</button>
					  	<input type="hidden" name="bnum" value="${visual.vseq }"/>
					  	<input type="hidden" name="btype" value="visual"/>
					  	</div>
				  	</div>
				</div>
				</div>
			</div>
			
			<div class="tab-pane active" id="nav-view" role="tabpanel" aria-labelledby="nav-home-tab">
				     <div style="margin:auto;">
				     <canvas id="myVisual" width="400" height="400"></canvas>
					 </div>
			</div>
			</div>
		</td>
		</tr>
	</tbody>
</table>
</div>
</div>


</form>
<script>
var ctx = document.getElementById('myVisual').getContext('2d');
new Chart(ctx, {
    type: ${vtype_split},
    data: {
        labels: ${strList},
        datasets: [{
            label: '# of Votes',
            data: ${numList},
            backgroundColor: ${bgList},
            borderColor: ${boList},
            borderWidth: 1
        }]
    },
    options: {}
});
</script>
</body>
</html>