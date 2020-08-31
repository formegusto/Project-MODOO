window.onload = () => {
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

function mfdDataTrans() {
	var mfdContent = document.getElementsByClassName('mfdContent')[0];
	mfdContent.className = "mfdContent active";
}

function mfdDataTransCancle() {
	var mfdContent = document.getElementsByClassName('mfdContent active')[0];
	mfdContent.className = "mfdContent";
}

