<jsp:directive.page contentType="text/html; charset=UTF-8" language="java" />
<jsp:directive.page info="This JSP page is used when a band is registered. Author: Claire. Version: 20161119" />

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bandstyle.css" />
<title>CDI Festival 2017 - Validation</title>
</head>
<body>

	<%--Include the main administration menu --%>
	<jsp:include page="/WEB-INF/include/menu-admin.jsp" />

	<div id="container">

		<jsp:include page="/WEB-INF/include/header.jsp" />

		<h2>Validation</h2>
		
		<div id="inner-container">
			
			<p>Le groupe <jsp:expression>request.getAttribute("name")</jsp:expression>
				a bien été <jsp:expression>request.getAttribute("validationType")</jsp:expression>.</p>
			<p>Que souhaitez-vous faire ?</p>
			<a href="#" onclick="callBandController('Groupe')">Voir la liste des groupes.</a>
			<a href="#" onclick="callBandController('Creer')">Cr&eacute;er une nouvelle fiche groupe.</a>
			
		</div>
		
		<jsp:include page="/WEB-INF/include/footer.jsp" />
		
	</div>

	<%-- To handle calls to servlet from link and button. --%>
	<script src="<%=request.getContextPath()%>/js/bandscript.js"></script>
	
</body>
</html>