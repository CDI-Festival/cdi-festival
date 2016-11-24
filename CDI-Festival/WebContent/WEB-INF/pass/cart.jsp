<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">

<head>
<meta charset="UTF-8" />

<title>Festival CDI 2017</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/style/passStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
<%@ page import="java.util.*"%>

</head>

<body>

	    	<%-- Include banner --%>
	<jsp:include page="/WEB-INF/include/header.jsp" />

	<!-- Include main menu -->
	<jsp:include page="/WEB-INF/include/menu.jsp" />




	<div class="title">
		<H1>Billet Festival</H1>
	</div>



	<hr>
	<section class="section-pass">


		<div class="subTitle">
			<h2>le pass </h2>
		</div>

		
		
		<div class="section group">
	<div class="col span_1_of_2"> 
    <img class="pass" src="${pageContext.request.contextPath}/images/pass.png"
	alt="pass">
	</div>
	<div class="col span_1_of_2">
        <H3>CDIFEST : BILLET <%=request.getAttribute("journée")%> JOURS</H3>
        <p class="festi-date">Du Vendredi 23/06/2017 au Dimanche 25/06/2017</p>
        <p>Hippodrome Bor&eacute;ly de Marseille</p>
        <p class="align-right">Selectionnez vos places</p>
        <hr>
        <span class="checkout">Pass : <%=request.getAttribute("description")%>
        <p id="pricing"><%=request.getAttribute("price")%> Euros</p>
        <p>Les Dates <%=request.getAttribute("date").toString()%></p>
         
        <p class="checkout">Quantité :</p>  
            
            <form name="quantity" method="post" action="checkout/Checkout"
							id="tikcetform">

							<select id="mySelect" name="ticketQuantity" form="tikcetform" onchange="calculTotal()">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>

							</select> <input type="submit">
						</form>
					</span>
					
					
					<div id=result>
						
					</div>
	</section>
<jsp:include page="/WEB-INF/include/footer.jsp" />
</body>
</html>
