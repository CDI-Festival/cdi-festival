<%-- Main admin menu --%>

<nav>

	<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}"/>

	<ul>
		<li><a href="<%=request.getContextPath()%>/accueil">Accueil</a></li>
		<li><a href="#" onclick="callBandController('Groupe')">Groupe</a></li>
		<li><a href="#">Sc&egrave;nes</a></li>
		<li class="dropdown" ><a href="#">Billetterie</a>	
		<div class="dropsub">
			<a href="${pageContext.request.contextPath}/pass/show">Achat Pass</a>
			<a href="${pageContext.request.contextPath}/pass/manage">Gestion Pass</a>
		</div>
	</ul>
</nav>