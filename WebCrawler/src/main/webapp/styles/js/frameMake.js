let iseqList = [];

function onSubmit(){
	let loading = document.getElementsByClassName('loading')[0];
	loading.className = "loading active";
	
	let input = document.getElementById('iseqList');
	input.value = iseqList;
	
	document.frameForm.submit();
}

function onFrame(iseq){
	let ptag = document.getElementsByClassName("ptag " + iseq)[0];
	let contentCard = document.getElementsByClassName("contentCard " + iseq)[0];
	let chkBlind = document.getElementsByClassName("checkBlind " + iseq)[0];
	let btnGrp = document.getElementsByClassName("buttonGroup " + iseq)[0];
	let canBtnGrp = document.getElementsByClassName("buttonGroup cancle " + iseq)[0];
	
	canBtnGrp.style.display = "flex";
	btnGrp.style.display = "none";
	contentCard.style.background = "white";
	chkBlind.style.opacity = 1;
	ptag.innerText = "나를 버릴건가요?";
	
	iseqList.push(iseq);
	
	console.log(iseqList);
}

function offFrame(iseq){
	let ptag = document.getElementsByClassName("ptag " + iseq)[0];
	let contentCard = document.getElementsByClassName("contentCard " + iseq)[0];
	let chkBlind = document.getElementsByClassName("checkBlind " + iseq)[0];
	let btnGrp = document.getElementsByClassName("buttonGroup " + iseq)[0];
	let canBtnGrp = document.getElementsByClassName("buttonGroup cancle " + iseq)[0];
	
	canBtnGrp.style.display = "";
	btnGrp.style.display = "";
	contentCard.style.background = "";
	chkBlind.style.opacity = "";
	ptag.innerText = "저를 선택해주세요!";

	iseqList = iseqList.filter(iseq_ => iseq_ !== iseq);
	
	console.log(iseqList);
}