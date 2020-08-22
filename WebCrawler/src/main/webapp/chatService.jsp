<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/chatService.css?z"></link>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script type="text/javascript" src="styles/js/chatService.js?"></script>
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
					<canvas class="visualcanvas" style="width:500px; height: 500px;"></canvas>
				</div>
			</div>
		</div>
		<div class="chatDiv">
			<div class="chatTitle">
				${room.title }
			</div>
			<div id="chat" class="chat">
				<div class="me">
					<div class="chatContent">
						당연히 모르죠!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요? 이거 완전 길게 써볼게요 개 길어요.
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요?
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						누구신데요
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						안녕하세요
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						당연히 모르죠!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요?
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						누구신데요
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						안녕하세요
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						당연히 모르죠!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요?
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						누구신데요
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						안녕하세요
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						당연히 모르죠!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요?
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						누구신데요
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						안녕하세요
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						당연히 모르죠!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요?
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						누구신데요
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						안녕하세요
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						당연히 모르죠!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						너무해요 제가 누군지 몰라요?
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						누구신데요
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						안녕하세요
					</div>
					<span class="chatUser">
						유저이름
					</span>
				</div>
			</div>
			<div class="chatForm">
				<input name="message" id="message" type="text" placeholder="메세지 적기" onkeypress="javasciprt:if(event.keyCode==13){onMessage()}"/>
				<button onclick="onMessage()">전송</button>
			</div>
		</div>
		<!--  
		<div class="userDiv">
			<div class="userHeader">
				접속자 리스트
			</div>
			<div class="userList">
				<div class="user">
					노태헌
				</div>
				<div class="user">
					김태헌
				</div>
				<div class="user">
					이태헌
				</div>
				<div class="user">
					박태헌
				</div>
				<div class="user">
					최태헌
				</div>
			</div>
			<div class="userFooter">
				<button>
					나가기
				</button>
			</div>
		</div>
		-->
	</div>
</section>
</body>
</html>