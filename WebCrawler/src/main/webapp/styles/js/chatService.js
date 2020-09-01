/* Socket Function */
let msgInput;
let chatSocket;
let userId;

/* Custome Message Function */
window.onload = () => {
	msgInput = document.getElementById("message");
	
	chat = document.getElementById("chat");
	chat.scrollTop = chat.scrollHeight;
	
	tm = document.getElementById("tm");
	if(tm){
		tm.style.display = "none";
	}
	
	visual = document.getElementById("visual");
	if(visual) {
		visual.style.display = "none";
	}
	
	var initHeaderType = document.getElementsByClassName("dataHeaderitem")[0];
	initHeaderType.className = "dataHeaderitem active";
	
	var initContentType = document.getElementsByClassName("content")[0];
	var contentId = initContentType.id;
	
	if(contentId === 'visual') {
		return;
	}
	
	initContentType.style.display = "inline";
	initContentType.className = "content active";
}

function onMessage(event) {
	var message = event.data.split("|");
	var type = message[0];
	
	console.log(message);
	
	if(type == "msg") {
		var sender = message[1];
		var content = message[2];
		
		createNotMeMsg(sender, content);
	}
}

function onOpen(event) {
	createNotMeMsg("관리자", userId + "님 환영합니다!");
}

function setChatSocket(rseq, id) {
	chatSocket = new WebSocket('ws://' + window.location.host + '/MODOO/chat.do?' + rseq + '&' + id);
	userId = id; 
	
	chatSocket.onopen = function(event) {
		onOpen(event);
	}
	
	chatSocket.onmessage = function(event) {
		onMessage(event);
	}
}

function createMeMsg(msg){
	var chat = document.getElementById("chat");
	var meDiv = document.createElement('div');
	meDiv.className = "me";
	
	var me = document.createElement('div');
	me.className = "chatContent";
	
	var meMsg = document.createTextNode(msg);
	
	me.appendChild(meMsg);
	meDiv.appendChild(me);
	chat.appendChild(meDiv);
	
	chat.scrollTop = chat.scrollHeight;
}

function createNotMeMsg(id,msg){
	var chat = document.getElementById("chat");
	var notMeDiv = document.createElement('div');
	notMeDiv.className = "notme";
	
	var notMe = document.createElement('div');
	notMe.className = "chatContent";
	
	var notMeUser = document.createElement('span');
	notMeUser.className = "chatUser";
	
	var notMeMsg = document.createTextNode(msg);
	var notMeUserId = document.createTextNode(id);
	
	notMe.appendChild(notMeMsg);
	notMeUser.appendChild(notMeUserId);
	notMeDiv.appendChild(notMe);
	notMeDiv.appendChild(notMeUser);
	chat.appendChild(notMeDiv);
	
	chat.scrollTop = chat.scrollHeight;
}

function sendMessage() {
	var msg = msgInput.value;
	createMeMsg(msg);
	msgInput.value="";
	
	chatSocket.send(msg);
}


/* Visual Function */
let ctx;
let title;
let vtype;
let labels;
let datas;
let color;

function setVisualState(sctx,stitle,svtype,slabels,sdatas,scolor){
	ctx = sctx;
	title = stitle;
	vtype = svtype;
	labels = slabels;
	datas = sdatas;
	color = scolor;
}

function visualDraw() {
	console.log(vtype);
	
	new Chart(ctx, {
    // The type of chart we want to create
    type: vtype,

    // The data for our dataset
    data: {
        labels: labels,
        datasets: [{
            label: title,
            backgroundColor: color,
            borderColor: 'rgb(0,0,0)',
            data: datas
        }]
    	},
	options: {
		legend: {
			maintainAspectRatio: false,
            display: false,
			labels: {
				fontColor: 'white'
			}
		}
		}
	});
	
	console.log(ctx.style.width);
	console.log(ctx.style.height);
}

function changeType(type, name){
	var nowType = document.getElementsByClassName("dataHeaderitem active")[0];
	nowType.className = "";
	
	type.className = "dataHeaderitem active";
	
	var nowContent = document.getElementsByClassName("content active")[0];
	nowContent.style.display = "none";
	nowContent.className = "";
	
	newContent = document.getElementById(name);
	newContent.style.display = "inline";
	newContent.className = "content active";
	
	if(name === 'visual') {
		visualDraw();
	}
}


function changeTypeSenti (type, name,positive, negative, neutral) {
	changeType(type,name);
	
	var sentictx = document.getElementsByClassName("senticanvas")[0];
	new Chart(sentictx, {
    // The type of chart we want to create
    type: 'pie',

    // The data for our dataset
    data: {
        labels: ['긍정','부정','중립'],
        datasets: [{
            label: '감성분석 결과',
            backgroundColor: ['rgb(000,051,255)','rgb(255,051,051)','rgb(255,102,051)'],
            borderColor: 'rgb(0,0,0)',
            data: [positive,negative,neutral]
        }]
    	},
	});
}
