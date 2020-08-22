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
					������
				</li>
				<li onclick="changeType(this,'tm')">
					�ؽ�Ʈ���̴�
				</li>
				<li onclick="changeType(this,'visual')">
					�ð�ȭ
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
		<!--  
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
		-->
	</div>
</section>
</body>
</html>