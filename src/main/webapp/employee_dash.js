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
	
	let newRequestDiv = document.getElementById("new-request-div");
	let btn = document.getElementById("add-button");
	let closeButton = document.getElementsByClassName("close")[0];
	
	btn.onclick = function(){
		newRequestDiv.style.display = "block";
	}
	
	closeButton.onclick = function(){
		newRequestDiv.style.display = "none";
	}
	
	function readPhotoPath(input){
		if(input.files && input.files[0]){
			let fr = new FileReader();
			fr.onload = function(e){
				document.getElementById("submitted-photo").setAttribute("src", e.target.result);
			}
			
			fr.readAsDataURL(input.files[0]);
		}
	}
	
	$("#receipt-img").change(function(){
		readPhotoPath(this);
	});
}