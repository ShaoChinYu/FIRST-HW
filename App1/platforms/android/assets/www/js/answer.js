(function(){
	var btn = document.getElementById("ansbtn");
	btn.onclick = function(){
		var content = document.getElementById("answer-content").value;
		if(content.length !=0){

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
		    		self.location='question.html';
		    	
		      }
		    }
	   	    xmlhttp.open("POST",sessionStorage.serverurl+"Answer.action",true);
		    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded"); 
		    var timenow = new Date();
		    var time = timenow+"";
		    var para="owner="+localStorage.username+"&topic="+sessionStorage.rdpg+"&content="+content+"&time="+time;
		    xmlhttp.send(para);
		}else{
			alert("还没有输入回答呢~");}
		
	}
	
})();