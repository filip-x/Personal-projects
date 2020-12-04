<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<title>Scoala Nationala de Gaz</title>
	<!-- ### General resources ### !-->
	<link href="stylesheets/nav-bar.css" rel="stylesheet" type="text/css">
	<link href="stylesheets/footer.css" rel="stylesheet" type="text/css">
	<link href="stylesheets/middle.css" rel="stylesheet" type="text/css">
	<script src="javascripts/index.js" type="text/javascript"></script>
	<!-- ### END ### !-->
</head>
<!-- ### Meniuri ### !-->

<body>
	<nav id = "menu1">
	    <a href="https://sngmedias.ro">
		<img src="imagini/sigla.png"/ height='80%' width='auto' style="top: 10%; position: relative; left: 20px; float: left;">
		<li id = "menuList">Liceul Tehnologic „Școala Națională de Gaz” Mediaș</li>
		</a>
		<ul id = "socialButtons" style="left: calc(-210px + 100%);">
			<li class = "social"><a href="https://facebook.com/sngmedias"><img class = "social" src="imagini/logo-fb.png"/></a></li>
			<li class = "social"><a href="https://www.edu.ro/"><img class = "social" src="imagini/logo-edu.png"/></a></li>
			<li class = "social"><a href="http://isjsb.ro/"><img class = "social" src="imagini/logo-isj.png"/></a></li>
			<li class = "social"><a href="https://ccdsibiu.ro/"><img class = "social" src="imagini/logo-CCD.jpg"/></a></li>
		</ul>
	</nav>
	<nav id = "menu2">
		<ul id = "menuLinks">
			<!--<li class = "navLink"><a class = "menu2Link" href="#a">Prezentarea Școlii</a><div class = "VisibleDropDown"><ul><li>a</li><li>b</li><li>c</li></ul></div></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Oferta Școalară</a><div class = "VisibleDropDown"><ul><li>d</li><li>e</li><li>f</li></ul></div></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Plan de Școlarizare</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Resurse Umane</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Resurse Materiale</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Orar</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Galerie Foto</a></li>-->
		</ul>
	</nav>
	<!-- ### END ### !-->
	<div id = "divInside" style="top:50px;">
		<div id="wrapper" style="top:50px;">
			<div id="middle">
				 
			</div>
		</div>
	</div>
</body>

<style>
h1 {
	font-size: 1.6em;
	color: rgb(180, 80, 112);
}
middle {
	overflow-x: hidden;
}
footer {
	top:0px;
}
@keyframes menu2LinkAnim {
	from {
		color: #FFFFFF;
	}
	to {
		color: #43D8FF;
	}
}

.dropDownList {
	color: #FFFFFF;
	font-style: italic;
	font-family: expletus-sans;
	font-weight: 400;
	text-decoration: none;
}
.navLink ul {
	list-style: none;
	padding-left: 0;
}
.navLink ul li {
	padding-top: 4px;
	padding-bottom: 4px;
	width: auto;
	height: 100%;
}
.navLink ul li a {
	width: auto;
	display: block;
	height: 100%;
}

.dropDownList:hover {
	animation: menu2LinkAnim 0.2s forwards;
}
</style>
<script>
	var menu = <?php echo file_get_contents("https://sngmedias.ro/menu.json") ?>;
	var MLs = document.getElementById("menuLinks");
	for(var key in menu) {
		if (menu.hasOwnProperty(key)) {
			navLink = document.createElement("li");
			navLink.className = "navLink";
			a = document.createElement("a");
			a.className = "menu2Link";
			a.innerHTML = key;
			var div = 0;
			if (menu[key].hasOwnProperty("dropdown")) {
				div = document.createElement("div");
				div.className = "VisibleDropDown";
				ul = document.createElement("ul");
				for(var subkey in menu[key].dropdown) {
					li = document.createElement("li");
					a1 = document.createElement("a");
					a1.innerHTML = subkey;
					a1.href = menu[key].dropdown[subkey].link;
					a1.className = "dropDownList"
					if (menu[key].dropdown[subkey].redirect == true)
						a1.className = a1.className + " redirect";
					li.appendChild(a1);
					ul.appendChild(li);
				}
				div.appendChild(ul);
				
				a.href = "javascript:void(0);";
			}
			else {
				a.href = menu[key].link;
				if(menu[key].redirect == true)
					a.className = a.className + " redirect";
			}
			navLink.appendChild(a);
			if (div != 0) navLink.appendChild(div);
			MLs.appendChild(navLink);
		}
	}
	var NavLinks = document.getElementById("menuLinks");
	var kids = NavLinks.children;
	var sum = 0;
	for(var i=0;i<kids.length;i++)
		sum = sum + kids[i].offsetWidth + 15;
	MLs.style.width = sum + "px";
	
	var tags;
	tags = document.getElementById("menuLinks").getElementsByTagName("a");
	for(var i=0; i<tags.length;i++) 
		if (tags[i].className.search("redirect") != -1) {
			console.log(tags[i].className);
			tags[i].href = "https://sngmedias.ro/desktop.php" + "?link=" + tags[i].href;
		}
	var element = document.getElementById('middle');
	var footer, remember;
	document.addEventListener("DOMContentLoaded", function() {footer = document.getElementsByTagName("footer")[0]; SetUp(); KeepFoot()}, false);
	
	function SetUp(){
		remember = [footer.style.left, footer.style.right];
	}
	function KeepFoot() {
		var height = element.clientHeight;
		console.log(height + " - " + window.innerHeight);
		if (height < window.innerHeight) {
			footer.style.position = "fixed";
			footer.style.bottom = "0";
			footer.style.left = "0";
			footer.style.right = "0";
			footer.style.top = "auto";
			document.getElementById("middle").style.height = "calc(-40px + 100%)";
			document.getElementById("middle").style.top = "auto";
			//document.getElementById("wrapper").style.height = "calc(-40px + 100%)";
			document.getElementById("divInside").style.height = "calc(-40px + 100%)";
		}
		else {
			//var footer = document.getElementsByTagName("footer")[0];
			footer.style.position = "relative";
			footer.style.bottom = "-20px";
			footer.style.left = remember[0];
			footer.style.right = remember[1];
			footer.style.top = "auto";
			document.getElementById("middle").style.height = "auto";
			document.getElementById("middle").style.top = "80%";
			//document.getElementById("wrapper").style.height = "100%";
			document.getElementById("divInside").style.height = "auto";
		}
		document.getElementById("socialButtons").style.top = "0";
	}
</script>
<footer>
	<div id="credits">
		© COPYRIGHT 2019 Liceul tehnologic "Şcoala Naţională de Gaz" Mediaş. Realizat de Ivan Adrian. Colaboratori: Comșa Filip.
	</div>
</footer>
</html>

