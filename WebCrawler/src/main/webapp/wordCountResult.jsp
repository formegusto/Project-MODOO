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
<title>Board Confirm Page</title>
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

<form method="post">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <tbody>
	  	<tr>
	    	<td>
	    	<nav>
			<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<c:forEach items="${infoList }" var="info">
			<input type="hidden" name="ogField" value="${info.field }"/>
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-${info.field }" role="tab" aria-controls="nav-test" aria-selected="false">${info.field }</a>
			</c:forEach>
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-view" role="tab" aria-controls="nav-test" aria-selected="false">WordCloud</a>
			</div>
			</nav>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div class="tab-content" id="nav-tabContent">
			<c:forEach items="${infoList }" var="info">
			<div class="tab-pane fade" id="nav-${info.field }" role="tabpanel" aria-labelledby="nav-home-tab">
			     <div style="margin:auto;">
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
				    <input type="text" name="link" value="tm:wordcount" class="form-control" id="inputLink" readOnly>
				  </div>
				  <div class="form-row">
				    <div class="form-group col-md-6">
				      <label for="inputField">Field</label>
				      <input type="text" name="field" value="${info.field }" class="form-control" id="inputField" placeholder="Input Data Field">
				    </div>
				    <div class="form-group col-md-6">
				      <label for="inputCssQuery">CssQuery</label>
				      <input type="text" name="cssQuery" value="tm:wordcount" class="form-control" id="inputCssQuery" readOnly>
				    </div>
				  </div>
				</div>
			</div>
		    </c:forEach>
		    <div class="tab-pane active" id="nav-view" role="tabpanel" aria-labelledby="nav-home-tab">
			     <div style="margin:auto;">
			     <jsp:include page="/rview/test.html"/> <!-- 에러는 뜨지만 잘 작동한다 하하 -->
				 </div>
			</div>
		    </div>
		    
		    <!-- Button -->
			<div class="container" style="margin-top: 15px;">
			<div class="row">
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
				  	<div class="btn-group mr-2" role="group" aria-label="First group">
				  	<button type="button" class="btn btn-secondary" onclick="location.href='getTmInfo.do?seq=${rtnseq }'">Cancel</button>
				  	<button type="button" id="crawling" class="btn btn-secondary" onclick="javascript: form.action='multiAdd_proc.do'; form.submit()">Result Add</button>
				  	</div>
			  	</div>
			</div>
			</div>
			</td>
		</tr>
	    <tr>
	    	<td>
	    	<nav>
			<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<!-- infoList -->
			<c:forEach items="${infoList }" var="info">
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-${info.field }0" role="tab" aria-controls="nav-test" aria-selected="false">${info.field }</a>
			</c:forEach>
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-table" role="tab" aria-controls="nav-test" aria-selected="false">All</a>
			</div>
			</nav>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div class="tab-content" id="nav-tabContent">
			<%
				for(InfoVO info : infoList){
					List<DataVO> dataList = dataMap.get(info.getField());
			%>
			<div class="tab-pane fade" id="nav-<%=info.getField() %>0" role="tabpanel" aria-labelledby="nav-home-tab">
			     <table class="table">
				  <tbody>
				  <%for(DataVO data : dataList){ %>
						   <tr>
							   	<td>
							   		<input type="hidden" name="<%=info.getField() %>" value="<%=data.getData() %>"/>
							   		<%=data.getData() %>
							   	</td>
						   </tr>
				 <%} %>
			  	 </tbody>
				</table>
			</div>
		    <% } %>
		    <div class="tab-pane fade" id="nav-table" role="tabpanel" aria-labelledby="nav-home-tab">
		    	<table class="table">
		    	<thead>
		    	<tr>
		    	<%
		    	for(InfoVO info : infoList){
		    	%>
		    				<th scope="col"><%=info.getField() %></th>
		    	<% } %>
		    	</tr>
		    	</thead>
		    	<tbody>
		    	<tr>
		    	<% for(InfoVO info : infoList){ 
		    		List<DataVO> dataList = dataMap.get(info.getField());
		    	%>
		    		<td>
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
		    		</td>
		    	<%} %>
		    	</tr>
		    	</tbody>
		    	</table>
		    </div>
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