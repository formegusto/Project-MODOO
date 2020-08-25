function onTm(iseq,ttype){
	let loading = document.getElementsByClassName('loading')[0];
	loading.className = "loading active";
	
	location.href="tm.do?iseq=" + iseq + "&ttype=" + ttype; 
}