<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.cdiFestival.model.Article" %>
<%Article article = (Article)request.getAttribute("article"); %>    

<!DOCTYPE html">

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
			<li><a href="#">Accueil</a></li>
			<li><a href="#">Groupes</a></li>
			<li><a href="#">Programmation</a></li>
			<li><a href="#">Billeterie</a></li>
		</ul>
	</nav>


<!-- News section -->
	<div class="container">
		<article class="fullnews_container" role="article">
			<header>
     			<h1> <%= article.getTitle() %> </h1>
     			<time class="date" pubdate="pubdate">Le :<%= article.getDate() %>, par :<%=article.getAuthor()%></time>
    		</header>   
    		<br />           
    	<div class="full_news"><p class="justify"><%= article.getContent() %></p></div>
		</article>
		
		<form action="<%=request.getContextPath()%>/article/update?id=<%=article.getId()%>"> </form>
		<form onsubmit="" action=""> <button type="submit">Supprimer</button> </form>
		
	</div>

<!-- Footer with informations, partners and links -->
	<footer class="footer_container">
			<ul class="footer_links, footer_list">
				<li><a href="#">Contact</a></li>
				<li><a href="#">Mentions légales</a></li>
				<li><a href="#">Crédits</a></li>
				<li><a href="#">Admin</a></li>
			</ul>
			<ul class="footer_socials, footer_list">
				<li><a href="#"><img id="logo_FB" class="icon" src="../../images/socials/logoFB.png"></a></li>
				<li><a href="#"><img id="logo_Twitter" class="icon" src="../../images/socials/logoTwitter.png"></a></li>
				<li><a href="#"><img id="logo_YT" class="icon" src="../../images/socials/logoYT.png"></a></li>
			</ul>
	</footer>
</body>
</html>