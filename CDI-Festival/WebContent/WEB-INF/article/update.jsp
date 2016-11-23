<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.cdiFestival.model.Article" %>
<jsp:scriptlet>Article article = (Article)request.getAttribute("article"); </jsp:scriptlet> 

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
		<img id="banner_logo" src="../../images//banner/logo.png">
		<h1 id="slogan">More code, more ROCK !!</h1>
		<img id="banner_dates" src="../../images/banner/dates.png">
	</header>


<!-- Navigation menu bar -->
	<nav id="menu">
		<ul>
			<li><a href="">Accueil</a></li>
			<li><a href="">Groupes</a></li>
			<li><a href="">Programmation</a></li>
			<li><a href="">Billeterie</a></li>
		</ul>
	</nav>


<!-- News maker -->
	<div class="containercreate">
		<form onsubmit="A VENIR" name="btncreate" action="http://localhost:8085/CDI_Festival/article/update" method="post">
			
			<p>	<label class="aligne" for="author">Auteur</label> 
				<input type="text" id="author" name="author" value="<jsp:expression> =article.getAuthor()</jsp:expression">
			</p>
			<p> 
				<label class="aligne" for="date">Date création</label> 
				<label> <jsp:expression> article.getDate() </jsp:expression> </label>
			</p>
   	 		<p> <label class="aligne" for="title">Titre</label> 
   	 			<input type="text" id="inputtitle" name="title" value="<jsp:expression> article.getTitle() </jsp:expression"> 
   	 		</p>
   	 		<p> <label class="aligne" for="content">Article</label> 
   	 			<textarea id="content" name="content"><jsp:expression> article.getContent() </jsp:expression></textarea> 
   	 		</p>
   	 		
   	 		<div class="btn">
    			<button name="btnvalidate" type="submit">Valider</button>
    			<button name="btncancel" type="button" onclick="back()">Annuler</button>
    		</div> 
		</form>
	</div>
   	
<!-- Footer with informations, partners and links -->
	<footer class="footer_container">
			<ul class="footer_links, footer_list">
				<li><a href="#">Contact</a></li>
				<li><a href="#">Mentions légales</a></li>
				<li><a href="#">Crédits</a></li>
				<li><a href="#">WebMaster</a></li>
			</ul>
			<ul class="footer_socials, footer_list">
				<li><a href="#"><img id="logo_FB" class="icon" src="../../images/socials/logoFB.png"></a></li>
				<li><a href="#"><img id="logo_Twitter" class="icon" src="../../images/socials/logoTwitter.png"></a></li>
				<li><a href="#"><img id="logo_YT" class="icon" src="../../images/socials/logoYT.png"></a></li>
			</ul>
	</footer>
	
	<script src="http://localhost:8085/CDI_Festival/js/article.js"></script>
</body>
</html>