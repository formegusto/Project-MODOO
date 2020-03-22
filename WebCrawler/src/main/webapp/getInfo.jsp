<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3c//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Crawler SELECT Page</title>
</head>
<body>
<center>
	<h1>Crawler SELECT Page</h1>
	<hr/>
	<form method="post" name="form">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange" width="70">����</td>
			<td align="left">
				${info.title }
				<input type="hidden" name="seq" value="${info.seq }"/>
				<input type="hidden" name="title" value="${info.title }"/>
			</td>
		</tr>
		<tr>
			<td bgcolor="orange">�ּ�</td>
			<td align="left">
				${info.link }
				<input type="hidden" name="link" value="${info.link }"/>
			</td>
		</tr>
		<tr>
			<td bgcolor="orange">CssQuery</td>
			<td align="left">
				${info.cssQuery }
				<input type="hidden" name="cssQuery" value="${info.cssQuery }"/>
			</td>
		</tr>
		<tr>
			<td bgcolor="orange">����</td>
			<td align="left">
				${info.content }
				<input type="hidden" name="content" value="${info.content }"/>
				<input type="hidden" name="field" value="${info.field }"/>
			</td>
		</tr>
		<c:forEach items="${dataList }" var="dat">
		<tr>
			<td align="left">${info.field }</td>
			<td>${dat.data }</td>
		</tr>
		</c:forEach>
	</table>
	<hr>
	<input type="submit" value="�۸��" onclick="javascript: form.action='getInfoList.do'"/>
	<input type="submit" value="�����;�����Ʈ" onclick="javascript: form.action='updateDataConfirm.do'"/>
	</form>
</center>
</body>
</html>