
    

(function() {
    var signup = document.getElementById('form-signup'),
        signin = document.getElementById('form-signin');

    
    switchForms();
    checkSignup();
   checkSignin();

    // switch forms
    function switchForms() {
        var label = document.getElementById('form-label');
        label.addEventListener('click', function(e) {
            var signupCls = signup.classList,
                signinCls = signin.classList;
            if (signupCls.contains('hidden')) {
                signupCls.remove('hidden');
                signinCls.add('hidden'); 
                label.innerHTML = '登陆';
            } else {
                signinCls.remove('hidden');
                signupCls.add('hidden');
                label.innerHTML = '注册';
            }
        }, false);
    }

    // check signup form
    function checkSignup() {
        var nameIpt = signup['username'],
            emailIpt = signup['email'],
            pwdIpt = signup['password'],
            errArea = document.getElementById('signup-error'),
            submitBtn = document.getElementById('signup-submit');

        submitBtn.addEventListener('click', function(e) {
            var err = [];

            var name = nameIpt.value.trim();
            if (!name) {
                err.push('用户名不得为空');
            } else if (name.length < 2 || name.length > 16) {
                err.push('用户名长度为2～16字符');
            }

            var email = emailIpt.value.trim();
            if (!email) {
                err.push('邮箱地址不得为空');
            } else if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email)) {
                err.push('请输入有效的邮箱地址');
            }

            var pwd = pwdIpt.value;
            if (!pwd) {
                err.push('密码不得为空');
            } else if (pwd.length < 6 || pwd.length > 16) {
                err.push('密码长度为6～16字符');
            } else if (/^[0-9]*$/.test(pwd)) {
                err.push('密码不得为纯数字');
            }

            if (err.length) {
                errArea.innerHTML = err.join('<br>');
            } else {
            	
         	    var xmlhttp1;
        	    if (window.XMLHttpRequest)
        	    {// code for IE7+, Firefox, Chrome, Opera, Safari
        	    xmlhttp1=new XMLHttpRequest();
        	    }
        	  else
        	    {// code for IE6, IE5
        	    xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
        	    }
        	    xmlhttp1.onreadystatechange=function()
        	    {
        	    if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
        	      {
        	    	var res = xmlhttp1.responseText;
        	    	if( res.charAt(0) == 'O'){
        	    		localStorage.userdata = res;
        	    		var tmp=new Array(2);
        	    		var tmp2 = 0;
        	    		for(var i=0;i<res.length;i++){
        	    			if('*' == res.charAt(i)){
        	    				
        	    				tmp[tmp2] = i;
        	    				tmp2 = tmp2 + 1;
        	    			}
        	    		}
        	    		var username= "";
        	    		for(var i= tmp[0]+1;i<tmp[1];i++){
        	    			username = username + res.charAt(i);
        	    		}
        	    		var mail= "";
        	    		for(var i= tmp[1]+1;i<res.length;i++){
        	    			mail = mail + res.charAt(i);
        	    		}
        	    		localStorage.username = username;
        	    		localStorage.mail = mail;
        	    		
        	    		self.location='ownpg.html';
        	    	}else{
        	    		alert("该邮箱已存在");
        	    		self.location='login.html';
        	    	}
        	      }
        	    }
             	
        	    xmlhttp1.open("POST",sessionStorage.serverurl+"Zhuce.action",false);
        	    xmlhttp1.setRequestHeader("Content-type","application/x-www-form-urlencoded");          
        	    var para="username="+name+"&password="+pwd+"&email="+email;
        	    xmlhttp1.send(para);
            	
            	
            	
                //signup.submit();
            }
        }, false);
    }

    // check signin form
    function checkSignin() {
        var emailIpt = signin['email'],
            pwdIpt = signin['password'],
            errArea = document.getElementById('signin-error'),
            submitBtn = document.getElementById('signin-submit');

        submitBtn.addEventListener('click', function(e) {
            var err = [];

            var email = emailIpt.value.trim();
            var email = emailIpt.value.trim();
            if (!email) {
                err.push('邮箱地址不得为空');
            } else if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email)) {
                err.push('请输入有效的邮箱地址');
            }

            var pwd = pwdIpt.value;
            if (!pwd) {
                err.push('密码不得为空');
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
            	    	var res = xmlhttp.responseText;
            	    	if( res.charAt(0) == 'O'){
            	    		localStorage.userdata = res;
            	    		var tmp=new Array(2);
            	    		var tmp2 = 0;
            	    		for(var i=0;i<res.length;i++){
            	    			if('*' == res.charAt(i)){
            	    				
            	    				tmp[tmp2] = i;
            	    				tmp2 = tmp2 + 1;
            	    			}
            	    		}
            	    		var username= "";
            	    		for(var i= tmp[0]+1;i<tmp[1];i++){
            	    			username = username + res.charAt(i);
            	    		}
            	    		var mail= "";
            	    		for(var i= tmp[1]+1;i<res.length;i++){
            	    			mail = mail + res.charAt(i);
            	    		}
            	    		localStorage.username = username;
            	    		localStorage.mail = mail;
            	    		
            	    		self.location='ownpg.html';
            	    	}else{
            	    		alert("用户名/密码 错误");
            	    		self.location='login.html';
            	    	}
            	      }
            	    }
                 	
            	    xmlhttp.open("POST",sessionStorage.serverurl+"Login.action",false);
            	    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");          
            	    var para="password="+pwd+"&email="+email;
            	    xmlhttp.send(para);
            	   
            	    
            	// signin.submit();
            	 
            }
        }, false);
    }
    

    
})();
