<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.cdiFestival.model.Article" %>
<%Article article = (Article)request.getAttribute("article"); %>    

<!DOCTYPE html">

<html lang="fr">

<head>
	<meta charset="utf-8" />
	<title>Accueil Festival</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
	<link rel="stylesheet" type="text/css" href="http://localhost:8085/CDI_Festival/style/articleStyle.css">
</head>

<body>


	<!--  Banner with logo and text -->
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Navigation menu bar -->
	<jsp:include page="/WEB-INF/include/menu.jsp" />


<!-- News section -->
	<div class="container">
		<article class="summary_container" role="article">
			<header id="context">
     			<h3> <%= article.getTitle() %> </h3>
     			<h5 class="date">Le :<%= article.getDate() %>, par :<%=article.getAuthor()%></h5>
    		</header>   
    		<br />           
    	<div class="summary"><p class="justify"><%= article.getContent() %></p></div>
		</article>
		<div class="btn">
			<a href="<%=request.getContextPath()%>/article/updatepage?id= <%= article.getId() %>"><button onsubmit="">Modifier</button></a>
			<form onsubmit="" action="<%=request.getContextPath()%>/article/delete" method="post"> 
				<button type="submit">Supprimer</button>
				<input type="hidden" name="hiddenid" value="<%= article.getId() %>"/>
			</form>
		</div>
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