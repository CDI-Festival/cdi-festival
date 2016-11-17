<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="fr">

<head>
<meta charset="utf-8" />
<title>Accueil Festival</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8085/CDI_Festival/style/articleStyle.css">
</head>

<body>

<!--  Banner with logo and text -->
	<header class="banner">
		<img id="banner_logo" src="images//banner/logo.png">
		<h1 id="slogan">More code, more ROCK !!</h1>
		<img id="banner_dates" src="images/banner/dates.png">
	</header>

<!-- Navigation menu bar -->
	<nav id="menu">
		<ul>
			<li><a href="#">Accueil</a></li>
			<li><a href="#">Groupes</a></li>
			<li><a href="#">Programmation</a></li>
			<li><a href="#">Billeterie</a></li>
		</ul>
	</nav>

<!-- Articles section -->
	<div class="container">
		<article class="summary_container" role="article">
			<header>
     			<h3><a href="">Article1</a></h3>
     			<time datetime="" pubdate="pubdate"></time>
    		</header>              
    	<div class="summary"><p>L’archéologue, dans une approche 
    	diachronique, acquiert donc l’essentiel de sa documentation 
    	à travers des travaux de terrainN 1, par « opposition » à l’historien, 
    	dont les principales sources sont des textes.</p>
    	</div>
		</article>
		
		<article class="summary_container" role="article">
			<header>
     			<h3><a href="">Article2</a></h3>
     			<time datetime="" pubdate="pubdate"></time>
    		</header>              
    	<div class="summary"><p>L’archéologue, dans une approche 
    	diachronique, acquiert donc l’essentiel de sa documentation 
    	à travers des travaux de terrainN 1, par « opposition » à l’historien, 
    	dont les principales sources sont des textes.</p>
    	</div>
		</article>
	</div>
		
<!-- Footer -->
	<footer class="footer_container">
			<ul class="footer_links, footer_list">
				<li><a href="#">Contact</a></li>
				<li><a href="#">Mentions légales</a></li>
				<li><a href="#">Crédits</a></li>
			</ul>
			<ul class="footer_socials, footer_list">
				<li><a href="#"><img id="logo_FB" class="icon" src="images/socials/logoFB.png"></a></li>
				<li><a href="#"><img id="logo_Twitter" class="icon" src="images/socials/logoTwitter.png"></a></li>
				<li><a href="#"><img id="logo_YT" class="icon" src="images/socials/logoYT.png"></a></li>
			</ul>
			<div id="login"><a href="#">Login</a></div>
	</footer>
	
</body>

</html>