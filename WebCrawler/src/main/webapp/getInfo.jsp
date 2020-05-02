<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	function checkChange(dseq){
		$("#changeBox_" + dseq).html("<button type='button' class='btn btn-outline-dark' style='float: right' onclick='uncheckChange("
			+ dseq + ")'>"
			+ "<i class='fas fa-check'></i></button>");
		if($("input[name=dseqChangeList]").val() == ""){
			$("input[name=dseqChangeList]").val(dseq);
		} else { // 이미 값이 있다는 거
			$("input[name=dseqChangeList]").val($("input[name=dseqChangeList]").val()+","+dseq);
			var go = confirm("DataChange : " + $("input[name=dseqChangeList]").val().split(",")[0]+" and "
					+$("input[name=dseqChangeList]").val().split(",")[1] + " ?");
			if(go == true){
				location.href="changeData.do?seq="
					+ ${info.seq } + "&dseqChangeList=" 
					+ $("input[name=dseqChangeList]").val();
			}
			else {
				$("#changeBox_" + $("input[name=dseqChangeList]").val().split(",")[0]).html("<button type='button' class='btn btn-outline-dark' style='float: right' onclick='checkChange("
						+ $("input[name=dseqChangeList]").val().split(",")[0] + ")'>"
						+ "<i class='fas fa-exchange-alt'></i></button>");
				$("#changeBox_" + $("input[name=dseqChangeList]").val().split(",")[1]).html("<button type='button' class='btn btn-outline-dark' style='float: right' onclick='checkChange("
						+ $("input[name=dseqChangeList]").val().split(",")[1] + ")'>"
						+ "<i class='fas fa-exchange-alt'></i></button>");
				$("input[name=dseqChangeList]").val("");
			}
			
		}
	}
	function uncheckChange(dseq){
		$("#changeBox_" + dseq).html("<button type='button' class='btn btn-outline-dark' style='float: right' onclick='checkChange("
				+ dseq + ")'>"
				+ "<i class='fas fa-exchange-alt'></i></button>");
		$("input[name=dseqChangeList]").val($("input[name=dseqChangeList]").val().replace(dseq,""));
		$("input[name=dseqChangeList]").val($("input[name=dseqChangeList]").val().replace(",",""));
	}
	
	function onUpdateMode(){
		$("#inputFrame").show();
		$("#textFrame").hide();
		$("#conditionBtnBox").hide();
		$("#updateBtnBox").show();
	}
	function offUpdateMode(){
		if($("input[name=updateDseqList]").val() == ""){
			$("#inputFrame").hide();
			$("#textFrame").show();
			$("#updateBtnBox").hide();
			$("#conditionBtnBox").show();
		} else {
			alert("수정한 데이터 리스트 ==> " + $("input[name=updateDseqList]").val())
			document.form.action = "updateDataContent.do";
			document.form.submit(); // 이게 id 값으로 불러오면 안되고 자바스크립트 네임으로 이용해야 함.
		}
	}
	function updateInput(dseq){
		if($("input[name=updateDseqList]").val() == ""){
			$("input[name=updateDseqList]").val(dseq);
		} else {
			if($("input[name=updateDseqList]").val().indexOf(dseq)==-1){
				$("input[name=updateDseqList]").val($("input[name=updateDseqList]").val()+","+dseq);
			}
		}
	}
	function onReplaceMode(){
		if($("#replaceFrame").css("display") == "none"){
		    $("#replaceFrame").show();
		} else {
		    $("#replaceFrame").hide();
		}
	}
	function onCutMode(){
		if($("#cutFrame").css("display") == "none"){
		    $("#cutFrame").show();
		} else {
		    $("#cutFrame").hide();
		}
	}
	function onConditionDelMode(){
		if($("#conditionDelFrame").css("display") == "none"){
		    $("#conditionDelFrame").show();
		} else {
		    $("#conditionDelFrame").hide();
		}
	}
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
<form method="post" name="form">
<input type="hidden" value="" name="dseqChangeList"/>
<input type="hidden" value="" name="updateDseqList"/>
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">InfoList</a>
			<c:if test="${info.itype eq 'css:linklist' }">
			<a class="nav-item nav-link" id="nav-link-tab" data-toggle="tab" href="#nav-link" role="tab" aria-controls="nav-link" aria-selected="false">LinkList</a>
			</c:if>
			<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">DataList</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
		<!-- Info정보 -->
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
		     <table class="table">
			 <thead class="thead-dark">
			    <tr>
			      <th scope="col" colspan="2">InfoList Information.</th>
			    </tr>
			  </thead>
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
		
		<!-- URL 정보 (LinkList 전용) -->
		<c:if test="${info.itype eq 'css:linklist' }">
			<div class="tab-pane fade" id="nav-link" role="tabpanel" aria-labelledby="nav-link-tab">
			     <table class="table">
				 <thead class="thead-dark">
				    <tr>
				      <th scope="col" colspan="2">LinkList</th>
				    </tr>
				  </thead>
				    <tbody id="textFrame">
		  			<c:forEach items="${linkList }" var="link">
					<tr>
						<td>
						${link.data }
						</td>
					</tr>
					</c:forEach>
	  				</tbody>
				</table>
			</div>
		</c:if>
		
		<!-- Data정보 -->
        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<table class="table">
	  			<thead class="thead-dark">
				    <tr>
				      <th scope="col" colspan="2">
				      <div id="conditionBtnBox">
				      ${info.field }
				      <button type="button" class="btn btn-outline-light" style="float: right" onclick="onConditionDelMode()"><i class="far fa-trash-alt"></i></button>
				      <button type="button" class="btn btn-outline-light" style="float: right" onclick="onCutMode()"><i class="fas fa-cut"></i></button>
				      <button type="button" class="btn btn-outline-light" style="float: right" onclick="onReplaceMode()"><i class="far fa-comments"></i></button>
				      <button type="button" class="btn btn-outline-light" style="float: right" onclick="onUpdateMode()"><i class="fas fa-pen"></i></button>
				      </div>
				      <div id="updateBtnBox" style="display:none">
				      ${info.field }
				      <button type='button' class='btn btn-outline-light' style='float: right' onclick='offUpdateMode()'><i class='fas fa-check'></i></button>
				      </div>
				      </th> 
				    </tr>
				    <tr id="conditionDelFrame" style="display: none">
				    	<td>
				    	<div class="form-row align-items-center">
						    <div class="col-auto">
						      <select name="conditionKind" id="inputState" class="form-control">
						        <option selected>Kind Of Condition</option>
						        <option value="length">String Length</option>
						      </select>
						    </div>
						    <div class="col-auto">
						      <input type="text" class="form-control" name="length">
						    </div>
						    <div class="col-auto">
						      <select name="condition" id="inputState" class="form-control">
						        <option selected>Condition Sel</option>
						        <option value="lt">&lt</option>
						        <option value="le">&lt=</option>
						        <option value="gt">&gt</option>
						        <option value="ge">&gt=</option>
						        <option value="eq">==</option>
						        <option value="ne">!=</option>
						      </select>
						    </div>
						    <div class="col-auto">
						      <button class="btn btn-outline-secondary" type="button" onclick="javascript: form.action='conditionDel.do'; form.submit()">Delete</button>
						    </div>
						</div>
						</td>
				    </tr>
				    <tr id="replaceFrame" style="display:none">
				    	<td>
				    	<div class="input-group mb-3">
						  <input type="text" class="form-control" name="word" placeholder="word" aria-label="word" aria-describedby="basic-addon2">
						  <input type="text" class="form-control" name="rword" placeholder="replace word" aria-label="replace word" aria-describedby="basic-addon2">
						  <div class="input-group-append">
						    <button class="btn btn-outline-secondary" type="button" onclick="javascript: form.action='replaceData.do'; form.submit()">Replace</button>
						  </div>
						</div>
						
						</td>
				    </tr>
				    <tr id="cutFrame" style="display:none">
				    	<td>
				    	<div class="input-group mb-3">
						  <input type="text" class="form-control" name="sindex" placeholder="start index" aria-label="start index" aria-describedby="basic-addon2">
						  <input type="text" class="form-control" name="eindex" placeholder="end index" aria-label="end index" aria-describedby="basic-addon2">
						  <div class="input-group-append">
						    <button class="btn btn-outline-secondary" type="button" onclick="javascript: form.action='cutData.do'; form.submit()">DataCut</button>
						  </div>
						</div>
						</td>
				    </tr>
	  			</thead>
	  			<tbody id="inputFrame" style="display:none">
	  				<c:forEach items="${dataList }" var="dat">
	  				<tr>
	  					<td>
	  						  <div class="form-group">
							    <input type="text" class="form-control" name="data" id="inputAddress" value="${dat.data }" onkeydown="updateInput(${dat.dseq})">
							  </div>
	  					</td>
	  				</tr>
	  				</c:forEach>
	  			</tbody>
	  			<tbody id="textFrame">
		  			<c:forEach items="${dataList }" var="dat">
					<tr>
						<td>
						${dat.data }
						<div id="changeBox_${dat.dseq }">
						<button type="button" class="btn btn-outline-dark" style="float: right" onclick="checkChange(${dat.dseq })"><i class="fas fa-exchange-alt"></i></button>
						</div>
						<button type="button" class="btn btn-outline-dark" style="float: right" onclick="location.href='deleteDataSeq.do?seq=${info.seq }&dseq=${dat.dseq}'"><i class="far fa-trash-alt"></i></button>
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
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='getInfoList.do'; form.submit()">InfoList</button>
	    <c:if test="${fn:contains(info.cssQuery,'csv') ne true and fn:contains(info.cssQuery,'tm') ne true}">
	    <button type="button" id="crawling" class="btn btn-secondary" onclick="javascript: form.action='updateDataConfirm.do'; form.submit()">UpdateData</button>
	  	</c:if>
	  	</div>
  	</div>
</div>
</div>
</form>
</body>
</html>