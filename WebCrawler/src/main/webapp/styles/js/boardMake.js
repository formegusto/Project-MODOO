var tmp;

function titleEnter(content){
	tmp = content.innerText;
	content.style.background = "black";
	content.style.color = "white";
	content.innerText = "º±≈√!";
}

function titleLeave(content){
	content.style.background = "white";
	content.style.color = "black";
	content.innerText = tmp;
}

function titleClick(content, seq, type){
	content.style.background = "black";
	content.style.color = "white";
	content.removeAttribute('onmouseleave');
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