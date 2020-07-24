

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