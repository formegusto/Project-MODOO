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