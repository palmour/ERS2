document.addEventListener("DOMContentLoaded", init, false);

function init(){
    var displayForm = function(e){
        
        let formId;
        if(e.currentTarget.id === "login-btn"){
            formId = "login-form";
        }
        else{formId = "create-account-form";}
        
        let tabcontent = document.getElementsByClassName("tabcontent");
        let i;
        for(i=0; i<tabcontent.length; i++){
            tabcontent[i].style.display = "none";
        }

        tablinks = document.getElementsByClassName("tablinks");
        for(i=0; i<tablinks.length; i++){
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        document.getElementById(formId).style.display = "block";
        e.currentTarget.className += " active"; 
    };

    document.getElementById("login-btn").addEventListener("click", displayForm);
    document.getElementById("create-account-btn").addEventListener("click", displayForm);
} 
