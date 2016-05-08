(function() {
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
			 var starnum = parseInt( res.charAt(4) );
			 var starlocation=new Array(starnum);  // location of *
			 var qnum = parseInt( res.charAt(0) ); //  number of questions
			 
			 //for myquestion
			 if( res.charAt(0) == '0' ){//the login user has no question
				 document.getElementById("quetb").innerHTML='<P>你还没有问过问题呢!</p>';
			 }else{//login user has questions
				 var tmp2= 0;           //help to record the location of *
 	    		for(var i=0;i<res.length;i++){
	    			if('*' == res.charAt(i)){
	    				
	    				starlocation[tmp2] = i;
	    				tmp2 = tmp2 + 1;
	    			}
	    		}
 	    		var qname = "";
 	    		var totalname="";
 	    		var st = 2;  //start location for one question name of *
 	    		var ed = 3;  //end location of for one question name of *
 	    		for(var j=0;j<qnum;j++){
 	    			for(var i=starlocation[st]+1; i<starlocation[ed] ;i++){
 	    				qname = qname+""+res.charAt(i);
 	    				
 	    			}
 	    			st = st + 1; 
 	    			ed = ed + 1;  
 	    			totalname = totalname +"<p><a href=\"question.html\" class=\"link\">"+qname+"</a></p>";
 	    			qname="";
			 }
				 document.getElementById("quetb").innerHTML= totalname;  //update ownpg.html
					var obj_lis = document.getElementById("quetb").getElementsByTagName("a");
					for(var i=0;i<obj_lis.length;i++){
						obj_lis[i].onclick = function(){
							sessionStorage.rdpg=this.innerHTML;
						}
					}
				 
			 }
			 
			 //for my answer
			 
			 if( res.charAt(2) == '0' )  //the login user has no answer
				 document.getElementById("anstb").innerHTML='<P>你还没有回答过问题呢!</p>';
			 }else{//login user has answers
				var anum = parseInt( res.charAt(2) ); //  number of questions
 	    		var total="";
 	    		var st = 2+qnum;  //start location for one question name of *
 	    		var ed = st+1;  //end location of for one question name of *
 	    		var tmpstr="";
 	    		for(var j=0; j<anum;j++){
 	    			for(var k=0; k<3;k++){
 	    				for(var i = starlocation[st]+1; i<starlocation[ed];i++){
 	    					tmpstr = tmpstr + res.charAt(i);
 	    				}
 	    				if( k == 0 ) {
 	    					total = total + "<div class=\"ans-wrapper\"><p><a href=\"question.html\" class=\"link emphasis\">"+ tmpstr +"</a></p>"
 	    				}
 	    				if( k == 1) {
 	    					total = total + "<div class=\"answer\">"+ tmpstr +"</div>"
 	    				}
 	    				if( k == 2) {
 	    					total = total +   "<p class=\"meta\">"+ tmpstr +"</p></div>"
 	    				}
 	    				st = st + 1;
 	    				ed = ed + 1;
 	    				tmpstr = "";
 	    			}
 	    		}
				 document.getElementById("anstb").innerHTML= total;  //update ownpg.html
					var obj_lis2 = document.getElementById("anstb").getElementsByTagName("a");
					for(var i=0;i<obj_lis2.length;i++){
						obj_lis2[i].onclick = function(){
							sessionStorage.rdpg=this.innerHTML;
						}
					}
			 }
			 
	      }
	 }
	 xmlhttp.open("POST",sessionStorage.serverurl+"GetQA.action",true);
	 xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 var para = "username="+sessionStorage.rdps;
	 xmlhttp.send(para);

})();