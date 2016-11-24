<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%-- Useful class import --%>
<%@ page import="fr.cdiFestival.model.Article" %>
<%@ page import="fr.cdiFestival.service.Articles" %>

<%-- Getting Article list from the Main controller --%>
<% Articles listArticle = (Articles) request.getAttribute("articles"); %>
<% System.out.println(listArticle); %>

<!DOCTYPE html>

<html lang="fr">

<head>
	<meta charset="utf-8" />
	<title>CDI-Festival index</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/articleStyle.css">
</head>

<body>

<!-- Include banner -->
	<jsp:include page="/WEB-INF/include/header.jsp" />

<!-- Include main menu -->
	<jsp:include page="/WEB-INF/include/menu.jsp" />


<!-- Creation button -->
	<form id="btncreate" action="<%=request.getContextPath()%>/article/addpage">
		<button onclick="back()">Créer un nouvel article</button>
	</form>
	

<!-- Articles section -->
	<div class="container">
		
		<%-- Getting each article in the list and fill all the summary container  --%>
		<% for (Article article : listArticle) { %>
		
			<article id="<%=article.getId()%>" class="summary_container" role="article">
				<header id="context">
					<h3><a href="<%=request.getContextPath()%>/article/read?id=<%=article.getId()%>"> <%= article.getTitle() %></a></h3>
					<h5 class="date">Le :<%= article.getDate() %>, par :<%=article.getAuthor()%></h5>
				</header>
				
				<%-- Checking the content and reduce it if it's bigger than 150 characters --%>
				<div class="summary"><p class="justify">
				<%if (article.getContent().length() >= 149) { %>
					<%=article.getContent().substring(0, 150) + "..." %> 
				<% } else { %>
					<%=article.getContent()%>
				<% } %>
				</p></div>
			</article>
		
		<% } %>
		
	<!-- Footer -->
	<jsp:include page="/WEB-INF/include/footer.jsp" />
	</div>
	
	<script src="${pageContext.request.contextPath}/js/bandscript.js"></script>
	
</body>

</html>