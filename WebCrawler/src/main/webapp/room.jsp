<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/room.css?g"></link>
<script type="text/javascript" src="styles/js/room.js?z"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<div class="data">
			<jsp:include page="/confirmRview/1.html"/>
		</div>
		<div class="chatDiv">
			<div class="chatTitle">
				안녕하세요 채팅방입니다.
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
	</div>
</section>
</body>
</html>