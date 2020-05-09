<%@page import="java.util.ArrayList"%>
<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	ArrayList<InfoVO> infoList = (ArrayList) request.getAttribute("infoList");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.bundle.min.js"></script>
<title>tmObjectConfirm</title>
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
  <a class="nav-item nav-link" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
  <a class="nav-item nav-link active" href="tmObjectConfirm.do">TM</a>
  <a class="nav-item nav-link" href="getVisualList.do">Visual</a>
</nav>

<!-- infoList Card -->
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-object-tab" data-toggle="tab" href="#nav-object" role="tab" aria-controls="nav-object" aria-selected="true">Object</a>
			<a class="nav-item nav-link" id="nav-making-tab" data-toggle="tab" href="#nav-making" role="tab" aria-controls="nav-making" aria-selected="false">Making</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
	<!-- 행위 객체 정보 -->
		<div class="tab-pane fade show active" id="nav-object" role="tabpanel" aria-labelledby="nav-home-tab">
		<div class="card-deck">
			<%
			for(int i=0;i<infoList.size();i++){
				InfoVO info = infoList.get(i);
			%>
				<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				  <div class="card-header"><%=info.getSeq() %> : <%=info.getRegDate() %> 
				  </div>
				  <div class="card-body">
				    <h5 class="card-title"><a href="getTmInfo.do?seq=<%=info.getSeq() %>" style="color:white; font-weight: bold;"><%=info.getTitle() %></a></h5>
				  </div>
				</div>
			<%if(((i+1)%4)==0){%>
					</div>
					<div class="card-deck">
			<%	}} %>
		</div>
		</div>
		<!-- Making정보 -->
        <div class="tab-pane fade" id="nav-making" role="tabpanel" aria-labelledby="nav-profile-tab">
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
				<c:forEach items="${tmList }" var="tm">
				<tr>
					<th scope="row">${tm.tseq }</th>
					<td>
					<a href="getTm.do?tseq=${tm.tseq }">${tm.title }</a>
					</td>
					<td>${tm.id }</td>
					<td>${tm.regdate }</td>
				</tr>
				</c:forEach>
			  </tbody>
			</table>
        </div>
	</div>
</div>
</div>
</div>
</body>
</html>