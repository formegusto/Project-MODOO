let tmpType = '';

function changeType(e,type){
	let itype = document.getElementById("itype");
	if(tmpType === ''){
		e.style.opacity = ".3";
	} else {
		e.style.opacity = ".3";
		tmpType.style.opacity = "1";
	}
	tmpType = e;
	itype.value = type
}

function onClick(clickType){
	if(clickType === 'confirm'){
		document.info.submit();
	} else {
		location.href="infoInsert.jsp";
	}
}