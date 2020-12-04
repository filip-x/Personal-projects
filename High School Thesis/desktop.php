<?php
	ini_set('display_errors', 1);
	ini_set('display_startup_errors', 1);
	error_reporting(E_ALL);
	if (array_key_exists('link',$_GET)) {
		$lnk = $_GET['link'];
		$html = file_get_contents("https://sngmedias.ro/template.php");
		$pos1 = strpos($html, "<div id=\"middle\">");
		$pos2 = $pos1 + strlen("<div id=\"middle\">");
		$f1 = substr($html, 0, $pos2);
		$f2 = substr($html, $pos2);
		$html2 = file_get_contents($lnk);
		libxml_use_internal_errors(true);
		echo($f1 . "\n" . $html2 . $f2);
		exit();
	}
?>

<!doctype html>
<html>

<head>
	<!--link rel="icon" href="imagini/sigla.png"-->
	<meta charset="utf-8">
	<title>Scoala Nationala de Gaz</title>
	<!-- ### General resources ### !-->
	<link href="stylesheets/nav-bar.css" rel="stylesheet" type="text/css">
	<link href="stylesheets/footer.css" rel="stylesheet" type="text/css">
	<link href="stylesheets/middle.css" rel="stylesheet" type="text/css">
	<link href="stylesheets/tooltip.css" rel="stylesheet" type="text/css">
	<script src="javascripts/index.js" type="text/javascript"></script>
	<!-- ### END ### !-->
</head>
<!-- ### Meniuri ### !-->
<nav id = "menu1">
    <a href="https://sngmedias.ro">
	<img src="imagini/sigla.png" height='80%' width='auto' style="top: 10%; position: relative; left: 20px; float: left;">
	<li id = "menuList">Liceul Tehnologic „Școala Națională de Gaz” Mediaș</li>
	</a>
	<ul id = "socialButtons">
		<li class = "social"><a class="tooltip" href="https://facebook.com/sngmedias"><img class="social" src="imagini/logo-fb.png"/><!--span class="tooltiptext">fb.com/sngmedias</span--></a></li>
		<li class = "social"><a href="https://www.edu.ro/"><img class = "social" src="imagini/logo-edu.png"/></a></li>
		<li class = "social"><a href="https://isjsb.ro/"><img class = "social" src="imagini/logo-isj.png"/></a></li>
		<li class = "social"><a href="https://ccdsibiu.ro/"><img class = "social" src="imagini/logo-CCD.jpg"/></a></li>
	</ul>
</nav>
<nav id = "menu2">
	<ul id = "menuLinks">
		<!--<li class = "navLink"><a class = "menu2Link" href="#a">Prezentarea Școlii</a><div class = "VisibleDropDown"><ul><li>a</li><li>b</li><li>c</li></ul></div></li>
		<li class = "navLink"><a class = "menu2Link" href="#a">Oferta Școalară</a><div class = "VisibleDropDown"><ul><li><a class = "redirect" href="http://testare.sngmedias.ro/pages/oferta-scolara/2018-2019.html">2018-2019</a></li><li>e</li><li>f</li></ul></div></li>
		<li class = "navLink"><a class = "menu2Link" href="#a">Plan de Școlarizare</a></li>
		<li class = "navLink"><a class = "menu2Link redirect" href="http://testare.sngmedias.ro/pages/resurse-umane.html">Resurse Umane</a></li>
		<li class = "navLink"><a class = "menu2Link" href="#a">Resurse Materiale</a></li>
		<li class = "navLink"><a class = "menu2Link" href="#a">Orar</a></li>
		<li class = "navLink"><a class = "menu2Link" href="#a">Galerie Foto</a></li>-->
	</ul>
</nav>
<!-- ### END ### !-->
<style>
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
<!-- ### Slideshow ### !-->
<div id = "container">
	<div id = "slideshow">
		<!-- ### Slideshow resources ### !-->
		<!--link href="https://fonts.googleapis.com/css?family=Reem+Kufi" rel="stylesheet"-->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
		<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'>
		<link rel="stylesheet" href="stylesheets/slideshow.css">
		
		<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
		<script src="javascripts/slideshow.js"></script>
		<!-- ### END ### !-->
		<ul id="slides"></ul>
		<ul id="slide-select">
			<li class="btn prev fa fa-arrow-left"></li>
		</ul>
		<?php
			ini_set('display_errors', 'On');
			// Slideshow Setup
			$dir = "slideshow-images";
			$files = scandir($dir);
			$filex = json_encode($files);
			// ---
			// Noutati Setup
			$noutati = array();
			$noutateExp = array();
			$handle = fopen("Resources/noutati.txt", "r");
			if ($handle) {
				while (($line = fgets($handle)) !== false) {
					$aux = array();
					$line = substr($line, 0, strlen($line) - 2);
					if (substr($line, 0, 1) == '$') {
						$aux["link"] = substr($line, 1);
						$line = fgets($handle);
						$aux["desc"] = false;
						$aux["title"] = $line;
					}
					else {
						$aux["link"] = $line;
						$aux["desc"] = file_get_contents($line . '?GetDesc=true');
						$aux["title"] = file_get_contents($line . '?GetTitle=true', true);
					}
					array_push($noutateExp, $aux);
				}
				fclose($handle);
			} else {
				// error opening the file.
			} 
			$noutati = json_encode($noutateExp);
		?>
		
		<script> // Slideshow
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
					tags[i].href = window.location.href + "?link=" + tags[i].href;
				}
	
			var files = <?php echo $filex ?>;
			var n = files.splice(0,2);
			var aux, index;
			for (i=0; i< files.length; i++)
			{	index = files[i].substr(0, 1);
				if("0123456789".search(index) == -1) continue;
				index = Number(index);
				if (index != i)
				{
					aux = files[i];
					files[i] = files[index-1];
					files[index-1] = aux;
					
				}
			}
			for (i=0; i < files.length; i++)
			{
				li.style.top = 0;
				
				var newLi = document.createElement("li");
				var li = document.createElement("li");
				li.className = "slide";
				newLi.className = "selector";
				
				var divLeft = document.createElement("div");
				divLeft.className = "slide-partial slide-left";
				
				var divRight = document.createElement("div");
				divRight.className = "slide-partial slide-right";
				
				var divImage1 = document.createElement("div");
				divImage1.className = "sImg";
				divImage1.style.background = "url('https://sngmedias.ro/slideshow-images/" + files[i] +"') 0 0 no-repeat";
				divImage1.style.position = 'relative';
				divImage1.style.height = '100%';
				divImage1.style.width = '100%';
				divImage1.style.border = 0;
				divImage1.style.backgroundSize = "200% 100%";
				
				var divImage2 = document.createElement("div");
				divImage2.className = "sImg";
				divImage2.style.background = "url('https://sngmedias.ro/slideshow-images/" + files[i] + "') 100% 0 no-repeat";
				divImage2.style.position = 'relative';
				divImage2.style.height = '100%';
				divImage2.style.width = '100%';
				divImage2.style.border = 0;
				divImage2.style.backgroundSize = "200% 100%";
				var canvas = document.createElement("canvas");
				var context = canvas.getContext("2d");
				
				var h2 = document.createElement("h3");
				h2.className = "title";
				
				var hSpam = document.createElement("span");
				hSpam.className = "title-text";
				h2.appendChild(hSpam);
				hSpam.innerHTML = files[i].substr(1, files[i].length-5);
				if (hSpam.innerHTML.search("-hide-") != -1)
					hSpam.innerHTML = "";
				hSpam.width = "100%";
				hSpam.style.width = "50%";
				//hSpam.style.whiteSpace = "initial";
				//hSpam.style.wordWrap = "break-word";
				hSpam.style.textShadow = "1px 1px 0px rgba(0, 0, 0, 1)";
				
				document.getElementById("slides").appendChild(li);
				li.appendChild(divLeft);
				li.appendChild(divRight);
				divLeft.append(divImage1);
				divRight.append(divImage2);
				document.getElementById("slide-select").appendChild(newLi);
				li.appendChild(h2);
			}
			var newLi = document.createElement("li");
			newLi.className = "btn next fa fa-arrow-right";
			document.getElementById("slide-select").appendChild(newLi);
		</script>
	</div>
</div>
<!-- ### END ### !-->

<body>
	<div id="wrapper">
		<div id="middle">
			<div id="noutati">
				<h1 style="font-size: 20px;"><b><i><center>Noutăți</center></i></b></h1>
				
			</div>
			<br><br><br><br><br>
		</div>
	</div>
</body>
<script> // Noutati
	var noutati = <?php echo $noutati ?>;
	for(i = 0; i < noutati.length; i++)
	{
		var br = document.createElement("br");
		document.getElementById("noutati").appendChild(br);
		var redirect = document.createElement("a");
		redirect.href = noutati[i]["link"];
		redirect.style.color = 'black';
		var p = document.createElement("p");
		p.innerHTML = noutati[i]["title"];
		p.style.fontWeight = "bold";
		redirect.appendChild(p);
		document.getElementById("noutati").appendChild(redirect);
		if(noutati[i]["desc"]) {
			var p2 = document.createElement("p");
			p2.innerHTML = noutati[i]["desc"];
			document.getElementById("noutati").appendChild(p2);
		}
		
	}
	var x, s;
	if(screen.width > screen.height) {
		x = screen.height;
		s = 0.7;
	}
	else {
		x = screen.width;
		s = 1.5;
	} 
	document.getElementById("container").style.height = (s * x) + "px"; // slideshow corrector
</script>
<footer>
	<div id="credits">
		© COPYRIGHT 2019 Liceul tehnologic "Şcoala Naţională de Gaz" Mediaş. Realizat de Ivan Adrian. Colaboratori: Comșa Filip.
	</div>
</footer>
</html>

