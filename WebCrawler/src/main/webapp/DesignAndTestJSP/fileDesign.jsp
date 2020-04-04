<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="../resources/css/fileUpload.css" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function readURL(input) {
	  if (input.files && input.files[0]) {

	    var reader = new FileReader();

	    reader.onload = function(e) {
	      $('.image-upload-wrap').hide();
	      $('.file-upload-content').show();
	      $('.image-title').html(input.files[0].name);
	    };

	    reader.readAsDataURL(input.files[0]);

	  } else {
	    removeUpload();
	  }
	}

	function removeUpload() {
	  $('.file-upload-input').replaceWith($('.file-upload-input').clone());
	  $('.file-upload-content').hide();
	  $('.image-upload-wrap').show();
	}
	$('.image-upload-wrap').bind('dragover', function () {
	        $('.image-upload-wrap').addClass('image-dropping');
	    });
	    $('.image-upload-wrap').bind('dragleave', function () {
	        $('.image-upload-wrap').removeClass('image-dropping');
	});
</script>
</head>
<body>
<script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<div class="file-upload">
	  <div class="image-upload-wrap">
	    <input class="file-upload-input" type='file' onchange="readURL(this);"/>
	    <div class="drag-text">
	      <h3>Drag and drop or Click hear</h3>
	    </div>
	  </div>
	  <div class="file-upload-content">
	  	<span class="image-title"></span>
	    <div class="image-title-wrap">
	      <button type="button" onclick="removeUpload()" class="remove-image">Change Upload File</button>

	    </div>
	  </div>
</div>
</body>
</html>