<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="styles/css/account.css"></link>
<script type="text/javascript" src="styles/js/account.js"></script>
<title>MODOO</title>
</head>
<body>
	<div class="logoBox">
		<div class="logoForm">
			Management<br/>
			<span>Of Data</span><br/>
			Our Ours
		</div>
	</div>
	<div class="inputBox">
		<div class="inputFormBox">
			<ul class="inputNav">
				<li id="signinLi" onClick="onChangeInputType(this,'signin')">Sign-In</li>
				<li id="signupLi" onClick="onChangeInputType(this,'signup')">Sign-Up</li>
			</ul>
			<jsp:include page="components/signin.html"/>
			<jsp:include page="components/signup.html"/>
		</div>
	</div>
</body>
</html>