function onClick(type){
	let titleInput = document.getElementById("titleInput");
	let title = document.getElementById("title");
	
	title.value = titleInput.value;
	
	if(type === 'save'){
		document.saveFrame.submit();
	}
}