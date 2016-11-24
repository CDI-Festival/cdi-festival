<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.cdiFestival.model.Article" %>
<%Article article = (Article)request.getAttribute("article"); %>    

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



<!-- Navigation menu bar -->



<!-- News maker -->
	<div class="container">
		<form onsubmit="A VENIR" name="btncreate" action="http://localhost:8085/CDI_Festival/article/update" method="post">
			<p>	<label class="aligne" for="author">Auteur</label> <input type="text" id="author" name="author" value="<%=article.getAuthor() %>"></p>
			<p> <label class="aligne" for="date">Date création</label> <label> <%= article.getDate() %> </label></p>
   	 		<p> <label class="aligne" for="title">Titre</label> <input type="text" id="inputtitle" name="title" value="<%=article.getTitle()%>"> </p>
   	 		<p> <label class="aligne" for="content">Article</label> <textarea id="content" name="content"><%= article.getContent() %></textarea> </p>
   	 		<div class="btn">
    			<button name="btnvalidate" type="submit">Valider</button>
    			<button name="btncancel" type="button" onclick="back()">Annuler</button>
    		</div> 
		</form>
		
		<!-- Footer -->
		<jsp:include page="/WEB-INF/include/footer.jsp" />
	</div>
   	
</body>
</html>