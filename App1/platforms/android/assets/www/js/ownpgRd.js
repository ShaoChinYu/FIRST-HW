(function() {
	var userdata = localStorage.userdata;
	var username = localStorage.username;
	var mail = localStorage.mail;
	document.getElementById("myownpg").onclick = function() {
		sessionStorage.rdps = localStorage.username;
	}
	if(!sessionStorage.rdps){sessionStorage.rdps = localStorage.username;}
	document.getElementById("username").innerHTML= sessionStorage.rdps +" 的主页";

	
})();