document.addEventListener("DOMContentLoaded", init, false);

function practiceClick(){
	practice1.innerHTML="this text will be added to div";
}

function makeEmpTable() {        
    $.get("loadImps", function(responseJson) {          
        var $table = $("<table>").appendTo($("#practice1")); 
        $.each(responseJson, function(index, Employee) {    
            $("<tr>").appendTo($table)                     
            		.append($("<td>").text(Employee.U_USERNAME)) 
            		.append($("<td>").text(Employee.U_PASSWORD))      
            		.append($("<td>").text(Employee.U_FIRSTNAME))    
            	  	.append($("<td>").text(Employee.U_LASTNAME))
            	  	.append($("<td>").text(Employee.U_EMAIL))
            	  	.append($("<td>").text(Employee.UR_ID));
        });
    });
}

function getEmployees(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}
	
	xhttp.open("GET", "loadImps", true);
	xhttp.send()
	
}

function getUserReimburse2(){
	 $.get("userReimburse", function(responseJson) {          
	        var $table = $("<table>").appendTo($("#practice2")); 
	        $.each(responseJson, function(index, Reimbursement) {    
	            $("<tr>").appendTo($table)                     
	            		.append($("<td>").text(Reimbursement.R_ID))
	            		.append($("<td>").text(Reimbursement.R_AMOUNT))  
	            		.append($("<td>").text(Reimbursement.R_Description))
	            		.append($("<td>").text(Reimbursement.R_SUBMITTED))
	            		.append($("<td>")).text(Reimbursement.R_SOLVED)
	            		.append($("<td>").text(Reimbursement.U_ID_AUTHOR))
	            		.append($("<td>").text(Reimbursement.U_ID_RESOLVER))
	            		.append($("<td>").text(Reimbursement.RT_TYPE))
	            		.append($("<td>").text(Reimbursement.RT_STATUS));
	        });
	    });
}


function getUserReimburse(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}
	
	xhttp.open("GET", "userReimburse", true);
	xhttp.send()
}

function allnewPenReimburse(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}

	
	xhttp.open("GET", "newPenReimburse", true);
	xhttp.send()
}

function allresReimburse(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}

	
	xhttp.open("GET", "resolvedReimburse", true);
	xhttp.send()
	
}

function UserunresReimbur(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}

	
	xhttp.open("GET", "userUnResReimbur", true);
	xhttp.send()
}

function UserResReimbur(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}

	
	xhttp.open("GET", "userResReimbur", true);
	xhttp.send()
}

function userInfo(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}

	
	xhttp.open("GET", "userInfo", true);
	xhttp.send()
	
}

function editInfo(){
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		if(xhttp.readyState === 4 && xhttp.status === 200){
			console.log(this.readyState)
			alert(xhttp.responseText);
		}
		
	}

	
	xhttp.open("GET", "editInfo", true);
	xhttp.send()
}