// ХыЧе
function onKeyword(type){
	let keywordInput = document.getElementById('keyword');
	
	location.href= type + "Service.do?keyword=" + keywordInput.value;
}

function onInsertPage(serviceName){
	if(serviceName === 'crawling'){
		location.href = 'infoInsert.jsp';
	}
	else if(serviceName === 'csv'){
		location.href = 'csvInsert.jsp';
	}
}