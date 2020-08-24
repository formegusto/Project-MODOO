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
		/*
			이미 있는 항목인지 검사
		*/
		let updateTable = document.getElementsByClassName('updateTable')[0];
		let newTr = document.createElement('tr');
		newTr.innerHTML = "<td>" + nowData + "</td>" +
				"<td>-></td>" +
				"<td>" + e.value + "</td>" ;
		updateTable.appendChild(newTr);
		
		updateDseq.push(dseq);
		updateData.push(e.value);
		
		console.log(updateDseq);
		console.log(updateData);
		
		let updateConfirm = document.getElementsByClassName('updateConfirm ' + dseq)[0];
		updateConfirm.className = 'updateConfirm active ' + dseq;
	}
}

function onUpSubmit() {
	/*
	let dseqInput = document.getElementById('upDseqInput');
	dseqInput.value = [...updateDseq];
	
	let dataInput = document.getElementById('upDataInput');
	dataInput.value = [...updateData];
	*/
	let inputDseqList = document.getElementsByClassName('inputDseqList')[0];
	let inputDataList = document.getElementsByClassName('inputDataList')[0];
	
	for(let i=0;i<updateDseq.length;i++){
		let inputDseq = document.createElement('input');
		inputDseq.setAttribute('type','hidden');
		inputDseq.setAttribute('name','dseqList');
		inputDseq.setAttribute('value',updateDseq[i]);
		
		let inputData = document.createElement('input');
		inputData.setAttribute('type','hidden');
		inputData.setAttribute('name','dataList');
		inputData.setAttribute('value',updateData[i]);
		
		inputDseqList.appendChild(inputDseq);
		inputDataList.append(inputData);
	}

	document.updateForm.submit();
}

function onCheck() {
	document.getElementsByClassName("loading")[0].className = "loading active";
}

function submitCancle() {
	let loading = document.getElementsByClassName("loading active")[0];
	loading.style.animation = "fadeOut 1s forwards";
	loading.addEventListener("animationend", function() {
		loading.style.animation = "";
		loading.className = "loading";
		this.removeEventListener("animationend",arguments.callee);
	}, false);
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