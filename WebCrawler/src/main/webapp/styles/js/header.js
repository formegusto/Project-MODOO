function selTogether(type){
	if(type === 'cancle') {
		var togetherDiv = document.getElementsByClassName('together active')[0];
		togetherDiv.className = "together";
	} else if (type === 'make') {
		location.href = "boardMake.do";
	}
}

function onTogether() {
	var togetherDiv = document.getElementsByClassName('together')[0];
	togetherDiv.className = "together active";
}