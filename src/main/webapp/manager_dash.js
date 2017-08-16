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
	
	let logout = function(e){
		xhttp = new XMLHttpRequest();
		
		xhttp.open("GET", "logout", true);
		xhttp.send();
	};
	
	document.getElementById("logout").addEventListener("click", logout);
	
	$.get("allReimburEver", function(responseJson) {          
        let $tablebody = $("#r-tablebody"); 
        $.each(responseJson, function(index, Reimbursement) {    
            $("<tr>").appendTo($tablebody) 
            		.append($("<td>").text(Reimbursement.U_ID_AUTHOR))
            		.append($("<td>").text(Reimbursement.R_SUBMITTED))
            		.append($("<td>").text(Reimbursement.R_AMOUNT))  
            		.append($("<td>").text(Reimbursement.RT_STATUS))
            		.append($("<td>").text(Reimbursement.RT_TYPE))
            		.append($("<td>").text(Reimbursement.U_ID_RESOLVER))
            		.append($("<td>").text(Reimbursement.R_SOLVED))
            		.append("<td><span class='badge deny-btn'>deny<span class='to-hide'>"+Reimbursement.R_ID+"</span></span></td>")
            		.append("<td><span class='badge approve-btn'>approve<span class='to-hide'>"+Reimbursement.R_ID+"</span></span></td>");
        });
    });
	
	$("#pending-btn").click(function(){
		let $tablebody = $("#r-tablebody");
		$tablebody.empty();
		
		$.get("newPenReimburse", function(responseJson){
			$.each(responseJson, function(index, Reimbursement){
				$("<tr>").appendTo($tablebody)     
				.append($("<td>").text(Reimbursement.U_ID_AUTHOR))
        		.append($("<td>").text(Reimbursement.R_SUBMITTED))
        		.append($("<td>").text(Reimbursement.R_AMOUNT))  
        		.append($("<td>").text(Reimbursement.RT_STATUS))
        		.append($("<td>").text(Reimbursement.RT_TYPE))
        		.append($("<td>").text(Reimbursement.U_ID_RESOLVER))
        		.append($("<td>").text(Reimbursement.R_SOLVED))
        		.append("<td><span class='badge deny-btn'>deny</span></td>")
        		.append("<td><span class='badge approve-btn'>approve</span></td>");
			});
		});
	});
	
	$("#resolved-btn").click(function(){
		let $tablebody = $("#r-tablebody");
		$tablebody.empty();
		
		$.get("resolvedReimburse", function(responseJson){
			$.each(responseJson, function(index, Reimbursement){
				$("<tr>").appendTo($tablebody)  
				.append($("<td>").text(Reimbursement.U_ID_AUTHOR))
        		.append($("<td>").text(Reimbursement.R_SUBMITTED))
        		.append($("<td>").text(Reimbursement.R_AMOUNT))  
        		.append($("<td>").text(Reimbursement.RT_STATUS))
        		.append($("<td>").text(Reimbursement.RT_TYPE))
        		.append($("<td>").text(Reimbursement.U_ID_RESOLVER))
        		.append($("<td>").text(Reimbursement.R_SOLVED))
        		.append("<td><span class='badge deny-btn'>deny</span></td>")
        		.append("<td><span class='badge approve-btn'>approve</span></td>");
			});
		});
	});
	
	$(document).on("click", ".deny-btn", function(e){
		
		let xhttp = new XMLHttpRequest();
		let rid = $(this).find("span").html();
		xhttp.open("POST", "denyReimbursement", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		
		xhttp.send(rid);
	});
	
	$(document).on("click", ".approve-btn", function(e){
		
		let xhttp = new XMLHttpRequest();
		let rid = $(this).find("span").html();
		xhttp.open("POST", "approveReimbursement", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		
		xhttp.send(rid);
	});
	
	
	
}