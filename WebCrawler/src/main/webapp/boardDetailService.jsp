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
		
	visualDraw(visualCanvas,title,vtype,labels,datas,color);
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
				<li onclick="changeType(this,'tm')">
					텍스트마이닝
				</li>
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
					<c:import url="/userRview/${tm.tseq }.html" charEncoding="EUC-KR" />
				</div>				
				<div id="visual">
					<canvas class="visualcanvas"></canvas>
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
			</div>
			<hr/>
			<div class="comment">
				<div class="commentForm">
					<textarea placeholder="욕은 안됩니다!"></textarea>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
				<div class="commentBox">
					<div class="commentWriter">작성자</div>
					<hr/>
					<div class="commentContent">댓글내용</div>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>