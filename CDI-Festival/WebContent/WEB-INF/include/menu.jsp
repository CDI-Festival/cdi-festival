<%-- Main menu --%>

<nav>

	<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>

	<ul>
		<li><a href="#">Accueil</a></li>
		<li><a href="#" onclick="callBandController('Groupe')">Groupe</a></li>
		<li><a href="#">Sc&egrave;nes</a></li>
		<li><a href="pass/show">Billetterie</a></li>
	</ul>
</nav>