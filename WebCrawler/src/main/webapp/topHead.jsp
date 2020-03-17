<%@page import="java.util.List"%>
<%@page import="com.crawler.biz.info.InfoVO"%>
<%@page import="com.crawler.biz.data.DataVO"%>
<%@page import="com.crawler.biz.common.WCrawl"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<c:if test="${user == null and pageContext.request.requestURI != '/WCrawl/login.jsp'}">
	<script>
		alert("로그인 후 이용해주세요.");
		location.href="/WCrawl/login.jsp";
	</script>
</c:if>
<c:if test="${user != null and (pageContext.request.requestURI == '/WCrawl/login.jsp' or pageContext.request.requestURI == '/WCrawl/register.jsp'
 or pageContext.request.requestURI == '/WCrawl/topHead.jsp')}">
	<script>
		alert("로그인 상태입니다.");
		location.href="/WCrawl/getInfoList.do";
	</script>
</c:if>
