document.addEventListener("DOMContentLoaded", init, false);

function init(){
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			let username = xhttp.responseText;
			document.getElementById("username").innerHTML = username;
		}
	}
	
	xhttp.open("GET", "loadEmployeeDash", true);
	xhttp.send();
	

	document.getElementById("alerts").addEventListener("click", function(){
		alert("js works");
	});
	
	let logout = function(e){
		xhttp = new XMLHttpRequest();
		
		xhttp.open("GET", "logout", true);
		xhttp.send();
	};
	
	document.getElementById("logout").addEventListener("click", logout);
}