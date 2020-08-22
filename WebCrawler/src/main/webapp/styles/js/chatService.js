window.onload = () => {
	chat = document.getElementById("chat");
	chat.scrollTop = chat.scrollHeight;
	
	tm = document.getElementById("tm");
	tm.style.display = "none";
	
	visual = document.getElementById("visual");
	visual.style.display = "none";
}

function onMessage() {
	var messageInput = document.getElementById("message");
	var message = messageInput.value;
	
	console.log(message);
	
	var chat = document.getElementById("chat");
	
	var meDiv = document.createElement('div');
	meDiv.className = "me";
	
	var me = document.createElement('div');
	me.className = "chatContent";
	
	var meMsg = document.createTextNode(message);
	
	me.appendChild(meMsg);
	meDiv.appendChild(me);
	chat.appendChild(meDiv);
	
	chat.scrollTop = chat.scrollHeight;
	messageInput.value="";
}

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