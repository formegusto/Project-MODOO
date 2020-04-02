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
<title>Board List</title>
</head>
<body>
<!-- Navbar : Login, �˶� ���� -->
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

<!-- Navs : ���� �޴� -->
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link" href="getInfoList.do">Info</a>
  <a class="nav-item nav-link active" href="getBoardList.do">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
</nav>

<!-- getBoardList Design -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">��ȣ</th>
	      <th scope="col">����</th>
	      <th scope="col">�ۼ���</th>
	      <th scope="col">�����</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${boardList }" var="board">
		<tr>
			<th scope="row">${board.bseq }</th>
			<td>
			<a href="getBoard.do?bseq=${board.bseq }">${board.title }</a>
			</td>
			<td>${board.id }</td>
			<td>${board.regDate }</td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups"
		style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary">1</button>
	    <button type="button" class="btn btn-secondary">2</button>
	    <button type="button" class="btn btn-secondary">3</button>
	    <button type="button" class="btn btn-secondary">4</button>
	  	</div>
	  	<div class="btn-group mr-2" role="group" aria-label="Second group">
	    <button type="button" class="btn btn-secondary">5</button>
	    <button type="button" class="btn btn-secondary">6</button>
	    <button type="button" class="btn btn-secondary">7</button>
	  	</div>
	  	<div class="btn-group" role="group" aria-label="Third group">
	    <button type="button" class="btn btn-secondary">8</button>
  	</div>
</div>
</div>
</div>
</form>
</body>
</html>