// ХыЧе
function onKeyword(){
	let keywordInput = document.getElementById('keyword');
	
	location.href= "visualService.do?keyword=" + keywordInput.value;
}

function visualDraw(ctx,title,vtype,labels,datas,color) {
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

function onChange(e,type){
	let prevE = document.getElementsByClassName('sideItem active')[1];
	console.log(prevE);
	let typeView;
	let prevView;
	if(type === 'list'){
		prevView = document.getElementById('tmMakeList');
		typeView = document.getElementById('tmList');
	}
	else {
		prevView = document.getElementById('tmList');
		typeView = document.getElementById('tmMakeList');
		
	}
	prevE.className="sideItem"
	prevView.className = "contents";
	typeView.className = "contents active";
	e.className="sideItem active";
	
	prevE = e;
}