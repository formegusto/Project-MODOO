function titleEnter(content){
	content.style.background = "black";
	content.style.color = "white";
}

function titleLeave(content){
	content.style.background = "white";
	content.style.color = "black";
}

function titleClick(content, seq, type){
	var fseqInput = document.getElementById('fseq');
	var tseqInput = document.getElementById('tseq');
	var vseqInput = document.getElementById('vseq');
	
	if((type === 'frame' && fseqInput.value !== "") || (type === 'tm' && tseqInput.value !== "") ||
		(type === 'visual' && vseqInput.value !== "")){
		alert('종류 당 하나씩만 선택 가능합니다.');		
	}
	else{
		content.style.background = "black";
		content.style.color = "white";
		content.removeAttribute('onmouseleave');
		content.removeAttribute('onmouseenter');
		
		if(type === 'frame'){
			var fseqInput = document.getElementById('fseq');
			fseqInput.value = seq;
		} else if(type === 'tm') {
			var tseqInput = document.getElementById('tseq');
			tseqInput.value = seq;
		} else if(type === 'visual') {
			var vseqInput = document.getElementById('vseq');
			vseqInput.value = seq;
		}
		
		content.setAttribute('onclick', "titleReClick(this,'" + type + "')");
	}
}

function titleReClick(content, type){
	content.style.background = "white";
	content.style.color = "black";
	
	let seq;
	
	if(type === 'frame'){
			var fseqInput = document.getElementById('fseq');
			seq = fseqInput.value;
			fseqInput.value = "";
	} else if(type === 'tm') {
			var tseqInput = document.getElementById('tseq');
			seq = tseqInput.value;
			tseqInput.value = "";
	} else if(type === 'visual') {
			var vseqInput = document.getElementById('vseq');
			seq = vseqInput.value;
			vseqInput.value = "";
	}
	
	content.setAttribute('onclick', "titleClick(this," + seq + ",'" + type + "')");
	content.setAttribute('onmouseleave', "titleLeave(this)");
	content.setAttribute('onmouseenter', "titleEnter(this)");
}

function makeClick() {
	let loading = document.getElementsByClassName('loading')[0];
	loading.className = "loading active";
}

function typeClick(btn, type) {
	btn.style.background = "black";
	btn.style.color = "white";
	btn.style.border = "1px solid white";
	
	var typeInput = document.getElementById('type');
	typeInput.value = type;
}

function onMake() {
	var typeInput = document.getElementById('type');
	if(typeInput.value === 'board'){
		document.board.action = 'boardMake.do';
	} else if(typeInput.value === 'room') {
		document.board.action = 'roomMake.do';
	}
	
	document.board.submit();
}