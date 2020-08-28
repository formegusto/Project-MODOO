// 통합
function onKeyword(iseq,mode){
	let keywordInput = document.getElementById('keyword');
	
	location.href="dataService.do?iseq=" + iseq + "&mode=" + mode + "&keyword=" + keywordInput.value;
}

// read 용
function onClick(type){
	if(type === 'save'){
		document.saveInfo.submit();
	}
}

function allDataContent(e){
	let whitespace = e.style.whiteSpace;
	console.log(whitespace);
	if(!whitespace){
		e.style.whiteSpace = "normal";
	} else if(whitespace === "normal"){
		e.style.whiteSpace = "nowrap";
	} else if(whitespace === "nowrap"){
		e.style.whiteSpace = "normal";
	}
}

// delete 용
let delDataSeq = [];
function pushDelData(e, dseq){
	e.className = "dataTools active";
	e.setAttribute('onclick', 'popDelData(this,'+ dseq + ')');
	//e.innerText = "아직 취소할 기회는 드리겠습니다.";
	
	delDataSeq.push(dseq);
	
	console.log("push ==> " + delDataSeq);
}

function popDelData(e, dseq){
	e.className = "dataTools";
	e.setAttribute('onclick', 'pushDelData(this,'+ dseq + ')');
	//e.innerText = "지나간 데이터는 돌아오지 않습니다.";
	
	delDataSeq = delDataSeq.filter(delData => delData != dseq);
	
	console.log("pop ==> " + delDataSeq);
}

function onDelSubmit() {
	if(!delDataSeq.length){
		console.log("Empty Array")
		alert('빈 상태로 하시게요?');
	}
	let input = document.getElementById('delDseqInput');
	input.value = delDataSeq;
	
	document.delForm.submit();
}

// update 용
let nowData;
let updateDseq = [];
let updateData = [];

function changeStart(e){
	console.log("change Start");
	nowData = e.value;
}

function changeEnd(e, dseq) {
	console.log("change End");
	if(nowData === e.value){
		console.log("changeData Nope");
	} else {
		updateDseq.push(dseq);
		updateData.push(e.value);
		
		console.log(updateDseq);
		console.log(updateData);
		
		let updateConfirm = document.getElementsByClassName('updateConfirm ' + dseq)[0];
		updateConfirm.style.opacity = 1; 
	}
}

function onUpSubmit() {
	let dseqInput = document.getElementById('upDseqInput');
	dseqInput.value = updateDseq;
	
	let dataInput = document.getElementById('upDataInput');
	dataInput.value = updateData;
	
	document.updateForm.submit();
}

let fseq

function onDownload(seq) {
	fseq = seq;
	let loading = document.getElementsByClassName('csvloading')[0];
	loading.className = "csvloading active";
}

function submitDownload(){
	let ctitleInput = document.getElementById("ctitle");
	let ctitle = ctitleInput.value;
	location.href='csvDownload.do?fseq=' + fseq + '&ctitle=' + ctitle;
}

function appendClick(iseq) {
	let dataTbody = document.getElementsByClassName('dataTr ' + iseq)[0];
	let newTr = document.createElement('tr');
	
	newTr.innerHTML = "<td class=\"dataItem\">" + 
			"<textarea class=\"appendData " + iseq + "\"></textarea>"
			"</td>";
			
	dataTbody.appendChild(newTr);
}

function onAppend() {
	let appendData = document.getElementsByClassName('appendData');
	let inputDataList = document.getElementsByClassName('inputDataList')[0];
	
	for(let i=0;i<appendData.length;i++){
		let iseq = appendData[i].className.split(" ")[1];
		let inputData = document.createElement('input');
		inputData.setAttribute('type','hidden');
		inputData.setAttribute('name','data');
		inputData.setAttribute('value',iseq + ',' + appendData[i].value);
		
		inputDataList.append(inputData);
	}
	
	document.appendForm.submit();
}

function onDownloadCancle() {
	let loading = document.getElementsByClassName('csvloading active')[0];
	loading.className = "csvloading";
}