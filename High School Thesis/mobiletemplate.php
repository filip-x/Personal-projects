<!doctype html>
<html>

<head>
	<meta charset="utf-8">
	<title>Scoala Nationala de Gaz</title>
	<!-- ### General resources ### !-->
	<link href="stylesheets/mobile.css" rel="stylesheet" type="text/css">
	<script src="javascripts/index.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- ### END ### !-->
</head>
<nav id = "menu1">
		<img src="imagini/menu-white.png"/ id="menuButton" height='50%' width='auto' style="top: 25%; position: relative; left: 40px; float: left;" onclick="openNav()">
		<a href="https://sngmedias.ro">
		<!--img src="imagini/sigla.png"/ height='80%' width='auto' style="top: 10%; position: relative; left: 100px; float: left;"-->
		<li id = "menuList">Liceul Tehnologic „Școala Națională de Gaz” Mediaș</li>
		</a>
	</nav>
	<nav id = "menu2">
		<ul id = "menuLinks">
			<!--<li class = "navLink"><a class = "menu2Link" href="javascript:void(0);">Prezentarea Școlii</a><ul><li>a</li><li>b</li><li>c</li><li>x</li></ul></li>
			<li class = "navLink"><a class = "menu2Link" href="javascript:void(0);">Oferta Școalară</a><ul><li>d</li><li>e</li><li>f</li></ul></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Plan de Școlarizare</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Resurse Umane</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Resurse Materiale</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Orar</a></li>
			<li class = "navLink"><a class = "menu2Link" href="#a">Galerie Foto</a></li>-->
		</ul>
		<ul id = "socialButtons">
			<li class = "social"><a href="https://facebook.com/sngmedias"><img class = "social" src="imagini/logo-fb.png"/></a></li>
			<li class = "social"><a href="https://www.edu.ro/"><img class = "social" src="imagini/logo-edu.png"/></a></li>
			<li class = "social"><a href="http://isjsb.ro/"><img class = "social" src="imagini/logo-isj.png"/></a></li>
			<li class = "social"><a href="https://ccdsibiu.ro/"><img class = "social" src="imagini/logo-CCD.jpg"/></a></li>
		</ul>
	</nav>
<!-- ### END ### !-->


<body>
	<div id = "divInside" style="top:40px; position: relative;">
		<div id="wrapper">
			<div id="middle">
				
			</div>
		</div>
	</div>
</body>

<footer style="top:0px;">
	<div id="credits">
		© COPYRIGHT 2019 Liceul tehnologic "Şcoala Naţională de Gaz" Mediaş. Realizat de Ivan Adrian. Colaboratori: Comșa Filip.
	</div>
</footer>
<style>
p {
	font-size: 40px;
	padding-top: 20px;
}
h1 {
	font-size: 1.6em;
	color: rgb(180, 80, 112);
}
h2 {
	font-size: 1.5em;
}
h3 {
	font-size: 1.4em;
}
h4 {
	font-size: 1.3em;
}
.navLink ul {
	list-style: none;
}
.navLink {
	line-height: 1;
}

#socialButtons {
	line-height: 1;
	margin: 0;
	padding-left: 0px;
}
#middle {
	overflow-x: scroll;
}
.dropDownList {
	color: #FFFFFF;
	font-style: italic;
	font-family: expletus-sans;
	font-weight: 400;
	text-decoration: none;
	display: block;
	height: 100%;
}
.navLink li {
	padding-top: 0.25em;
	padding-bottom: 0.25em;
	height: 2em;
}
.menu2Link {
	padding: 20px 8px 35px 32px;
}

.navLink ul {
	padding-left: 0;
}

.navLink ul li a {
	font-size: 40px;
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
			var ul = 0;
			if (menu[key].hasOwnProperty("dropdown")) {
				console.log('DROPDOWN');
				ul = document.createElement("ul");
				for(var subkey in menu[key].dropdown) {
					li = document.createElement("li");
					a1 = document.createElement("a");
					a1.innerHTML = subkey;
					a1.href = menu[key].dropdown[subkey].link;
					a1.className = "dropDownList";
					if (menu[key].dropdown[subkey].redirect == true)
						a1.className = a1.className + " redirect";
					li.appendChild(a1);
					ul.appendChild(li);
				}
				
				a.href = "javascript:void(0);";
			}
			else {
				a.href = menu[key].link;
				if(menu[key].redirect == true)
					a.className = a.className + " redirect";
			}
			navLink.appendChild(a);
			if (ul != 0) navLink.appendChild(ul);
			MLs.appendChild(navLink);
		}
	}

	var tags;
	tags = document.getElementById("menuLinks").getElementsByTagName("a");
	for(var i=0; i<tags.length;i++) 
		if (tags[i].className.search("redirect") != -1) {
			console.log(tags[i].className);
			tags[i].href = "https://sngmedias.ro/mobile.php" + "?link=" + tags[i].href;
		}

	document.addEventListener("DOMContentLoaded", function() {KeepFoot()}, false);
	var element = document.getElementById('middle');
	var height = element.clientHeight;

	function KeepFoot() {
		console.log(height);
		console.log(screen.height);
		if (height < screen.height) {
			var footer = document.getElementsByTagName("footer")[0];
			footer.style.position = "fixed";
			footer.style.bottom = "0";
			footer.style.left = "0";
			footer.style.right = "0";
			footer.style.top = "auto";
			document.getElementById("middle").style.height = "calc(-40px + 100%)";
			document.getElementById("middle").style.top = "auto";
			document.getElementById("wrapper").style.height = "100%";
			document.getElementById("divInside").style.height = "calc(-40px + 100%)";
		}
		document.getElementById("menuLinks").style.margin = "0";
		document.getElementById("menuLinks").style.padding = "0";
		//document.getElementById("socialButtons").style.top = "0";
	}
	</script>
	<script>

	var MenuLinks = document.getElementsByClassName("navLink");
	for (var i=0; i < MenuLinks.length; i++) {
		if (MenuLinks[i].getElementsByTagName("ul")[0] != null) {
			MenuLinks[i].getElementsByTagName("ul")[0].style.height = "0em";
			//MenuLinks[i].getElementsByTagName("ul")[0].style.display = "none";
		}
			
	}
	$(".menu2Link").click(function() {
		var justClose = false;
		var n;
		if (this.nextSibling) {
			n = this.nextSibling.getElementsByTagName("li").length;
			//if (this.nextSibling.style.display == "initial") justClose = true;
			if(this.nextSibling.style.height == 2*n + "em") justClose = true;
		}
		for (var i=0; i < MenuLinks.length; i++)
			if ((MenuLinks[i] != this || justClose == true) && MenuLinks[i].getElementsByTagName("ul")[0] != null) {
				MenuLinks[i].getElementsByTagName("ul")[0].style.height = "0em";
				//MenuLinks[i].getElementsByTagName("ul")[0].style.display = "none";
			}
				
		if (this.nextSibling != null && justClose == false) {
			//this.nextSibling.style.display = "initial";
			this.nextSibling.style.height = 2*n + "em";
		}
			
	});

	function openNav() {
		document.getElementById("menu2").style.width = "60%";
		document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
		document.getElementById("menuButton").onclick = closeNav;
	}

	function closeNav() {
		document.getElementById("menu2").style.width = "0";
		document.body.style.backgroundColor = "rgba(0,0,0,0)";
		document.getElementById("menuButton").onclick = openNav;
	}
	var startX, startY, dist,
		threshold = 150, //required min distance traveled to be considered swipe
		allowedTime = 200, // maximum time allowed to travel that distance
		elapsedTime,
		startTime;
		
	function handleswipeR(isrightswipe){
		if (isrightswipe)
			openNav();
	}
	function handleswipeL(isleftswipe){
		if (isleftswipe)
			closeNav();
	}

	window.addEventListener('touchstart', function(e){
		var touchobj = e.changedTouches[0]
		dist = 0
		startX = touchobj.pageX
		startY = touchobj.pageY
		startTime = new Date().getTime() // record time when finger first makes contact with surface
		//e.preventDefault()
	}, false)

	window.addEventListener('touchmove', function(e){
		//e.preventDefault() // prevent scrolling when inside DIV
	}, false)

	window.addEventListener('touchend', function(e){
		var touchobj = e.changedTouches[0]
		dist = touchobj.pageX - startX // get total dist traveled by finger while in contact with surface
		elapsedTime = new Date().getTime() - startTime // get time elapsed
		// check that elapsed time is within specified, horizontal dist traveled >= threshold, and vertical dist traveled <= 100
		var swiperightBol = (elapsedTime <= allowedTime && dist >= threshold && Math.abs(touchobj.pageY - startY) <= 100)
		var swipeleftBol = (elapsedTime <= allowedTime && -dist >= threshold && Math.abs(touchobj.pageY - startY) <= 100)
		handleswipeR(swiperightBol)
		handleswipeL(swipeleftBol)
		//e.preventDefault()
	}, false)
	var Links = document.getElementsByTagName('a');
	for(var i=0;i<Links.length;i++) {
	    if(Links[i].href.search(".pdf") != -1)
	        Links[i].href = "https://docs.google.com/viewerng/viewer?url=" + Links[i].href;
	}
</script>
</html>

