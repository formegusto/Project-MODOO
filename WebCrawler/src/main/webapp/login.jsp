<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/css/login.css" rel="stylesheet"></link>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>loginPage</title>
</head>
<body>
	<div class="sidenav">
         <div class="login-main-text">
            <h2>WCrawl<br> Login Page</h2>
            <p>Login or register from here to access.</p>
         </div>
      </div>
      <div class="main">
         <div class="col-md-6 col-sm-12">
            <div class="login-form">
               <form id="form" method="post">
                  <div class="form-group">
                     <label>User Name</label>
                     <input type="text" name="id" class="form-control" placeholder="Input ID">
                  </div>
                  <div class="form-group">
                     <label>Password</label>
                     <input type="password" name="password" class="form-control" placeholder="Input Password">
                  </div>
                  <button type="submit" class="btn btn-black" onclick="javascript: form.action='login.do'" style="color: white;">Login</button>
                  <button type="submit" class="btn btn-secondary" onclick="javascript: form.action='register.jsp'">Register</button>
               </form>
            </div>
         </div>
      </div>
</body>
</html>