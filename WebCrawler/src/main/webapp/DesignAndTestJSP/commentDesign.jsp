<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap 4 Blog Post Comments Section Design</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/resources/comment.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-12">
      <div class="comments">
        <div class="comments-details">
          <span class="total-comments comments-sort">117 Comments</span>    
        </div>
        <div class="comment-box add-comment">
          <span class="commenter-pic">
          </span>
          <span class="commenter-name">
            <input type="text" placeholder="Add a public comment" name="Add Comment">
            <button type="submit" class="btn btn-default">Register</button>
          </span>
        </div>
        <div class="comment-box">
          <span class="commenter-pic">
          </span>
          <span class="commenter-name">
            <a href="#">Happy uiuxStream</a>
          </span>       
          <p class="comment-txt more">Suspendisse massa enim, condimentum sit amet maximus quis, pulvinar sit amet ante. Fusce eleifend dui mi, blandit vehicula orci iaculis ac.</p>
        </div>
        <div class="comment-box">
          <span class="commenter-pic">
          </span>
          <span class="commenter-name">
            <a href="#">Happy uiuxStream</a>
          </span>       
          <p class="comment-txt more">Suspendisse massa enim, condimentum sit amet maximus quis, pulvinar sit amet ante. Fusce eleifend dui mi, blandit vehicula orci iaculis ac.</p>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>