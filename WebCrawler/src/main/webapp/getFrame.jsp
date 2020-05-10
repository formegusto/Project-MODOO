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
					 <table class="table">
					  <tbody>
					    <tr>
					      <th scope="row">제목</th>
					      <td>${info.title }
					      <input type="hidden" name="seq" value="${info.seq }"/>
						  <input type="hidden" name="title" value="${info.title }"/>
					      </td>
					    </tr>
					    <tr>
					      <th scope="row">주소</th>
					      <td>${info.link }
					      <input type="hidden" name="link" value="${info.link }"/>
					      </td>
					    </tr>
					    <tr>
					      <th scope="row">CssQuery</th>
					      <td>${info.cssQuery }
						  <input type="hidden" name="cssQuery" value="${info.cssQuery }"/>
						  </td>
					    </tr>
					    <tr>
						  <th scope="row">내용</th>
						  <td>${info.content }
						  <input type="hidden" name="content" value="${info.content }"/>
						  <input type="hidden" name="field" value="${info.field }"/>
						  </td>
						</tr>
						<tr>
						  <th scope="row">타입</th>
						  <td>${info.itype }
						  <input type="hidden" name="itype" value="${info.itype }"/>
						  </td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
		    </c:forEach>
		    </div>
		    <div class="container" style="margin-top: 15px;">
			<div class="row">
				<div class="input-group mb-3">
					<input type="hidden" name="fseq" value="${frame.fseq }"/>
			    	<input type="text" class="form-control" name="ctitle" placeholder="CSV Title" aria-label="Recipient's username" aria-describedby="basic-addon2">
					<div class="input-group-append">
			    		<button class="btn btn-outline-secondary" type="button" onclick="javascript: form.action='convertCSV.do'; form.submit()">Convert CSV</button>
			    	</div>
			    </div>
			</div>
			</div>
		    <!-- Button -->
			<div class="container" style="margin-top: 15px;">
			<div class="row">
				
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
				  	<div class="btn-group mr-2" role="group" aria-label="First group">
				  	<button type="button" class="btn btn-secondary" onclick="location.href='getInfoList.do'">InfoList</button>
				  	<button type="button" class="btn btn-outline-secondary" onclick="javascript: form.action='boardConfirm.do'; form.submit()">Share</button>
				  	<input type="hidden" name="bnum" value="${frame.fseq }"/>
				  	<input type="hidden" name="btype" value="frame"/>
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
					List<DataVO> dataList = dataMap.get(info.getSeq()+"");
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
		    		List<DataVO> dataList = dataMap.get(info.getSeq()+"");
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