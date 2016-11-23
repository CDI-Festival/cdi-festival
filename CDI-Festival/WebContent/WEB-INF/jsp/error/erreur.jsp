<jsp:directive.page contentType="text/html; charset=UTF-8" language="java" />
<jsp:directive.page info="This JSP page is used when something went wrong. Author: Claire. Version: 20161122" />

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bandstyle.css" />
<title>CDI Festival 2017 - Erreur</title>
</head>
<body>

	<%--Include the main administration menu --%>
	<jsp:include page="/WEB-INF/include/menu-admin.jsp" />

	<div id="container">

		<jsp:include page="/WEB-INF/include/header.jsp" />

		<h2>Oooops !</h2>
		
		<div id="inner-container">
		
				<p>Désolé, votre demande n'a pas aboutie.
				<br />Il semble qu'il y ait eu un souci lors de 
				<jsp:expression>request.getAttribute("erreur")</jsp:expression>.</p>
				<p><jsp:expression>request.getAttribute("erreurInfo")</jsp:expression></p>
				<a href="#" onclick="callBandController('Groupe')">> Retourner sur la liste des groupes</a>
			
		</div>
		
		<jsp:include page="/WEB-INF/include/footer.jsp" />
		
	</div>

	<%-- To handle calls to servlet from link and button. --%>
	<script src="${pageContext.request.contextPath}/js/bandscript.js"></script>
	<%-- To handle form submit --%>
	<script src="${pageContext.request.contextPath}/js/bandformscript.js"></script>
	
</body>
</html>