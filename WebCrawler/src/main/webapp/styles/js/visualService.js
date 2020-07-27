function visualDraw(ctx,type) {
	new Chart(ctx, {
    // The type of chart we want to create
    type: type,

    // The data for our dataset
    data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
            label: 'My First dataset',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(0,0,0)',
            data: [0, 10, 5, 2, 20, 30, 45]
        }]
    },
	});
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