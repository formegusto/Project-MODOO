function onTm(iseq){
	let loading = document.getElementsByClassName('loading')[0];
	loading.className = "loading active";
	
	location.href="tm.do?iseq=" + iseq;
}

function sentiDraw(ctx, pos, neg, neu) {
	new Chart(ctx, {
    // The type of chart we want to create
    type: 'pie',

    // The data for our dataset
    data: {
        labels: ['긍정','부정','중립'],
        datasets: [{
            label: '감성분석 결과',
            backgroundColor: ['rgb(000,051,255)','rgb(255,051,051)','rgb(255,102,051)'],
            borderColor: 'rgb(0,0,0)',
            data: [pos,neg,neu]
        }]
    	},
	});
	
	console.log(ctx.style.width);
	console.log(ctx.style.height);
}

function save(ttype){
	if(ttype == 'wordcloud'){
		var loading = document.getElementsByClassName('loading')[0];
		loading.className = "loading active";
	} else {
		document.tmform.submit();
	}
}

function wordcloudSave(ans){
	var saveFrame = document.getElementsByClassName('saveFrame')[0];
	
	if(ans == 'yes'){
		saveFrame.value = "yes";
	} else {
		saveFrame.value = "no";
	}
	
	document.tmform.submit();
}