<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/boardDetailService.css?a"></link>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script type="text/javascript" src="styles/js/boardDetailService.js?e"></script>
<script>
window.addEventListener("load", function() {
	let visualCanvas = document.getElementsByClassName('visualcanvas')[0];
	
	let title = '${visual.title}';
	let vtype = ${visual.vtype};
	let color = ${visual.color};
	let labels = ${visual.labels};
	let datas = ${visual.datas};
	
	console.log(title);
	console.log(vtype);
	console.log(color);
	console.log(labels);
	console.log(datas);
	
	setVisualState(visualCanvas,title,vtype,labels,datas,color);
})
</script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="data">
			<ul class="dataHeader">
				<li class="dataHeaderitem active" onclick="changeType(this,'frame')">
					프레임
				</li>
				<c:if test="${tm.ttype eq 'wordcloud' }">
						<li onclick="changeType(this,'tm')">
							텍스트마이닝
						</li>
				</c:if>
				<c:if test="${tm.ttype eq 'sna'}">
						<li onclick="changeType(this,'tm')">
							텍스트마이닝
						</li>
				</c:if>
				<c:if test="${tm.ttype eq 'sentiment'}">
						<li onclick="changeTypeSenti(this,'tm',${tm.tvi.positive},${tm.tvi.negative }, ${tm.tvi.neutral })">
							텍스트마이닝
						</li>
				</c:if>
				<li onclick="changeType(this,'visual')">
					시각화
				</li>
			</ul>
			<div class="dataContent">
				<div id="frame" class="content active">
					<table>
						<tbody>
						<c:forEach items="${fhiList }" var="fhi">
							<tr>
								<td class="fieldHeader">
									${fhi.field }
								</td>
								<c:forEach items="${fhi.dataList }" var="dvo">
									<td>
										${dvo.data }
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="tm">
					<c:if test="${tm.ttype eq 'wordcloud' }">
						<c:import url="/userRview/${tm.tseq }.html" charEncoding="EUC-KR" />
					</c:if>
					<c:if test="${tm.ttype eq 'sna'}">
						<img src="userRview/${tm.tseq }.png"/>
					</c:if>
					<c:if test="${tm.ttype eq 'sentiment'}">
						<canvas class="senticanvas"></canvas>
					</c:if>
				</div>				
				<div id="visual">
					<canvas class="visualcanvas" style="width:500px; height: 500px;"></canvas>
				</div>
			</div>
		</div>
		<div class="boardDiv">
			<div class="board">
				<h1 class="boardTitle">${board.title }</h1>
				<hr/>
				<div class="boardContent">
					${board.content }
				</div>
				<hr/>
			</div>
			<hr/>
			<div class="comment">
				<div class="commentForm">
					<form method="post" action="commentService.do" name="form">
						<c:if test="${user == null}">
							<textarea class="commentArea" name="content" disabled="disabled">로그인 후 이용 가능합니다.</textarea>
						</c:if>
						<c:if test="${user != null}">
							<textarea placeholder="욕은 안됩니다!" class="commentArea" name="content" onkeypress="javasciprt:if(event.keyCode==13){document.form.submit;}"></textarea>
						</c:if>
						<button type="submit" class="commentButton">댓글 달기</button>
						<input type="hidden" name="bseq" value="${board.bseq }" />
					</form>
				</div>
				<c:forEach items="${commentList }" var="comment">
				<div class="commentBox">
					<div class="commentWriter">${comment.id }</div>
					<hr/>
					<div class="commentContent">${comment.content }</div>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
</section>
</body>
</html>