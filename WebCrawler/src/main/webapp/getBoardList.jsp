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
  <a class="nav-item nav-link active" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
  <a class="nav-item nav-link" href="tmObjectConfirm.do">TM</a>
  <a class="nav-item nav-link" href="getInfoList.do">Visual</a>
</nav>

<!-- getBoardList Design -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">번호</th>
	      <th scope="col">제목</th>
	      <th scope="col">작성자</th>
	      <th scope="col">등록일</th>
	    </tr>
	  </thead>
	  <tbody>
		<c:forEach items="${boardList }" var="board">
		<tr>
			<th scope="row">${board.bseq }</th>
			<td>
			<a href="getBoard.do?bseq=${board.bseq }&startPage=${startPage}&pageNum=${pageNum }">${board.title }</a>
			</td>
			<td>${board.id }</td>
			<td>${board.regDate }</td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups"
		style="margin:auto;">
		<c:if test="${startPage ne 0 }">
		<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary" onclick="location.href='getBoardList.do?pageNum=${startPage-4}&startPage=${startPage - 5}'">Previous</button>
	    </div>
	    </c:if>
	  	<div class="btn-group mr-2" role="group" aria-label="second group">
	  	<c:forEach var="i" begin="${startPage + 1}" end="${endPage }" step="1">
	    <button type="button" class="btn btn-secondary" onclick="location.href='getBoardList.do?pageNum=${i}&startPage=${startPage }'">${i }</button>
	    </c:forEach>
	  	</div>
	  	<c:if test="${endPage ne finalPage }">
	  	<div class="btn-group mr-2" role="group" aria-label="Third group">
	    <button type="button" class="btn btn-secondary" onclick="location.href='getBoardList.do?pageNum=${endPage+1}&startPage=${endPage}'">Next</button>
	    </div>
		</c:if>
	</div>
</div>
</div>
</form>
</body>
</html>