function onClick(type){
	if(type === 'save'){
		document.saveInfo.submit();
	}
	if(type === 'replace'){
		document.saveInfo.action = "replaceInfo.do";
		document.saveInfo.submit();
	}
	if(type === 'append'){
		document.saveInfo.action = "appendInfo.do";
		document.saveInfo.submit();
	}
}