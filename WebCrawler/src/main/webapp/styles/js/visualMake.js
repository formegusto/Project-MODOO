let numIseq = null;
let strIseq = null;				
function onVisual(iseq,type){
	let ptag = document.getElementsByClassName("ptag " + iseq)[0];
	let contentCard = document.getElementsByClassName("contentCard " + iseq)[0];
	let chkBlind = document.getElementsByClassName("checkBlind " + iseq)[0];
	let btnGrp = document.getElementsByClassName("buttonGroup " + iseq)[0];
	let canBtnGrp = document.getElementsByClassName("buttonGroup cancle " + iseq)[0];
	if(type === 'number'){
		canBtnGrp.style.display = "flex";
		btnGrp.style.display = "none";
		contentCard.style.background = "white";
		chkBlind.style.opacity = 1;
		numIseq = iseq;
		ptag.innerText = "숫자다!";
	} else {
		canBtnGrp.style.display = "flex";
		btnGrp.style.display = "none";
		contentCard.style.background = "white";
		chkBlind.style.opacity = 1;
		strIseq = iseq;
		ptag.innerText = "문자다!";
	}
	
	console.log("num ==> " + numIseq + "/str ==> " + strIseq);
	
	if(numIseq && strIseq){
		let numInput = document.getElementById('numIseq');
		let strInput = document.getElementById('strIseq');
		let loading = document.getElementsByClassName('loading')[0];
		
		loading.className = "loading active";
		
		numInput.value = numIseq;
		strInput.value = strIseq;
	}
}

function onSubmit(color) {
	let colorInput = document.getElementById('color');
	colorInput.value = color;
	
	document.visualForm.submit();
}

function offVisual(iseq){
	console.log("num ==> " + numIseq + "/str ==> " + strIseq);
	
	let ptag = document.getElementsByClassName("ptag " + iseq)[0];
	let contentCard = document.getElementsByClassName("contentCard " + iseq)[0];
	let chkBlind = document.getElementsByClassName("checkBlind " + iseq)[0];
	let btnGrp = document.getElementsByClassName("buttonGroup " + iseq)[0];
	let canBtnGrp = document.getElementsByClassName("buttonGroup cancle " + iseq)[0];
	
	if(numIseq === iseq){
		numIseq = 0;
	} else {
		strIseq = 0;
	}
	ptag.innerText = "이 데이터 리스트는 뭐죠?";
	canBtnGrp.style.display = "none";
	btnGrp.style.display = "flex";
	contentCard.style.background = "";
	chkBlind.style.opacity = "";
}

function onColor(color){
	let colorDiv = document.getElementsByClassName('color ' + color)[0];
	colorDiv.style.opacity = 1;
}

function offColor(color){
	let colorDiv = document.getElementsByClassName('color ' + color)[0];
	colorDiv.style.opacity = 0;
}