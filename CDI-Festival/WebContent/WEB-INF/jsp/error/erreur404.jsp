<jsp:directive.page contentType="text/html; charset=UTF-8" language="java" />
<jsp:directive.page info="This JSP page is used when something went wrong. Author: Claire. Version: 20161122" />

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bandstyle.css" />
<title>CDI Festival 2017 - Erreur 404</title>
</head>
<body>

	<%--Include the main administration menu --%>
	<jsp:include page="/WEB-INF/include/menu-admin.jsp" />

	<div id="container">

		<jsp:include page="/WEB-INF/include/header.jsp" />

		<h2>Erreur 404</h2>
		
		<div id="inner-container">
		
			<div id="main-content">
				<p>"Looks like you found a dead link!"</p>
				<img id="error" src="${pageContext.request.contextPath}/img/deadLink.png" alt="Dead Link" />
			</div>
			
			<p>Désolé, la page demandée n'existe pas !
			<br />Plusieurs raisons possibles :
			<br />- J'ai failli à mon devoir et oublié de faire une redirection vers la bonne page.
			<br />- Comme disait mon mentor, vous avez peut-être tripatouillé l'URL !
			</p>
			
		</div>
		
		<jsp:include page="/WEB-INF/include/footer.jsp" />
		
	</div>

	<%-- To handle calls to servlet from link and button. --%>
	<script src="${pageContext.request.contextPath}/js/bandscript.js"></script>
	<%-- To handle form submit --%>
	<script src="${pageContext.request.contextPath}/js/bandformscript.js"></script>
	
</body>
</html>