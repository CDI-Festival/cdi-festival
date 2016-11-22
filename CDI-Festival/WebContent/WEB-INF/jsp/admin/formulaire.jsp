<jsp:directive.page contentType="text/html; charset=UTF-8" language="java" />
<jsp:directive.page info="This JSP page is used for the band creation form. Author: Claire. Version: 20161120" />

<jsp:directive.page import="fr.cdiFestival.model.Band" />

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bandstyle.css" />
<title>CDI Festival 2017 - Formulaire groupe</title>
</head>
<body>

	<%-- Attributes to pre-fill the form for band update --%>
	<jsp:scriptlet>
		Band band = (Band) request.getAttribute("band");
		String bandName = "";
		String bandBiography = "";
		String bandWebsite = "";
		String attribute = "required";
		
		if (band != null) {
			
			attribute = "disabled";
			
			if (band.getName() != "") {
				bandName = band.getName();
			}
			if (band.getBiography() != null) {
				bandBiography = band.getBiography();
			}
			if (band.getWebsite() != null) {
				bandWebsite = band.getWebsite();
			}
		}
	</jsp:scriptlet>

	<%--Include the main administration menu --%>
	<jsp:include page="/WEB-INF/include/menu-admin.jsp" />

	<div id="container">

		<jsp:include page="/WEB-INF/include/header.jsp" />

		<%-- Attributes to change the h2 title --%>
		<jsp:scriptlet>
			String h2title = "Créer une fiche groupe";
			if (band != null) {
				h2title = "Modifier une fiche groupe";
			}
		</jsp:scriptlet>
		
		<h2><jsp:expression>h2title</jsp:expression></h2>
		
		<div id="inner-container">
			<!-- New band creation form -->
			<form name="fiche" method="post" onsubmit="return validate(this);"
				action="<%=request.getContextPath()%>/admin/groupes/enregistrer">
				<label>Nom du groupe*</label>
				<input type="text" name="bandname" value="<jsp:expression>bandName</jsp:expression>" 
					<jsp:expression>attribute</jsp:expression> />
				<br /> <br />
				<label>Logo</label>
				<br /><br />
				<label>Photo</label>
				<br /><br />
				<br /> <label>Biographie</label>
				<textarea name="bandbio"><jsp:expression>bandBiography</jsp:expression></textarea>
				<br /><br />
				<!-- TODO text field + year box? -->
				<label>Discographie</label>
				<textarea name="banddisco"></textarea>
				<br /> <br />
				<label>Site Internet</label>
				<input type="url" name="bandwebsite" value="<jsp:expression>bandWebsite</jsp:expression>" />
				<br /> <br />
				
				<%-- Attributes to adapt form buttons --%>
				<jsp:scriptlet>
					String cancel = "Effacer";
					String save = "Enregistrer";
					
					if (band != null) {
						cancel = "Annuler";
						save = "Enregistrer les modifications";
					}
				</jsp:scriptlet>
				
				<input type="reset" name="cancelbutton" value="<jsp:expression>cancel</jsp:expression>" />
				<input type="submit" name="savebutton" value="<jsp:expression>save</jsp:expression>" />
			</form>
		
		</div>
		
		<jsp:include page="/WEB-INF/include/footer.jsp" />
		
	</div>

	<%-- To handle calls to servlet from link and button. --%>
	<script src="<%=request.getContextPath()%>/js/bandscript.js"></script>
	<%-- To handle form submit --%>
	<script src="<%=request.getContextPath()%>/js/bandformscript.js"></script>
	
</body>
</html>