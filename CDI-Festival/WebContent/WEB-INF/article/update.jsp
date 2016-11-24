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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
</head>

<body>


<!--  Banner with logo and text -->
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Navigation menu bar -->
<jsp:include page="/WEB-INF/include/menu.jsp" />



<!-- News maker -->

	<div class="container">
	
		<%-- Filling each part of html code with article attributes --%>
		<form onsubmit="retunr isEmpty" name="btncreate" action="http://localhost:8085/CDI_Festival/article/update" method="post">
			
			<p>	<label class="aligne" for="author">Auteur</label> 
				<input type="text" id="author" name="author" value="<jsp:expression> article.getAuthor() </jsp:expression>">
			</p>
			<p> 
				<label class="aligne" for="date">Date création</label> 
				<label> <jsp:expression> article.getDate() </jsp:expression> </label>
			</p>
   	 		<p> <label class="aligne" for="title">Titre</label> 
   	 			<input type="text" id="inputtitle" name="title" value="<jsp:expression> article.getTitle() </jsp:expression>"> 
   	 		</p>
   	 		<p> <label class="aligne" for="content">Article</label> 
   	 			<textarea id="content" name="content"><jsp:expression> article.getContent() </jsp:expression></textarea> 
   	 		</p>
   	 		
   	 		<div class="btn">
    			<button name="btnvalidate" type="submit">Valider</button>
    			<button name="btncancel" type="button" onclick="back()">Annuler</button>
    			
    			<%-- Just to pass the article ID --%>
    			<input type="hidden" name="hiddenid" value="<%= article.getId() %>"/> 
    		</div> 
		</form>
		
		<!-- Footer -->
		<jsp:include page="/WEB-INF/include/footer.jsp" />
	</div>
	
<script src="http://localhost:8085/CDI_Festival/js/article.js"></script>
   	
</body>
</html>