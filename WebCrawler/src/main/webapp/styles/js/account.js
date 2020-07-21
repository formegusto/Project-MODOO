function onChangeInputType(li,type) {
	let inputTag;
	let noneTag;
	let noneLi;
	
	if(type === 'signin'){
		noneTag = document.getElementById('signupForm');
		inputTag = document.getElementById('signinForm');
		noneLi = document.getElementById('signupLi');
	} else {
		noneTag = document.getElementById('signinForm');
		inputTag = document.getElementById('signupForm');
		noneLi = document.getElementById('signinLi');
	}
	
	// display none
	noneTag.style.display = "none";
	
	// li animation
	noneLi.style.transform = "translateY(5px)";
	noneLi.style.transition = "1s";
	li.style.transform = "translateY(-5px)";
	li.style.transition = "1s";
	
	inputTag.style.display = "flex";
	inputTag.style.animation = "fadeIn 1.5s";
}