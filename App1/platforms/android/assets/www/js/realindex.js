(function () {
	document.getElementById("myownpg").onclick = function() {
		sessionStorage.rdps = localStorage.username;
	}
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	 xmlhttp.onreadystatechange=function(){
		 if (xmlhttp.readyState==4 && xmlhttp.status==200)
	      {
			 var res = xmlhttp.responseText;
			 var starnum = parseInt( res.charAt(2) );
			 var starlocation=new Array(starnum);  // location of *
			 var qnum = parseInt( res.charAt(0) ); //  number of questions
			 
			 //for all question
			 if( res.charAt(0) == '0' ){// no question
				 document.getElementById("questb").innerHTML='<P>还没有人问过问题呢!</p>';
			 }else{//all questions
				 var tmp2= 0;           //help to record the location of *
 	    		for(var i=0;i<res.length;i++){
	    			if('*' == res.charAt(i)){
	    				
	    				starlocation[tmp2] = i;
	    				tmp2 = tmp2 + 1;
	    			}
	    		}
 	    		var qname = "";
 	    		var totalname="";
 	    		var st = 1;  //start location for one question name of *
 	    		var ed = 2;  //end location of for one question name of *
 	    		for(var j=0;j<qnum;j++){
 	    			for(var i=starlocation[st]+1; i<starlocation[ed] ;i++){
 	    				qname = qname+""+res.charAt(i);
 	    				
 	    			}
 	    			st = st + 1; 
 	    			ed = ed + 1;  
 	    			totalname = totalname +"<p><a href=\"question.html\" class=\"link\"><h1>"+qname+"</h1></a></p>";
 	    			qname="";
			 }
				 document.getElementById("questb").innerHTML= totalname;  //update ownpg.html
				 
					var obj_lis = document.getElementById("questb").getElementsByTagName("a");
					for(i=0;i<obj_lis.length;i++){
						obj_lis[i].onclick = function(){
							sessionStorage.rdpg=this.innerHTML;
						}
					}
					
			 }
			 	 
			 
	      }
	 }
	 
	 xmlhttp.open("POST",sessionStorage.serverurl+"GetAllQue.action",true);
	 xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 xmlhttp.send();
	
	
})();