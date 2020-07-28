function visualDraw(ctx,vtype,labels,datas,color) {
	console.log(vtype);
	
	new Chart(ctx, {
    // The type of chart we want to create
    type: vtype,

    // The data for our dataset
    data: {
        labels: labels,
        datasets: [{
            label: '저의 이름을 지어주세요!',
            backgroundColor: color,
            borderColor: 'rgb(0,0,0)',
            data: datas
        }]
    	},
	options: {
		legend: {
			maintainAspectRatio: false,
            display: false,
		}
		}
	});
	
	console.log(ctx.style.width);
	console.log(ctx.style.height);
}