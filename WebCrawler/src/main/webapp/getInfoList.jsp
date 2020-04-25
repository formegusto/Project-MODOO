<%@page import="com.crawler.biz.info.FrameVO"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	ArrayList<InfoVO> infoList = (ArrayList) request.getAttribute("infoList");
	ArrayList<FrameVO> frameList = (ArrayList) request.getAttribute("frameList");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.bundle.min.js"></script>
<title>Info List</title>
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
  <a class="nav-item nav-link active" href="getInfoList.do">Info</a>
  <a class="nav-item nav-link" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
  <a class="nav-item nav-link" href="tmObjectConfirm.do">TM</a>
  <a class="nav-item nav-link" href="getVisualList.do">Visual</a>
</nav>

<!-- infoList Card -->
<!-- getInfoDesign -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-info-tab" data-toggle="tab" href="#nav-info" role="tab" aria-controls="nav-info" aria-selected="true">Set</a>
			<a class="nav-item nav-link" id="nav-frame-tab" data-toggle="tab" href="#nav-frame" role="tab" aria-controls="nav-frame" aria-selected="false">Frame</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
		<!-- Model정보 -->
		<div class="tab-pane fade show active" id="nav-info" role="tabpanel" aria-labelledby="nav-info-tab">
		    <div class="card-deck">
			<%
			for(int i=0;i<infoList.size();i++){
				InfoVO info = infoList.get(i);
			%>
				<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				  <div class="card-header"><%=info.getSeq() %> : <%=info.getRegDate() %> 
				  <button type="button" class="btn btn-outline-light" style="float: right" onclick="location.href='deleteInfo.do?seq=<%=info.getSeq()%>'">X</button>
				  </div>
				  <div class="card-body">
				    <h5 class="card-title"><a href="getInfo.do?seq=<%=info.getSeq() %>" style="color:white; font-weight: bold;"><%=info.getTitle() %></a></h5>
				    <p class="card-text"><%=info.getContent() %></p>
				  </div>
				</div>
			<%if(((i+1)%4)==0){%>
					</div>
					<div class="card-deck">
			<%	}} %>
			</div>
		</div>
		
		<!-- Making정보 -->
        <div class="tab-pane fade" id="nav-frame" role="tabpanel" aria-labelledby="nav-frame-tab">
			<div class="card-deck">
			<%
			for(int i=0;i<frameList.size();i++){
				FrameVO frame = frameList.get(i);
			%>
				<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
				  <div class="card-header"><%=frame.getFseq() %> : <%=frame.getRegdate() %> 
				  <button type="button" class="btn btn-outline-light" style="float: right" onclick="location.href='deleteFrame.do?fseq=<%=frame.getFseq()%>'">X</button>
				  </div>
				  <div class="card-body">
				    <h5 class="card-title"><a href="getFrame.do?fseq=<%=frame.getFseq() %>" style="color:white; font-weight: bold;"><%=frame.getTitle() %></a></h5>
				    <p class="card-text"><%=frame.getContent() %></p>
				  </div>
				</div>
			<%if(((i+1)%4)==0){%>
					</div>
					<div class="card-deck">
			<%	}} %>
			</div>
        </div>
	</div>
</div>
</div>
</div>

<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	  	<button type="button" class="btn btn-secondary" onclick="javascript: form.action='clipboardAdd.jsp'; form.submit()">New ClipBoard</button>
	  	<button type="button" class="btn btn-secondary" onclick="javascript: form.action='csvAdd.jsp'; form.submit()">New CSV</button>
	    <div class="dropdown">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    Crawler
		  </button>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		    <a class="dropdown-item" href="crawlerTextAdd.jsp">Text</a>
		    <a class="dropdown-item" href="crawlerLinkAdd.jsp">Link</a>
		    <a class="dropdown-item" href="crawlerLList.do">LinkList</a>
		  </div>
		</div>
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='checkInfoList.do'; form.submit()">New Frame</button>
	  	</div>
  	</div>
</div>
</div>
</form>
</body>
</html>