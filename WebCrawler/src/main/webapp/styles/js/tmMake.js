function onTm(iseq){
	let loading = document.getElementsByClassName('loading')[0];
	loading.className = "loading active";
	
	location.href="tm.do?iseq=" + iseq;
}