// ХыЧе
function onKeyword(type){
	let keywordInput = document.getElementById('keyword');
	
	location.href= type + "Service.do?keyword=" + keywordInput.value;
}

function onInsertPage(serviceName){
	if(serviceName === 'crawling'){
		var togetherDiv = document.getElementsByClassName('infoType')[0];
		togetherDiv.className = "infoType active";
		// location.href = 'infoInsert.jsp';
	}
	else if(serviceName === 'csv'){
		location.href = 'csvInsert.jsp';
	}
}

function selInfoType(type){
	if(type === 'cancle') {
		var infoTypeDiv = document.getElementsByClassName('infoType active')[0];
		infoTypeDiv.className = "infoType";
	} else if (type === 'text') {
		location.href = "infoInsert.do?type=text";
	} else if (type === 'link') {
		location.href = "infoInsert.do?type=link";
	} else if (type === 'linklist') {
		location.href = "infoLinkList.do";
	}
}