let numIseq;
let strIseq;				
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
		ptag.innerText = "���ڴ�!";
	} else {
		canBtnGrp.style.display = "flex";
		btnGrp.style.display = "none";
		contentCard.style.background = "white";
		chkBlind.style.opacity = 1;
		strIseq = iseq;
		ptag.innerText = "���ڴ�!";
	}
	
	console.log("num ==> " + numIseq + "/str ==> " + strIseq);
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
	ptag.innerText = "�� ������ ����Ʈ�� ����?";
	canBtnGrp.style.display = "none";
	btnGrp.style.display = "flex";
	contentCard.style.background = "";
	chkBlind.style.opacity = "";
}