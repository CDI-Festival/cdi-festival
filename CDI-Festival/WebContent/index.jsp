<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="fr.cdiFestival.model.Article" %>
<%@ page import="fr.cdiFestival.service.Articles" %>

<% Articles listArticle = (Articles) request.getAttribute("articles"); %>

<!DOCTYPE html>

<html lang="fr">

<head>
	<meta charset="utf-8" />
	<title>CDI-Festival index</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/articleStyle.css">
</head>

<body>

	<%-- Include banner --%>
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Include main menu -->
	<jsp:include page="/WEB-INF/include/menu.jsp" />


<!-- Creation button -->
	<form id="btncreate" action="<%=request.getContextPath()%>/article/addpage">
		<button onclick="back()">Créer un nouvel article</button>
	</form>
	

<!-- Articles section -->
	<div class="container">
		
<%-- Article maker with the list sent by ArticleController  --%>
		<% for (Article article : listArticle) { %>
		
			<article id="<%=article.getId()%>" class="summary_container" role="article">
				<header id="context">
					<h3><a href="<%=request.getContextPath()%>/article/read?id=<%=article.getId()%>"> <%= article.getTitle() %></a></h3>
					<h5 class="date">Le :<%= article.getDate() %>, par :<%=article.getAuthor()%></h5>
				</header>
				<div class="summary"><p class="justify">
				<%if (article.getContent().length() >= 149) { %>
					<%=article.getContent().substring(0, 150) + "..." %> 
				<% } else { %>
					<%=article.getContent()%>
				<% } %>
				</p></div>
			</article>
		
		<% } %>

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
	
	<script src="${pageContext.request.contextPath}/js/bandscript.js"></script>
	
</body>

</html>