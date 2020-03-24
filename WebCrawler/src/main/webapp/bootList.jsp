<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/css/chat.css" rel="stylesheet"></link>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://use.fontawesome.com/63a7f56cba.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>Top Test</title>
</head>
<body>

<!-- Navbar : Login, 알람 정보 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">WCrawl</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Features</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Pricing</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
  </div>
</nav>

<!-- Navs : 보드 메뉴 -->
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link active" href="#">Active</a>
  <a class="nav-item nav-link" href="#">Link</a>
  <a class="nav-item nav-link" href="#">Link</a>
  <a class="nav-item nav-link disabled" href="#">Disabled</a>
</nav>

<!-- InfoList Design -->
<div class="container" style="margin-top: 15px;">
<div class="row">
<!-- 분기문으로 4개씩 쪼개주도록 설정해야 할듯함. -->
	<div class="card-deck">
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
	</div>
	<div class="card-deck">
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
		<div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
		  <div class="card-header">Header <button type="button" class="btn btn-outline-light" style="float: right">X</button>
		  </div>
		  <div class="card-body">
		    <h5 class="card-title">Dark card title</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		  </div>
		</div>
	</div>
</div>
</div>
<hr/>

<!-- BoardList RoomList Design -->
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">First</th>
	      <th scope="col">Last</th>
	      <th scope="col">Handle</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Mark</td>
	      <td>Otto</td>
	      <td>@mdo</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Jacob</td>
	      <td>Thornton</td>
	      <td>@fat</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>Larry</td>
	      <td>the Bird</td>
	      <td>@twitter</td>
	    </tr>
	  </tbody>
	</table>
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups"
		style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	    <button type="button" class="btn btn-secondary">1</button>
	    <button type="button" class="btn btn-secondary">2</button>
	    <button type="button" class="btn btn-secondary">3</button>
	    <button type="button" class="btn btn-secondary">4</button>
	  	</div>
	  	<div class="btn-group mr-2" role="group" aria-label="Second group">
	    <button type="button" class="btn btn-secondary">5</button>
	    <button type="button" class="btn btn-secondary">6</button>
	    <button type="button" class="btn btn-secondary">7</button>
	  	</div>
	  	<div class="btn-group" role="group" aria-label="Third group">
	    <button type="button" class="btn btn-secondary">8</button>
  	</div>
</div>
</div>
</div>
<hr/>

<!-- getInfo Design -->
<div class="container" style="margin-top: 15px;">
<div class="row">
<div class="col-md-12">
	<nav>
		<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Project Tab 1</a>
			<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Project Tab 2</a>
		</div>
	</nav>
	<div class="tab-content" id="nav-tabContent">
		<!-- Info정보 -->
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
		     <table class="table">
			 <thead class="thead-dark">
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">First</th>
			      <th scope="col">Last</th>
			      <th scope="col">Handle</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <th scope="row">1</th>
			      <td>Mark</td>
			      <td>Otto</td>
			      <td>@mdo</td>
			    </tr>
			    <tr>
			      <th scope="row">2</th>
			      <td>Jacob</td>
			      <td>Thornton</td>
			      <td>@fat</td>
			    </tr>
			    <tr>
			      <th scope="row">3</th>
			      <td>Larry</td>
			      <td>the Bird</td>
			      <td>@twitter</td>
			    </tr>
			</tbody>
			</table>
		</div>
		<!-- Data정보 -->
        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
			<table class="table">
	  			<thead class="thead-dark">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">First</th>
				      <th scope="col">Last</th>
				      <th scope="col">Handle</th>
				    </tr>
	  			</thead>
	  			<tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>Mark</td>
				      <td>Otto</td>
				      <td>@mdo</td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>Jacob</td>
				      <td>Thornton</td>
				      <td>@fat</td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>Larry</td>
				      <td>the Bird</td>
				      <td>@twitter</td>
				    </tr>
	  			</tbody>
			</table>
        </div>
	</div>
</div>
</div>
</div>
<hr/>

<!-- getBoard.Design -->
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">First</th>
	      <th scope="col">Last</th>
	      <th scope="col">Handle</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Mark</td>
	      <td>Otto</td>
	      <td>@mdo</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Jacob</td>
	      <td>Thornton</td>
	      <td>@fat</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>Larry</td>
	      <td>the Bird</td>
	      <td>@twitter</td>
	    </tr>
	    <tr>
	    	<td colspan="4">
	    	<nav>
			<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-a" role="tab" aria-controls="nav-test" aria-selected="true">Project Tab 1</a>
			<a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-b" role="tab" aria-controls="nav-profile" aria-selected="false">Project Tab 2</a>
			</div>
			</nav>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<div class="tab-content" id="nav-tabContent">
			<!-- Info정보 -->
			<div class="tab-pane fade show active" id="nav-a" role="tabpanel" aria-labelledby="nav-home-tab">
			     <table class="table">
				 <thead class="thead-dark">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">First</th>
				      <th scope="col">Last</th>
				      <th scope="col">Handle</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td>Mark</td>
				      <td>Otto</td>
				      <td>@mdo</td>
				    </tr>
				    <tr>
				      <th scope="row">2</th>
				      <td>Jacob</td>
				      <td>Thornton</td>
				      <td>@fat</td>
				    </tr>
				    <tr>
				      <th scope="row">3</th>
				      <td>Larry</td>
				      <td>the Bird</td>
				      <td>@twitter</td>
				    </tr>
				</tbody>
				</table>
				</div>
				<!-- Data정보 -->
		        <div class="tab-pane fade" id="nav-b" role="tabpanel" aria-labelledby="nav-profile-tab">
					<table class="table">
			  			<thead class="thead-dark">
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">First</th>
						      <th scope="col">Last</th>
						      <th scope="col">Handle</th>
						    </tr>
			  			</thead>
			  			<tbody>
						    <tr>
						      <th scope="row">1</th>
						      <td>Mark</td>
						      <td>Otto</td>
						      <td>@mdo</td>
						    </tr>
						    <tr>
						      <th scope="row">2</th>
						      <td>Jacob</td>
						      <td>Thornton</td>
						      <td>@fat</td>
						    </tr>
						    <tr>
						      <th scope="row">3</th>
						      <td>Larry</td>
						      <td>the Bird</td>
						      <td>@twitter</td>
						    </tr>
			  			</tbody>
					</table>
		        </div>
		        </div>
		        </td>
	    </tr>
	  </tbody>
	</table>
</div>
</div>
<hr/>

<!-- getRoomDesign -->
<div class="container">
<div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>접속자리스트</h4>
            </div>
          </div>
          <div class="inbox_chat">
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_ib">
                  <h5>Sunil Rajput</h5>
                </div>
              </div>
            </div>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_ib">
                  <h5>Sunil Rajput</h5>
                </div>
              </div>
            </div>
            <div class="chat_list">
              <div class="chat_people">
                <div class="chat_ib">
                  <h5>Sunil Rajput</h5>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="mesgs">
          <div class="msg_history">
            <div class="incoming_msg">
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p>Test which is a new approach to have all
                    solutions</p>
                  <span class="time_date"> 11:01 AM    |    June 9</span></div>
              </div>
            </div>
            <div class="outgoing_msg">
              <div class="sent_msg">
                <p>Test which is a new approach to have all
                  solutions</p>
                <span class="time_date"> 11:01 AM    |    June 9</span> </div>
            </div>
            <div class="incoming_msg">
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p>Test, which is a new approach to have</p>
                  <span class="time_date"> 11:01 AM    |    Yesterday</span></div>
              </div>
            </div>
            <div class="outgoing_msg">
              <div class="sent_msg">
                <p>Apollo University, Delhi, India Test</p>
                <span class="time_date"> 11:01 AM    |    Today</span> </div>
            </div>
            <div class="incoming_msg">
              <div class="received_msg">
                <div class="received_withd_msg">
                  <p>We work directly with our designers and suppliers,
                    and sell direct to you, which means quality, exclusive
                    products, at a price anyone can afford.</p>
                  <span class="time_date"> 11:01 AM    |    Today</span></div>
              </div>
            </div>
          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input type="text" class="write_msg" placeholder="Type a message" />
              <button class="msg_send_btn" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>


<!-- crawlerAddDesign -->
<div class="container" style="margin-top: 15px;">
<div class="row">
<div style="margin:auto;">
<form>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Email</label>
      <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Password</label>
      <input type="password" class="form-control" id="inputPassword4" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Address</label>
    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St">
  </div>
  <div class="form-group">
    <label for="inputAddress2">Address 2</label>
    <input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor">
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputCity">City</label>
      <input type="text" class="form-control" id="inputCity">
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">State</label>
      <select id="inputState" class="form-control">
        <option selected>Choose...</option>
        <option>...</option>
      </select>
    </div>
    <div class="form-group col-md-2">
      <label for="inputZip">Zip</label>
      <input type="text" class="form-control" id="inputZip">
    </div>
  </div>
  <div class="form-group">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" id="gridCheck">
      <label class="form-check-label" for="gridCheck">
        Check me out
      </label>
    </div>
  </div>
  <button type="submit" class="btn btn-primary">Sign in</button>
</form>
</div>
</div>
</div>
</body>
</html>