window.onload = () => {
	tm = document.getElementById("tm");
	tm.style.display = "none";
	
	/*
	visual = document.getElementById("visual");
	visual.style.display = "none";
	*/
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