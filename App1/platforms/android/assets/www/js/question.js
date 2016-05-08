(function() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var res = xmlhttp.responseText;
			var starnum = parseInt(res.charAt(2));
			var starlocation = new Array(starnum); // location of *
			var qnum = 1; // number of questions
			var tmp2 = 0; // help to record the location of *
			for (var i = 0; i < res.length; i++) {
				if ('*' == res.charAt(i)) {
					starlocation[tmp2] = i;
					tmp2 = tmp2 + 1;
				}
			}
			var qname = "";
			var qowner = "";
			var qdate = "";
			var totalname = "";
			var st = 1; // start location for one question name of *
			var ed = 2; // end location of for one question name of *
			for (var i = starlocation[st] + 1; i < starlocation[ed]; i++) {
				qname = qname + "" + res.charAt(i);
			}
			st = st + 1;
			ed = ed + 1;
			for (var i = starlocation[st] + 1; i < starlocation[ed]; i++) {
				qowner = qowner + "" + res.charAt(i);
			}
			st = st + 1;
			ed = ed + 1;
			for (var i = starlocation[st] + 1; i < starlocation[ed]; i++) {
				qdate = qdate + "" + res.charAt(i);
			}
			st = st + 1;
			ed = ed + 1;
			totalname = totalname + "<h3>" + qname + "</h3>"
					+ "<p class=\"meta\"><a id=\"qowner\" href=\"ownpg.html\" class=\"link\">"
					+ qowner + "</a> 提问于 <span>" + qdate + "</span></p>";
			document.getElementById("question").innerHTML = totalname; // update
																		// ownpg.html
		
			document.getElementById("qowner").onclick = function() {
				sessionStorage.rdps = this.innerHTML;
			}
			document.getElementById("myownpg").onclick = function() {
				sessionStorage.rdps = localStorage.username;
			}
			
			// for my answer

			if (res.charAt(0) == '0') {// the login user has no answer
				document.getElementById("answerupdate").innerHTML = '<P>还没有人回答过这个问题呢!</p>';
			} else {// login user has answers
				var anum = parseInt(res.charAt(0)); // number of questions
				var total = "";
				var tmpstr = "";
				for (var j = 0; j < anum; j++) {
					for (var k = 0; k < 3; k++) {
						for (var i = starlocation[st] + 1; i < starlocation[ed]; i++) {
							tmpstr = tmpstr + res.charAt(i);
						}
						if (k == 0) {
							total = total
									+ "<div class=\"answer-wrapper\"><div class=\"user clearfix\"><p>Know友<a href=\"ownpg.html\" class=\"name\">"
									+ tmpstr + "</a>说:</p></div>";
						}
						if (k == 1) {
							total = total + "<div class=\"answer\"><p>"
									+ tmpstr + "</p></div>";
						}
						if (k == 2) {
							total = total
									+ "<div class=\"meta\"><span class=\"time\">"
									+ tmpstr + "</span></div></div>";
						}
						st = st + 1;
						ed = ed + 1;
						tmpstr = "";
					}
				}
				document.getElementById("answerupdate").innerHTML = total; // update
																			// ownpg.html
				var obj_lis2 = document.getElementById("answerupdate").getElementsByTagName("a");
				for (var  i = 0; i < obj_lis2.length; i++) {
					obj_lis2[i].onclick = function() {
						sessionStorage.rdps = this.innerHTML;
					}
				}
			}

		}
	}
	xmlhttp.open("POST", sessionStorage.serverurl + "ReadQue.action", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var para = "topic=" + sessionStorage.rdpg;
	xmlhttp.send(para);

})();