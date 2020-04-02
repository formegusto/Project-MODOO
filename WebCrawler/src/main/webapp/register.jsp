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
<title>RegisterPage</title>
</head>
<body>
	<div class="sidenav">
         <div class="login-main-text">
            <h2>MODOO<br> Register Page</h2>
            <p>Management Of Data,Our,Ours</p>
         </div>
      </div>
      <div class="main">
         <div class="col-md-6 col-sm-12">
            <div class="register-form">
               <form id="form" method="post">
                  <div class="form-group">
                     <label>ID</label>
                     <input type="text" name="id" class="form-control" placeholder="Input ID">
                  </div>
                  <div class="form-group">
                     <label>Password</label>
                     <input type="password" name="password" class="form-control" placeholder="Input Password">
                  </div>
                  <div class="form-group">
                     <label>Name</label>
                     <input type="text" name="name" class="form-control" placeholder="Input Name">
                  </div>
                  <div class="form-group">
                     <label>phone</label>
                     <input type="text" name="phone" class="form-control" placeholder="Input Phone">
                  </div>
                  <div class="form-group">
                     <label>Email</label>
                     <input type="text" name="email" class="form-control" placeholder="Input Email">
                  </div>
                  <div class="form-group">
                     <label>Company</label>
                     <input type="text" name="company" class="form-control" placeholder="Input Company">
                  </div>
                  <button type="submit" class="btn btn-secondary" onclick="javascript: form.action='register.do'">Register</button>
                  <button type="submit" class="btn btn-black" onclick="javascript: form.action='login.jsp'" style="color: white;">Cancel</button>
               </form>
            </div>
         </div>
      </div>
</body>
</html>