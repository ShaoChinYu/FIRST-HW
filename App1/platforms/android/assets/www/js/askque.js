(function() {
    var newpwd = document.getElementById('askque');
     
	document.getElementById("myownpg").onclick = function() {
		sessionStorage.rdps = localStorage.username;
	}
 
    checkSigpwd();
 



    // check pwd
    function checkSigpwd() {
        var 
            pwdIpt = newpwd['topic'],
            pwdIpt1 = newpwd['content'],
            errArea = document.getElementById('newpwd-error'),
            submitBtn = document.getElementById('newpwd-sub');

        submitBtn.addEventListener('click', function(e) {
            var err = [];
            var pwd = pwdIpt.value;
            var pwd1 =pwdIpt1.value;
            if (!pwd) {
                err.push('标题不得为空');
            } 
            if (!pwd1){
            	err.push('描述不得为空');
            }

            if (err.length) {
                errArea.innerHTML = err.join('<br>');
            } else {
            	

        	    var xmlhttp;
        	    if (window.XMLHttpRequest)
        	    {// code for IE7+, Firefox, Chrome, Opera, Safari
        	    xmlhttp=new XMLHttpRequest();
        	    }
        	  else
        	    {// code for IE6, IE5
        	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        	    }
        	    xmlhttp.onreadystatechange=function()
        	    {
        	    if (xmlhttp.readyState==4 && xmlhttp.status==200)
        	      {
        	    
        	    		self.location='ownpg.html';
        	    	
        	      }
        	    }
             	
        	    xmlhttp.open("POST",sessionStorage.serverurl+"AskQue.action",true);
        	    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
        	    var timenow = new Date();
        	    var time = timenow+"";
        	    var para="username="+localStorage.username+"&topic="+pwd+"&content="+pwd1+"&time="+time;
        	    xmlhttp.send(para);
            	
            	// $("input[type='button']").attr("type","submit");
            	
            	
            	
            	
            	
            }
        }, false);
    }



})();
