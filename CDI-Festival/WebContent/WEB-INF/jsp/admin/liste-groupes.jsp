<jsp:directive.page contentType="text/html; charset=UTF-8" language="java" />
<jsp:directive.page info="This JSP page is used for the bands management. Author: Claire. Version: 20161119" />

<jsp:directive.page import="java.util.ArrayList" />

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bandstyle.css" />
<title>CDI Festival 2017 - Gestion groupes</title>
</head>
<body>

	<%-- Attributes for JSP --%>
	<jsp:useBean id="bandNameList" type="java.util.ArrayList<String>" scope="request" />

	<%--Include the main header menu --%>
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<%--Include the main administration menu --%>
	<jsp:include page="/WEB-INF/include/menu-admin.jsp" />

	<div id="container">

		<h2>Gestion des groupes</h2>

		<div id="inner-container">
			
			<div id="main-content">				
			
				<jsp:scriptlet>
				if (bandNameList.size() > 0) {
				</jsp:scriptlet>
				
					<select multiple id="listband" name="band">
				
					<%-- If the bandNameList is not empty, displays the list --%>
					<jsp:scriptlet>
					
						for (String bandName : bandNameList) {
					</jsp:scriptlet>
				
						<option value="<jsp:expression>bandName</jsp:expression>">
						<jsp:expression>bandName</jsp:expression></option>
	
						<jsp:scriptlet>
						; }
						</jsp:scriptlet>
					
					</select>
					
					<jsp:scriptlet>
					} else {
					</jsp:scriptlet>	
						
					<p>Il n'y a aucun groupe enregistr&eacute;.</p>
					
						<jsp:scriptlet>
						}
						</jsp:scriptlet>
					
					<br />
					
					<button type="button" onclick="callBandController('Creer')" value="Créer">Cr&eacute;er</button>
					<button type="button" onclick="callBandController('Modifier')" value="Modifier">Modifier</button>
					<button type="button" onclick="callBandController('Supprimer')" value="Supprimer">Supprimer</button>
			</div>
		</div>

		<jsp:include page="/WEB-INF/include/footer.jsp" />

	</div>

</body>
</html>