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
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script>
function listCheck(seq){
	var seqList_ = $("input[name=seqList]").val();
	$("input[name=seq]:checked").each(function() {
		if($(this).val() == seq){
			if($("input[name=seqList]").val() == ""){
				$("input[name=seqList]").val(seq);
			} else {
				$("input[name=seqList]").val($("input[name=seqList]").val()+","+seq);
			}
		} // 이번에 체크 된거
	});
	if(seqList_ == $("input[name=seqList]").val()){
		$("input[name=seqList]").val($("input[name=seqList]").val().replace(seq,""));
		if($("input[name=seqList]").val().indexOf(",,")!=-1){ // 중간값
			$("input[name=seqList]").val($("input[name=seqList]").val().replace(",,",","));
		} else if($("input[name=seqList]").val().indexOf(",")==0){ // 첫번째값
			$("input[name=seqList]").val(
					$("input[name=seqList]").val().substring(1,($("input[name=seqList]").val().length))
					);
		} else if($("input[name=seqList]").val().lastIndexOf(",") == ($("input[name=seqList]").val().length - 1)){ // 마지막값
			$("input[name=seqList]").val(
					$("input[name=seqList]").val().substring(0,($("input[name=seqList]").val().length - 1))
					);
		}

	} // uncheck 한 거임 지워줘야함.
	var seqList = $("input[name=seqList]").val();
}
</script>
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

<!-- Navs : 보드 메뉴 -->
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link active" href="getInfoList.do">Info</a>
  <a class="nav-item nav-link" href="getBoardList.do?pageNum=1">Board</a>
  <a class="nav-item nav-link" href="getRoomList.do">ChatRoom</a>
  <a class="nav-item nav-link" href="tmObjectConfirm.do">TM</a>
  <a class="nav-item nav-link" href="getVisualList.do">Visual</a>
</nav>

<!-- infoList Card -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="card-deck">
	<input type="hidden" name="seqList" value=""/>
	<%
	for(int i=0;i<infoList.size();i++){
		InfoVO info = infoList.get(i);
	%>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header"><%=info.getSeq() %> : <%=info.getRegDate() %> 
		      <div class="custom-control custom-checkbox my-1 mr-sm-2" style="display: inline; float: right;">
		      	
    			<input type="checkbox" class="custom-control-input" name="seq" class="seqList" id="<%=info.getSeq()%>" value="<%=info.getSeq()%>" onchange="listCheck(<%=info.getSeq() %>)">
    			<label class="custom-control-label" for="<%=info.getSeq()%>"></label>
  			  </div>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title"><a href="getInfo.do?seq=<%=info.getSeq()%>" style="color:white; font-weight: bold;"><%=info.getTitle() %></a></h5>
		    <p class="card-text"><%=info.getContent() %></p>
		  </div>
		</div>
	<%if(((i+1)%4)==0){%>
			</div>
			<div class="card-deck">
	<%	}} %>
	</div>
</div>
</div>

<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary" onclick="location.href='getInfoList.do'">InfoList</button>
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='boardConfirm.do'; form.submit()">BoardConfirm</button>
	  	</div>
  	</div>
</div>
</div>
</form>
</body>
</html>