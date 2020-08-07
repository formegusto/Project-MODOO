function onInsertPage(serviceName){
	if(serviceName === 'crawling'){
		location.href = 'infoInsert.jsp';
	}
	else if(serviceName === 'csv'){
		location.href = 'csvInsert.jsp';
	}
}