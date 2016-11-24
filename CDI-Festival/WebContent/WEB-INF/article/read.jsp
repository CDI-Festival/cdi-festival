<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.cdiFestival.model.Article" %>

<%-- Getting the article sent from the controller --%>
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
	<%-- Filling each part of html code with article attributes --%>
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
		
<!-- Footer -->
	<jsp:include page="/WEB-INF/include/footer.jsp" />
		
	</div>


	
</body>
</html>