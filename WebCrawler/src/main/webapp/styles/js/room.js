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