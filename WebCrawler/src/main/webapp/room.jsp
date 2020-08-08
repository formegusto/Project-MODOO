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
				�ȳ��ϼ��� ä�ù��Դϴ�.
			</div>
			<div id="chat" class="chat">
				<div class="me">
					<div class="chatContent">
						�翬�� ����!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����? �̰� ���� ��� �Ẽ�Կ� �� ����.
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����?
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�����ŵ���
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ȳ��ϼ���
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�翬�� ����!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����?
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�����ŵ���
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ȳ��ϼ���
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�翬�� ����!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����?
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�����ŵ���
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ȳ��ϼ���
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�翬�� ����!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����?
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�����ŵ���
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ȳ��ϼ���
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�翬�� ����!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����?
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�����ŵ���
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ȳ��ϼ���
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�翬�� ����!
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ʹ��ؿ� ���� ������ �����?
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
				<div class="me">
					<div class="chatContent">
						�����ŵ���
					</div>
				</div>
				<div class="notme">
					<div class="chatContent">
						�ȳ��ϼ���
					</div>
					<span class="chatUser">
						�����̸�
					</span>
				</div>
			</div>
			<div class="chatForm">
				<input name="message" id="message" type="text" placeholder="�޼��� ����" onkeypress="javasciprt:if(event.keyCode==13){onMessage()}"/>
				<button onclick="onMessage()">����</button>
			</div>
		</div>
		<div class="userDiv">
			<div class="userHeader">
				������ ����Ʈ
			</div>
			<div class="userList">
				<div class="user">
					������
				</div>
				<div class="user">
					������
				</div>
				<div class="user">
					������
				</div>
				<div class="user">
					������
				</div>
				<div class="user">
					������
				</div>
			</div>
			<div class="userFooter">
				<button>
					������
				</button>
			</div>
		</div>
	</div>
</section>
</body>
</html>