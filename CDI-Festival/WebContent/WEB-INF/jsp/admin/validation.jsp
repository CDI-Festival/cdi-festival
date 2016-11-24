<jsp:directive.page contentType="text/html; charset=UTF-8" language="java" />
<jsp:directive.page info="This JSP page is used when a band is registered. Author: Claire. Version: 20161119" />

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bandstyle.css" />
<title>CDI Festival 2017 - Validation</title>
</head>
<body>

	<%-- Include banner --%>
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Include main menu -->
	<jsp:include page="/WEB-INF/include/menu-admin.jsp" />

	<div id="container">

		<h2>Validation</h2>
		
		<div id="inner-container">
			
			<div id="main-content">
			
				<p>Le groupe <jsp:expression>request.getAttribute("name")</jsp:expression>
					 <jsp:expression>request.getAttribute("validationType")</jsp:expression>.</p>
				<p>Que souhaitez-vous faire ?</p>
				<a href="#" onclick="callBandController('Groupe')">> Voir la liste des groupes</a>
				<br />
				<a href="#" onclick="callBandController('Creer')">> Cr&eacute;er une nouvelle fiche groupe</a>
				
			</div>
			
		</div>
		
		<jsp:include page="/WEB-INF/include/footer.jsp" />
		
	</div>
	
</body>
</html>