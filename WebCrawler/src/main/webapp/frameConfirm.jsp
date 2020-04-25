<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	ArrayList<InfoVO> infoList = (ArrayList) request.getAttribute("infoList");
	HashMap<String,List<DataVO>> dataMap = (HashMap) request.getAttribute("dataMap");    
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>frame Confirm Page</title>
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

<!-- Board Input -->
<form method="post" id="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
<div style="margin:auto;">
  <div class="form-group">
	<label for="inputTitle">Title</label>
	<input type="text" name="title" class="form-control" id="inputTitle" placeholder="Input Frame Title">
  </div>
  <div class="form-group">
    <label for="contentTextarea">Content</label>
    <textarea class="form-control" name="content" id="contentTextarea" rows="3" placeholder="Input Frame Contents"></textarea>
    <input type="hidden" name="id" value="${user.id }"/>
  </div>
</div>
</div>
</div>

<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='getInfoList.do'; form.submit();">Cancel</button>
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='frameAdd_proc.do'; form.submit();">Register</button>
	  	</div>
  	</div>
</div>
</div>


<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <tbody>
	    <tr>
	    	<td>
	    	<nav>
			<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<!-- infoList -->
			<c:forEach items="${infoList }" var="info">
			<input type="hidden" name="inumList" value="${info.seq }"/>
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-${info.seq }" role="tab" aria-controls="nav-test" aria-selected="false">${info.field }</a>
			</c:forEach>
			</div>
			</nav>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div class="tab-content" id="nav-tabContent">
			<%
				for(InfoVO info : infoList){
					List<DataVO> dataList = dataMap.get(info.getSeq()+"");
			%>
			<div class="tab-pane fade" id="nav-<%=info.getSeq() %>" role="tabpanel" aria-labelledby="nav-home-tab">
			     <table class="table">
				  <tbody>
				  <%for(DataVO data : dataList){ %>
						   <tr>
							   	<%if((data.getData()).equals("")){ %>
						   		<td>
							   		!!blank!!
							   	</td>
							   <%}else{ %>
								<td>
								 	<%=data.getData() %>
								</td>
								<%} %>
						   </tr>
				 <%} %>
			  	 </tbody>
				</table>
			</div>
		    <% } %>
		    </div>
			</td>
	    </tr>
	  </tbody>
	</table>
</div>
</div>
</form>
</body>
</html>