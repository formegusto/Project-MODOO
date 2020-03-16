<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>loginPage</title>
</head>
<body>
<center>
	<h1>Login Page</h1>
	<hr>
	<form action="register.do" method="post">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange">ID</td>
			<td><input type="text" name="id"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">NAME</td>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">PHONE</td>
			<td><input type="text" name="phone"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">EMAIL</td>
			<td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td bgcolor="orange">COMPANY</td>
			<td><input type="text" name="company"/></td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="register"/>
			</td>
		</tr>
	</table>
	</form>
</center>
</body>
</html>