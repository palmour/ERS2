document.addEventListener("DOMContentLoaded", init, false);

function init(){
	
	alert("getSessionInfo fired");
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			let username = xhttp.responseText;
			alert(username);
			document.getElementById("username").innerHTML = username;
		}
	}
	
	xhttp.open("GET", "loadEmployeeDash", true);
	xhttp.send();
	

	document.getElementById("alerts").addEventListener("click", function(){
		alert("js works");
	});
}