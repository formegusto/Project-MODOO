function onClick(type){
	if(type === 'save'){
		document.saveInfo.submit();
	}
}

function allDataContent(e){
	let whitespace = e.style.whiteSpace;
	console.log(whitespace);
	if(!whitespace){
		e.style.whiteSpace = "normal";
	} else if(whitespace === "normal"){
		e.style.whiteSpace = "nowrap";
	} else if(whitespace === "nowrap"){
		e.style.whiteSpace = "normal";
	}
}